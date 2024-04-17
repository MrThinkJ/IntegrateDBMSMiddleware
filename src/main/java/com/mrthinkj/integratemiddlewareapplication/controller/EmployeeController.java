package com.mrthinkj.integratemiddlewareapplication.controller;

import com.mrthinkj.integratemiddlewareapplication.model.MergePerson;
import com.mrthinkj.integratemiddlewareapplication.model.MongoEmployee;
import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import com.mrthinkj.integratemiddlewareapplication.service.MergeService;
import com.mrthinkj.integratemiddlewareapplication.service.MongoEmployeeService;
import com.mrthinkj.integratemiddlewareapplication.service.SqlEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(
        name = "REST APIs for interacting with 2 DBMS"
)
public class EmployeeController {
    SqlEmployeeService sqlEmployeeService;
    MongoEmployeeService mongoEmployeeService;
    MergeService mergeService;

    public EmployeeController(SqlEmployeeService sqlEmployeeService, MongoEmployeeService mongoEmployeeService, MergeService mergeService) {
        this.sqlEmployeeService = sqlEmployeeService;
        this.mongoEmployeeService = mongoEmployeeService;
        this.mergeService = mergeService;
    }

    @Operation(
        summary = "Get All Employee in SQLServer DB REST API",
        description = "Get All Employee in SQLServer DB REST API is used to retrieve data from DB which is set in application.properties"
    )
    @ApiResponse(
        responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/sql/employee")
    public ResponseEntity<List<SqlEmployee>> getAllSqlEmployee(){
        return ResponseEntity.ok(sqlEmployeeService.getAllEmployee());
    }

    @Operation(
            summary = "Get All Employee in MongoDB DB REST API",
            description = "Get All Employee in MongoDB DB REST API is used to retrieve data from DB which is set in application.properties"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/mongo/employee")
    public ResponseEntity<List<MongoEmployee>> getAllMongoEmployee(){
        return ResponseEntity.ok(mongoEmployeeService.getAllEmployee());
    }

    @Operation(
            summary = "Merge all employee REST API",
            description = "Merge all employee REST API is used to merge all employee in two DBMSs by fistName and lastName column"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/merge/employee")
    public ResponseEntity<List<MergePerson>> getAllMergeEmployee(){
        return ResponseEntity.ok(mergeService.mergeAllPerson());
    }
}
