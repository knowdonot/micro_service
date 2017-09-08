package com.duo.ui;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by Ace on 2017/5/27.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UIBootstrap {
    public static void main(String[] args) {
    	SpringApplication.run(UIBootstrap.class, args);
    }
}
