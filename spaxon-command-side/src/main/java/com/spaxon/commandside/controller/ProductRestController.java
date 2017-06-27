package com.spaxon.commandside.controller;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spaxon.commandside.commands.AddProductCommand;
import com.spaxon.commandside.commands.MarkProductAsSaleableCommand;
import com.spaxon.commandside.commands.MarkProductAsUnsaleableCommand;
import com.spaxon.commonthings.utils.Asserts;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by ben on 19/01/16.
 */
@RestController
@RequestMapping("/products")
public class ProductRestController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    CommandGateway commandGateway;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void add(@PathVariable(value = "id") String id,
                    @RequestParam(value = "name", required = true) String name,
                    HttpServletResponse response) {

        LOG.debug("Adding Product [{}] '{}'", id, name);

        try {
            Asserts.INSTANCE.areNotEmpty(Arrays.asList(id, name));
            AddProductCommand command = new AddProductCommand(id, name);
            commandGateway.sendAndWait(command);
            LOG.info("Added Product [{}] '{}'", id, name);
            response.setStatus(HttpServletResponse.SC_CREATED);// Set up the 201 CREATED response
            return;
        } catch (AssertionError ae) {
            LOG.warn("Add Request failed - empty params?. [{}] '{}'", id, name);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (CommandExecutionException cex) {
            LOG.warn("Add Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOG.warn("A duplicate product with the same ID [{}] already exists.", id);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void saleable(@PathVariable(value = "id") String id,
                    @RequestParam(value = "saleable", required = true) Integer saleable,
                    HttpServletResponse response) {

        LOG.debug("Saleabling Product [{}] '{}'", id, saleable);

        try {
            Asserts.INSTANCE.areNotEmpty(Arrays.asList(id, saleable));
            if (saleable.intValue() == 1) {
            	MarkProductAsSaleableCommand command = new MarkProductAsSaleableCommand(id);
                commandGateway.sendAndWait(command);
            } else {
            	MarkProductAsUnsaleableCommand command = new MarkProductAsUnsaleableCommand(id);
                commandGateway.sendAndWait(command);
            }
            
            LOG.info("Saleabled Product [{}] '{}'", id, saleable);
            response.setStatus(HttpServletResponse.SC_OK);// Set up the 200 OK response
            return;
        } catch (AssertionError ae) {
            LOG.warn("Saleabled Request failed - empty params?. [{}] '{}'", id, saleable);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (CommandExecutionException cex) {
            LOG.warn("Saleabled Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
            }
        }
    }    
    
}
