package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(EurekaServerApp.class);
    }
}
