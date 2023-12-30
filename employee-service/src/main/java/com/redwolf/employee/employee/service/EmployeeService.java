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


public String employeeLeaveCheck(Employee e){

   /* WebClient webClient = WebClient.builder()
            .baseUrl("http://leave-service/api/leave")
            .defaultCookie("cookie-name", "cookie-value")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
*/
    String baseUrl= "http://leave-service";
   // String baseUrl= "http://localhost:59515";
/*
    WebClient client = WebClient.create(baseUrl);
    Mono<String> mono= client
            .get()
            .uri("/api/leave")
            .retrieve()
            .bodyToMono(String.class);
*/
  //String res=  webClientBuilder.build().get().uri(baseUrl+"/api/leave").
    //      retrieve().bodyToMono(String.class).block();


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


}
