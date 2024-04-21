package com.mrthinkj.integratemiddlewareapplication.service.impl;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import com.mrthinkj.integratemiddlewareapplication.repository.sqldao.SqlEmployeeRepository;
import com.mrthinkj.integratemiddlewareapplication.service.SqlEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlEmployeeServiceImpl implements SqlEmployeeService {
    SqlEmployeeRepository sqlEmployeeRepository;

    public SqlEmployeeServiceImpl(SqlEmployeeRepository sqlEmployeeRepository) {
        this.sqlEmployeeRepository = sqlEmployeeRepository;
    }

    @Override
    public List<SqlEmployee> getAllEmployee() {
        return sqlEmployeeRepository.findAll();
    }

    @Override
    public boolean deleteEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        SqlEmployee sqlEmployee = sqlEmployeeRepository.findByFirstNameAndLastName(firstName, lastName);
        if (sqlEmployee == null)
            return false;
        try{
            sqlEmployeeRepository.delete(sqlEmployee);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public SqlEmployee getEmployeeByFirstNameAndLastname(String firstName, String lastName) {
        return sqlEmployeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void createNewEmployee(MergePerson mergePerson) {
        SqlEmployee sqlEmployee = SqlEmployee.builder()
                .id(mergePerson.getId())
                .firstName(mergePerson.getFirstName())
                .lastName(mergePerson.getLastName())
                .benefitPlans(mergePerson.getBenefitPlans())
                .email(mergePerson.getEmail())
                .address1(mergePerson.getAddress1())
                .address2(mergePerson.getAddress2())
                .ethnicity(mergePerson.getEthnicity())
                .gender(mergePerson.isGender())
                .maritalStatus(mergePerson.getMaritalStatus())
                .middleInitial(mergePerson.getMiddleInitial())
                .city(mergePerson.getCity())
                .state(mergePerson.getState())
                .zip(mergePerson.getZip())
                .phoneNumber(mergePerson.getPhoneNumber())
                .socialSecurityNumber(mergePerson.getSocialSecurityNumber())
                .driversLicense(mergePerson.getDriversLicense())
                .shareholderStatus(mergePerson.isShareholderStatus())
                .build();
        sqlEmployeeRepository.save(sqlEmployee);
    }

    @Override
    public void updateEmployeeByFirstNameAndLastName(String firstName, String lastName, MergePerson mergePerson) {
        SqlEmployee sqlEmployee = getEmployeeByFirstNameAndLastname(firstName, lastName);
        SqlEmployee.builder()
                .id(sqlEmployee.getId())
                .firstName(sqlEmployee.getFirstName())
                .lastName(sqlEmployee.getLastName())
                .benefitPlans(mergePerson.getBenefitPlans())
                .email(mergePerson.getEmail())
                .address1(mergePerson.getAddress1())
                .address2(mergePerson.getAddress2())
                .ethnicity(mergePerson.getEthnicity())
                .gender(mergePerson.isGender())
                .maritalStatus(mergePerson.getMaritalStatus())
                .middleInitial(mergePerson.getMiddleInitial())
                .city(mergePerson.getCity())
                .state(mergePerson.getState())
                .zip(mergePerson.getZip())
                .phoneNumber(mergePerson.getPhoneNumber())
                .socialSecurityNumber(mergePerson.getSocialSecurityNumber())
                .driversLicense(mergePerson.getDriversLicense())
                .shareholderStatus(mergePerson.isShareholderStatus())
                .build();
    }
}
