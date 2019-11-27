package com.fy.cq.ssq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SsqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsqApplication.class, args);
    }

}
