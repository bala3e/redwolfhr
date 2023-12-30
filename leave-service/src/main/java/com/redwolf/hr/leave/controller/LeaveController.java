package com.redwolf.hr.leave.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/leave")

public class LeaveController {
    @GetMapping
    @ResponseBody
    public String getLeave(){
        System.out.println("Calling -----------------");

        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "Hello "+new Date();
    }
}
