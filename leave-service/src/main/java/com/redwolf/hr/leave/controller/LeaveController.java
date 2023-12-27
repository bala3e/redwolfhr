package com.redwolf.hr.leave.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    @GetMapping
    @ResponseBody
    public String getLeave(){
        return "Hello";
    }
}
