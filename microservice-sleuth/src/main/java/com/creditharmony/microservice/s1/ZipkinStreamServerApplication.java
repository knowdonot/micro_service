package com.creditharmony.microservice.s1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * 日志跟踪服务
 * 
 * @Class Name ZipkinStreamServerApplication
 * @author wangduo
 * @Create In 2017年6月9日
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinStreamServerApplication {
	/**
	 * 入口 2017年6月9日 By duoduo
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new SpringApplicationBuilder(ZipkinStreamServerApplication.class).web(
				true).run(args);
	}


}
