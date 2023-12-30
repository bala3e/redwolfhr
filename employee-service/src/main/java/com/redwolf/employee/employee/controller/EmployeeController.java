package com.redwolf.employee.employee.controller;

import com.redwolf.employee.employee.model.Employee;
import com.redwolf.employee.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
@Autowired
    private EmployeeService empService;
    @GetMapping
    public String helloEmp() {
        return "Welcome employee at B.VIKRAM-UKG-Jasmine " + new Date();
    }

  @PostMapping
    public String addEmployee(@RequestBody Employee e) {

     return   empService.employeeLeaveCheck(e);
    }
}

