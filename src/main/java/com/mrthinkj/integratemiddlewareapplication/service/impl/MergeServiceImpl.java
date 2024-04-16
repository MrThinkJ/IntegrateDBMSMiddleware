package com.mrthinkj.integratemiddlewareapplication.service.impl;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;
import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
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
}
