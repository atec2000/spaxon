package com.spaxon.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpaxonDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaxonDiscoveryServiceApplication.class, args);
	}
}
