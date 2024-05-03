package com.mrthinkj.integratemiddlewareapplication.controller;

import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import com.mrthinkj.integratemiddlewareapplication.service.MergeService;
import com.mrthinkj.integratemiddlewareapplication.service.SocketService;
import com.mrthinkj.integratemiddlewareapplication.service.SqlEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql")
@AllArgsConstructor
public class MSSQLEmployeeController {
    SqlEmployeeService sqlEmployeeService;
    MergeService mergeService;
    SocketService socketService;

    @PostMapping
    public ResponseEntity<Boolean> createNewEmployee(@RequestBody SqlEmployee sqlEmployee){
        try {
            sqlEmployeeService.createNewEmployee(sqlEmployee);
            socketService.sendToTopic("topic/public", mergeService.mergeAllPerson());
            return ResponseEntity.ok(true);
        } catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteEmployee(@RequestBody SqlEmployee sqlEmployee){
        boolean success = sqlEmployeeService.deleteEmployeeByFirstNameAndLastName(sqlEmployee.getFirstName(), sqlEmployee.getLastName());
        if (success)
            socketService.sendToTopic("topic/public", mergeService.mergeAllPerson());
        return ResponseEntity.ok(success);
    }
}
