package com.spaxon.commandside.reqvo;

public class AddProductReqVo {

    private String id;
	private String name;
    private boolean saleable;

	public AddProductReqVo() {
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSaleable(boolean saleable) {
		this.saleable = saleable;
	}

	public boolean getSaleable() {
		return saleable;
	}
	
}
