package com.duo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.duo.gate.Myfilter;

@EnableZuulProxy
@SpringCloudApplication
public class ZuulApplication {

	@Bean
	public Myfilter myfilter(){
		return new Myfilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
