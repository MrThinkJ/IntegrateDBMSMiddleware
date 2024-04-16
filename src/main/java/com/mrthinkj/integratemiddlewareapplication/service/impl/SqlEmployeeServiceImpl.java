package com.mrthinkj.integratemiddlewareapplication.service.impl;

import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import com.mrthinkj.integratemiddlewareapplication.repository.sqldao.SqlEmployeeRepository;
import com.mrthinkj.integratemiddlewareapplication.service.SqlEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlEmployeeServiceImpl implements SqlEmployeeService {
    SqlEmployeeRepository employeeRepository;

    public SqlEmployeeServiceImpl(SqlEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<SqlEmployee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
