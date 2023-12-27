package com.redwolf.employee.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @GetMapping
    public String helloEmp(){
        return "Welcome employee at B.VIKRAM-UKG-Jasmine "+new Date();
    }
}
