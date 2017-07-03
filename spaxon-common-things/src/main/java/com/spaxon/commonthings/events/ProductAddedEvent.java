package com.spaxon.commonthings.events;


public class ProductAddedEvent extends AbstractEvent {
	
	private String name;
    private boolean saleable = false;

    public ProductAddedEvent() {
    }

    public ProductAddedEvent(String id, String name, boolean saleable) {
        super(id);
        this.name = name;
        this.saleable = saleable;
    }

    public String getName() {
		return name;
	}

	public boolean getSaleable() {
		return saleable;
	}

}
