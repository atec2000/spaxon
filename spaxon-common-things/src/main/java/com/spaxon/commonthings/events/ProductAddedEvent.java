package com.spaxon.commonthings.events;


public class ProductAddedEvent extends AbstractEvent {

	private static final long serialVersionUID = -1518978805840349048L;
	
	private String name;
    private boolean saleable = false;

    public ProductAddedEvent() {
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
