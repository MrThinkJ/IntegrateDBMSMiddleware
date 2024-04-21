package com.mrthinkj.integratemiddlewareapplication.service.impl;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;
import com.mrthinkj.integratemiddlewareapplication.repository.mongodao.MongoEmployeeRepository;
import com.mrthinkj.integratemiddlewareapplication.service.MongoEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoEmployeeServiceImpl implements MongoEmployeeService {
    MongoEmployeeRepository mongoEmployeeRepository;

    public MongoEmployeeServiceImpl(MongoEmployeeRepository mongoEmployeeRepository) {
        this.mongoEmployeeRepository = mongoEmployeeRepository;
    }

    @Override
    public List<MongoEmployee> getAllEmployee() {
        return mongoEmployeeRepository.findAll();
    }

    @Override
    public boolean deleteEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        MongoEmployee mongoEmployee = mongoEmployeeRepository.findByFirstNameAndLastName(firstName, lastName);
        if (mongoEmployee == null)
            return false;
        try{
            mongoEmployeeRepository.delete(mongoEmployee);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public MongoEmployee getEmployeeByFirstNameAndLastname(String firstName, String lastName) {
        return mongoEmployeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public MongoEmployee createNewEmployee(MergePerson mergePerson) {
        MongoEmployee mongoEmployee = MongoEmployee.builder()
                .id(mergePerson.getId())
                .firstName(mergePerson.getFirstName())
                .lastName(mergePerson.getLastName())
                .vacationDays(mergePerson.getVacationDays())
                .payRate(mergePerson.getPayRate())
                .paidLastYear(mergePerson.getPaidLastYear())
                .payRateId(mergePerson.getPayRateId())
                .paidToDate(mergePerson.getPaidToDate())
                .createdAt(mergePerson.getCreatedAt())
                .updatedAt(mergePerson.getUpdatedAt())
                .build();
        return mongoEmployeeRepository.save(mongoEmployee);
    }

    @Override
    public MongoEmployee updateEmployeeByFirstNameAndLastName(String firstName, String lastName, MergePerson mergePerson) {
        MongoEmployee mongoEmployee = getEmployeeByFirstNameAndLastname(firstName, lastName);
        return MongoEmployee.builder()
                .id(mongoEmployee.getId())
                .firstName(mongoEmployee.getFirstName())
                .lastName(mongoEmployee.getLastName())
                .vacationDays(mergePerson.getVacationDays())
                .payRate(mergePerson.getPayRate())
                .paidLastYear(mergePerson.getPaidLastYear())
                .payRateId(mergePerson.getPayRateId())
                .paidToDate(mergePerson.getPaidToDate())
                .createdAt(mergePerson.getCreatedAt())
                .updatedAt(mergePerson.getUpdatedAt())
                .build();
    }
}
