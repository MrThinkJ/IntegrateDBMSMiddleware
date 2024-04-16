package com.mrthinkj.integratemiddlewareapplication.service.impl;

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
}
