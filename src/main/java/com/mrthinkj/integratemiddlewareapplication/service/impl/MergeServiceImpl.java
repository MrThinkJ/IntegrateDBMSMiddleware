package com.mrthinkj.integratemiddlewareapplication.service.impl;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;
import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import com.mrthinkj.integratemiddlewareapplication.payload.UpdateInfo;
import com.mrthinkj.integratemiddlewareapplication.repository.mongodao.MongoEmployeeRepository;
import com.mrthinkj.integratemiddlewareapplication.repository.sqldao.SqlEmployeeRepository;
import com.mrthinkj.integratemiddlewareapplication.service.MergeService;
import com.mrthinkj.integratemiddlewareapplication.service.MongoEmployeeService;
import com.mrthinkj.integratemiddlewareapplication.service.SqlEmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MergeServiceImpl implements MergeService {
    MongoEmployeeService mongoEmployeeService;
    SqlEmployeeService sqlEmployeeService;

    public MergeServiceImpl(MongoEmployeeService mongoEmployeeService, SqlEmployeeService sqlEmployeeService) {
        this.mongoEmployeeService = mongoEmployeeService;
        this.sqlEmployeeService = sqlEmployeeService;
    }

    @Override
    public List<MergePerson> mergeAllPerson() {
        List<MongoEmployee> mongoEmployees = mongoEmployeeService.getAllEmployee();
        List<SqlEmployee> sqlEmployees = sqlEmployeeService.getAllEmployee();
        Map<String, SqlEmployee> sqlEmployeeMap = new HashMap<>();
        List<MergePerson> mergePersons = new ArrayList<>();
        for (SqlEmployee sqlEmployee : sqlEmployees) {
            String key = sqlEmployee.getFirstName() + sqlEmployee.getLastName();
            sqlEmployeeMap.put(key, sqlEmployee);
        }

        for (MongoEmployee mongoEmployee : mongoEmployees) {
            String key = mongoEmployee.getFirstName() + mongoEmployee.getLastName();
            SqlEmployee matchingSqlEmployee = sqlEmployeeMap.get(key);
            if (matchingSqlEmployee != null) {
                mergePersons.add(mergeTwoEmployees(matchingSqlEmployee, mongoEmployee));
                sqlEmployeeMap.remove(key);
            }
            else{
                mergePersons.add(mergeTwoEmployees(SqlEmployee.builder()
                        .id(mongoEmployee.getId())
                        .firstName(mongoEmployee.getFirstName())
                        .lastName(mongoEmployee.getLastName()).build(),
                        mongoEmployee));
            }
        }
        for (SqlEmployee employee : sqlEmployeeMap.values())
            mergePersons.add(mergeTwoEmployees(employee, new MongoEmployee()));
        return mergePersons;
    }

    private MergePerson mergeTwoEmployees(SqlEmployee sqlEmployee, MongoEmployee mongoEmployee){
        return MergePerson.builder()
                .id(sqlEmployee.getId())
                .firstName(sqlEmployee.getFirstName())
                .lastName(sqlEmployee.getLastName())
                .benefitPlans(sqlEmployee.getBenefitPlans())
                .email(sqlEmployee.getEmail())
                .address1(sqlEmployee.getAddress1())
                .address2(sqlEmployee.getAddress2())
                .ethnicity(sqlEmployee.getEthnicity())
                .gender(sqlEmployee.isGender())
                .maritalStatus(sqlEmployee.getMaritalStatus())
                .middleInitial(sqlEmployee.getMiddleInitial())
                .city(sqlEmployee.getCity())
                .state(sqlEmployee.getState())
                .zip(sqlEmployee.getZip())
                .phoneNumber(sqlEmployee.getPhoneNumber())
                .socialSecurityNumber(sqlEmployee.getSocialSecurityNumber())
                .driversLicense(sqlEmployee.getDriversLicense())
                .shareholderStatus(sqlEmployee.isShareholderStatus())
                .vacationDays(mongoEmployee.getVacationDays())
                .paidToDate(mongoEmployee.getPaidToDate())
                .paidLastYear(mongoEmployee.getPaidLastYear())
                .payRate(mongoEmployee.getPayRate())
                .payRateId(mongoEmployee.getPayRateId())
                .createdAt(mongoEmployee.getCreatedAt())
                .updatedAt(mongoEmployee.getUpdatedAt())
                .build();
    }

    public String deleteFromTwoDBMS(String firstName, String lastName){
        boolean sql = sqlEmployeeService.deleteEmployeeByFirstNameAndLastName(firstName, lastName);
        boolean mongo = mongoEmployeeService.deleteEmployeeByFirstNameAndLastName(firstName, lastName);
        if (sql && !mongo)
            return "Delete in SQLServer";
        if (!sql && mongo)
            return "Delete in Mongo";
        if (!sql)
            return "Delete in both DBMS";
        return "Two DBMS do not contain these two values";
    }

    @Override
    public MergePerson updateFromTwoDBMS(Integer typeId, boolean isUpdated, MergePerson mergePerson) {
        String firstName = mergePerson.getFirstName();
        String lastName = mergePerson.getLastName();
        UpdateInfo updateInfo = UpdateInfo.getInfoFromInteger(typeId);
        if (updateInfo == UpdateInfo.All){
            sqlEmployeeService.updateEmployeeByFirstNameAndLastName(firstName, lastName, mergePerson);
            mongoEmployeeService.updateEmployeeByFirstNameAndLastName(firstName, lastName, mergePerson);
            return mergePerson;
        }
        if (updateInfo == UpdateInfo.Mongo){
            mongoEmployeeService.updateEmployeeByFirstNameAndLastName(firstName, lastName, mergePerson);
            if (isUpdated)
                sqlEmployeeService.createNewEmployee(mergePerson);
            return mergePerson;
        }
        sqlEmployeeService.updateEmployeeByFirstNameAndLastName(firstName, lastName, mergePerson);
        if (isUpdated)
            mongoEmployeeService.createNewEmployee(mergePerson);
        return mergePerson;
    }

    @Override
    public UpdateInfo getUpdateInfo(String firstName, String lastName) {
        boolean sql = sqlEmployeeService.getEmployeeByFirstNameAndLastname(firstName, lastName) != null;
        boolean mongo = mongoEmployeeService.getEmployeeByFirstNameAndLastname(firstName, lastName) != null;
        if (sql && mongo)
            return UpdateInfo.All;
        if (!sql)
            return UpdateInfo.Mongo;
        return UpdateInfo.SqlServer;
    }


}
