package com.spaxon.queryside.handlers;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spaxon.commonthings.events.OrderAddedEvent;

/**
 * Created by Ben on 10/08/2015.
 */
@Component
public class ProductViewEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ProductViewEventHandler.class);

    //@Autowired
    //private ProductRepository productRepository;

    @EventHandler
    public void handle(OrderAddedEvent event) {
        LOG.info("-=-=-= =-=-=- ProductAddedEvent: [{}] '{}'", event.getId(), event.getName());
        //productRepository.save(new Product(event.getId(), event.getName(), false));
    }

}
