package com.mrthinkj.integratemiddlewareapplication.controller;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;
import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import com.mrthinkj.integratemiddlewareapplication.service.MergeService;
import com.mrthinkj.integratemiddlewareapplication.service.MongoEmployeeService;
import com.mrthinkj.integratemiddlewareapplication.service.SqlEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    SqlEmployeeService sqlEmployeeService;
    MongoEmployeeService mongoEmployeeService;
    MergeService mergeService;

    public EmployeeController(SqlEmployeeService sqlEmployeeService, MongoEmployeeService mongoEmployeeService, MergeService mergeService) {
        this.sqlEmployeeService = sqlEmployeeService;
        this.mongoEmployeeService = mongoEmployeeService;
        this.mergeService = mergeService;
    }

    @GetMapping("/sql/employee")
    public ResponseEntity<List<SqlEmployee>> getAllSqlEmployee(){
        return ResponseEntity.ok(sqlEmployeeService.getAllEmployee());
    }

    @GetMapping("/mongo/employee")
    public ResponseEntity<List<MongoEmployee>> getAllMongoEmployee(){
        return ResponseEntity.ok(mongoEmployeeService.getAllEmployee());
    }

    @GetMapping("/merge/employee")
    public ResponseEntity<List<MergePerson>> getAllMergeEmployee(){
        return ResponseEntity.ok(mergeService.mergeAllPerson());
    }
}
