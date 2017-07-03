package com.spaxon.commandside.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Ben on 07/08/2015.
 */
public class AddProductCommand {

    @TargetAggregateIdentifier
    private final String id;
	private final String name;
    private final boolean saleable;

	public AddProductCommand(String id, String name, boolean saleable) {
		this.id = id;
		this.name = name;
		this.saleable = saleable;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean getSaleable() {
		return saleable;
	}
	
}
