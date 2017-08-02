package com.duo.user.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@SpringBootApplication
@EnableDiscoveryClient   //启用服务注册与发现
@EnableHystrix
public class UserAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserAPIApplication.class, args);
	}

}
