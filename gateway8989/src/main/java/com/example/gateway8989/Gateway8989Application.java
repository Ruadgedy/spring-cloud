package com.example.gateway8989;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway8989Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway8989Application.class, args);
    }

}
