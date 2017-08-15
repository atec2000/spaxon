package com.spaxon.commonthings.events;

import java.util.Set;

import com.spaxon.commonthings.domain.LineItem;

public class OrderAddedEvent extends AbstractEvent {
	
	private static final long serialVersionUID = -6120298053780059695L;
	
	private String name;
	private Set<LineItem> lineItems;

    public OrderAddedEvent() {
    }

    public OrderAddedEvent(String id, String name, Set<LineItem> lineItems) {
        super(id);
        this.name = name;
        this.lineItems = lineItems;
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
