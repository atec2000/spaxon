package com.spaxon.commandside.commands;

import java.util.Set;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.spaxon.commonthings.domain.LineItem;

/**
 * Created by Ben on 07/08/2015.
 */
public class AddOrderCommand {

    @TargetAggregateIdentifier
    private String id;
	private String name;
	private Set<LineItem> lineItems;

	public AddOrderCommand() {
	}

	public AddOrderCommand(String id, String name, Set<LineItem> lineItems) {
		this.id = id;
		this.name = name;
		this.lineItems = lineItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
}
