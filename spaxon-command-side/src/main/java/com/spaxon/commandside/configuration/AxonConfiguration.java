package com.spaxon.commandside.configuration;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.spaxon.commandside.aggregates.UserOrder;

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
	
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean
    public TransactionManager axonTransactionManager() {
        return new SpringTransactionManager(transactionManager);
    }
    
    @Bean
    public EventBus eventBus(){
        return new SimpleEventBus();
    }
    
    @Bean
    CommandBus commandBus(TransactionManager transactionManager) {
        SimpleCommandBus commandBus = new SimpleCommandBus(transactionManager, NoOpMessageMonitor.INSTANCE);
        commandBus.registerDispatchInterceptor(new BeanValidationInterceptor());
        return commandBus;
    }
    
    @Bean
    public TransactionManagingInterceptor transactionManagingInterceptor(){
        return new TransactionManagingInterceptor(new SpringTransactionManager(transactionManager));
    }
    
    @Bean
	public EntityManagerProvider entityManagerProvider() {
		return new ContainerManagedEntityManagerProvider();
	}    
    
	@Bean
    public Repository<UserOrder> orderRepository() {
        return new GenericJpaRepository<UserOrder>(entityManagerProvider(), UserOrder.class, eventBus());
    }    
    
}
