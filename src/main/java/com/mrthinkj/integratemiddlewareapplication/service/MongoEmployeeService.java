package com.mrthinkj.integratemiddlewareapplication.service;

import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;

import java.util.List;

public interface MongoEmployeeService {
    List<MongoEmployee> getAllEmployee();
}
