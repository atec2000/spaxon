package com.spaxon.commandside.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Ben on 07/08/2015.
 */
public class AddProductCommand {

    @TargetAggregateIdentifier
    private String id;
	private String name;
    private boolean saleable;

	public AddProductCommand() {
	}

	public AddProductCommand(String id, String name, boolean saleable) {
		this.id = id;
		this.name = name;
		this.saleable = saleable;
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

	public boolean isSaleable() {
		return saleable;
	}

	public void setSaleable(boolean saleable) {
		this.saleable = saleable;
	}
	
}
