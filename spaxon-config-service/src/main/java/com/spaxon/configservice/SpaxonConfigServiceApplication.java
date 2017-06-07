package com.spaxon.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpaxonConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaxonConfigServiceApplication.class, args);
	}
}
