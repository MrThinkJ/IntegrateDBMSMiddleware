package com.mrthinkj.integratemiddlewareapplication.service;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;

import java.util.List;

public interface MongoEmployeeService {
    List<MongoEmployee> getAllEmployee();
    boolean deleteEmployeeByFirstNameAndLastName(String firstName, String lastName);
    MongoEmployee getEmployeeByFirstNameAndLastname(String firstName, String lastName);
    MongoEmployee createNewEmployee(MergePerson mergePerson);
    MongoEmployee updateEmployeeByFirstNameAndLastName(String firstName, String lastName, MergePerson mergePerson);
}
