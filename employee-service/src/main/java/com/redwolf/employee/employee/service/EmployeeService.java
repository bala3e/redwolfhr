package com.redwolf.employee.employee.service;

import com.redwolf.employee.employee.model.Employee;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

@Slf4j
public class EmployeeService {

    private final WebClient.Builder webClientBuilder;

    private final RestTemplate restTemplate;
    private final ObservationRegistry observationRegistry;


public String employeeLeaveCheck(Employee e){


    String baseUrl= "http://leave-service";
   // String res =restTemplate.getForObject(baseUrl+"/api/leave",String.class);


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

   // return  "";
    });
    }
}
