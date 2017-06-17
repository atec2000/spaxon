package com.spaxon.commandside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpaxonCommandSideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaxonCommandSideApplication.class, args);
	}
}
