package com.redwolf.employee.employee.controller;

import com.redwolf.employee.employee.entity.Employee;
import com.redwolf.employee.employee.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;
    @GetMapping(value = "/date")
    public String helloEmp() {
        return "Welcome to employee service " + new Date();
    }

  @PostMapping(value = "/addemp")
    public String addEmployee(@RequestBody Employee e) {
        return   empService.employeeLeaveCheck(e);
    }

    @PostMapping(value = "/save")
    public Employee saveEmployee(@RequestBody Employee e){
        return empService.save(e);
    }

    @GetMapping(value = "/getallemp")
    public List<Employee> getAllEmployee(){
        return  empService.getUserStats();
    }

    @GetMapping(value = "/getemp/{ID}")
    public Employee getEmployee(@PathVariable Integer ID){
        return  empService.getEmployee(ID);
    }


    @GetMapping(value = "/leave")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody Employee e) {
        return CompletableFuture.supplyAsync(() -> empService.calAsync(e));
    }
    public CompletableFuture<String> fallbackMethod( Employee e, RuntimeException runtimeException) {
       // log.info("Cannot Place Order Executing Fallback logic");
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
    }


}

