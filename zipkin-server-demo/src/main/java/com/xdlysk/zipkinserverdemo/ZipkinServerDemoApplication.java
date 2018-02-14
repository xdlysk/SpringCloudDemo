package com.xdlysk.zipkinserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@EnableZipkinStreamServer
@SpringBootApplication
public class ZipkinServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerDemoApplication.class, args);
	}
}
