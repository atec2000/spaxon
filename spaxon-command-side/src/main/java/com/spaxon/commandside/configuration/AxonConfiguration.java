package com.spaxon.commandside.configuration;

import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spaxon.commandside.aggregates.Product;

/**
 * A configuration for axonframework.
 * Axonframework is used to support eventsourcing and CQRS.
 * 
 * @author idugalic
 *
 */
@Configuration
public class AxonConfiguration {
	
    /*
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    
    @Value("${spring.application.queue}")
    private String queueName;

    @Value("${spring.application.exchange}")
    private String exchangeName;

    @Value("${spring.application.databaseName}")
    private String databaseName;

    @Value("${spring.application.eventsCollectionName}")
    private String eventsCollectionName;

    @Value("${spring.application.snapshotCollectionName}")
    private String snapshotCollectionName;
    */
    
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AxonConfiguration.class);
	
    @Bean
    public EventBus eventBus(){
        return new SimpleEventBus();
    }
    
    @Bean
	public EntityManagerProvider entityManagerProvider() {
		return new ContainerManagedEntityManagerProvider();
	}    
    
	@Bean
    public Repository<Product> productRepository() {
        return new GenericJpaRepository<Product>(entityManagerProvider(), Product.class, eventBus());
    }    
    
}
