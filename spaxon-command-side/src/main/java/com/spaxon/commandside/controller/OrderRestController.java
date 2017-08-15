package com.spaxon.commandside.controller;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spaxon.commandside.commands.AddOrderCommand;
import com.spaxon.commonthings.utils.Asserts;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by ben on 19/01/16.
 */
@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    CommandGateway commandGateway;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody AddOrderCommand command, HttpServletResponse response) {
    	
        LOG.debug("Adding Order [{}]", command.getName());
    	String orderId = UUID.randomUUID().toString();
        LOG.debug("orderId: {}", orderId);
    	String name = command.getName();

        try {
            Asserts.INSTANCE.areNotEmpty(Arrays.asList(orderId, name));
            command.setId(orderId);

            commandGateway.sendAndWait(command);
            LOG.info("Added Order [{}] '{}'", orderId, name);
            response.setStatus(HttpServletResponse.SC_CREATED);// Set up the 201 CREATED response
            return;
        } catch (AssertionError ae) {
            LOG.warn("Add Request failed - empty params?. [{}] '{}'", orderId, name);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (CommandExecutionException cex) {
            LOG.warn("Add Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOG.warn("A duplicate product with the same ID [{}] already exists.", orderId);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }
    
}
