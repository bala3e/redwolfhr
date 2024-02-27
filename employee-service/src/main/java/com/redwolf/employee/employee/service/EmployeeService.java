package com.redwolf.employee.employee.service;


import com.redwolf.employee.employee.entity.Employee;
import com.redwolf.employee.employee.repository.EmployeeRepo;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor

@Slf4j
public class EmployeeService {
    private final Random random = new Random();
    private final WebClient.Builder webClientBuilder;

    private final RestTemplate restTemplate;
    private final ObservationRegistry observationRegistry;

    @Autowired
    private EmployeeRepo employeeRepo;


public String employeeLeaveCheck(Employee e){


    String baseUrl= "http://leave-service";
   // String res =restTemplate.getForObject(baseUrl+"/api/leave",String.class);

    //restTemplate.getForEntity()
  //  restTemplate.getForObject();
   // restTemplate.exchange();
   // restTemplate.


    String res = webClientBuilder.baseUrl(baseUrl)
            .build()
            .get()
            .uri("/api/leave")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class).block();

    return   res;
}


    public String calAsync(Employee e) {
        Observation inventoryServiceObservation = Observation.createNotStarted("employee-service-lookup",
                this.observationRegistry);
        inventoryServiceObservation.lowCardinalityKeyValue("call", "employee-service");
        String baseUrl= "http://leave-service";
        return inventoryServiceObservation.observe(() -> {
           String s= webClientBuilder.baseUrl(baseUrl)
                    .build()
                    .get()
                    .uri("/api/leave")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class).block();
           return s;
    });
    }




    @Cacheable(cacheNames = "stats")
    public List<Employee> getUserStats() {
           return getRequestsCountFromDb();
    }


    private List<Employee> getRequestsCountFromDb() {
        System.out.println("calling ");
          List<Employee> emplist=employeeRepo.findAll();
         return emplist;
    }

    @CacheEvict(allEntries = true,cacheNames = "stats")
    public Employee save(Employee employee){

        return employeeRepo.save(employee);
    }

    public Employee getEmployee(Integer ID){
       return employeeRepo.findById(ID).orElseThrow(() -> new EntityNotFoundException(String.valueOf(ID)));
    }
}
