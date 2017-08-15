package com.spaxon.commonthings.events;

import java.io.Serializable;

public abstract class AbstractEvent implements Serializable {

	private static final long serialVersionUID = -457088282284293519L;
	
	private String id;

    public AbstractEvent() {}

    public AbstractEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
