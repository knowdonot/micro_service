package com.duo;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import zipkin.server.EnableZipkinServer;
import zipkin.storage.mysql.MySQLStorage;


//@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class SleuthApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SleuthApplication.class).web(true).run(args);
	}
	
	@Bean
	public MySQLStorage mySqlStorage(DataSource datasource){
		return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
	}
}
