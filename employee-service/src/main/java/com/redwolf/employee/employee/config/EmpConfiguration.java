package com.redwolf.employee.employee.config;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.redwolf.employee.employee.model.Employee;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;





@Configuration
@EnableJpaRepositories
public class EmpConfiguration {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        instance.getConfig().addMapConfig(new MapConfig("stats").setTimeToLiveSeconds(60));
        return instance;
    }

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig cfg = ClientConfig.load();
        cfg.setClusterName("statsCluster");
        return cfg;
    }
/*  @Bean
    public Employee getEmpBean(){
      return  Employee.builder().age(2).name("ANTONY").build();
  }*/

}
