package com.mrthinkj.integratemiddlewareapplication.service;

import com.mrthinkj.core.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;

import java.util.List;

public interface MongoEmployeeService {
    List<MongoEmployee> getAllEmployee();
    boolean deleteEmployeeByFirstNameAndLastName(String firstName, String lastName);
    MongoEmployee getEmployeeByFirstNameAndLastname(String firstName, String lastName);
    MongoEmployee createNewEmployee(MongoEmployee mongoEmployee);
    MongoEmployee updateEmployeeByFirstNameAndLastName(String firstName, String lastName, MergePerson mergePerson);
}
