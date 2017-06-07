package com.spaxon.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.spaxon.gatewayservice.prefilters.SimpleLoggingPreFilter;

@EnableZuulProxy //Acts as reverse proxy, forwarding requests to other services based on routes.
@SpringBootApplication
public class SpaxonGatewayServiceApplication {

	@Bean
    public SimpleLoggingPreFilter simplePreFilter() {
        return new SimpleLoggingPreFilter();
    }

	public static void main(String[] args) {
		SpringApplication.run(SpaxonGatewayServiceApplication.class, args);
	}
}
