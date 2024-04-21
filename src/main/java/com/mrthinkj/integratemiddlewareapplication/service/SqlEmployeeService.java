package com.mrthinkj.integratemiddlewareapplication.service;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;

import java.util.List;

public interface SqlEmployeeService {
    List<SqlEmployee> getAllEmployee();
    boolean deleteEmployeeByFirstNameAndLastName(String firstName, String lastName);
    SqlEmployee getEmployeeByFirstNameAndLastname(String firstName, String lastName);
    void createNewEmployee(MergePerson mergePerson);
    void updateEmployeeByFirstNameAndLastName(String firstName, String lastName, MergePerson mergePerson);
}
