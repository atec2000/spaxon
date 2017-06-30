package com.spaxon.commandside.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.spaxon.commandside.aggregates.Product;

/**
 * Created by Ben on 07/08/2015.
 */
public class AddProductCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final Product product;

    public AddProductCommand(String id, Product product) {
        this.id = id;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
