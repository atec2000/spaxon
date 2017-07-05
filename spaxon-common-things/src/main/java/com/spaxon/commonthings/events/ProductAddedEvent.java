package com.spaxon.commonthings.events;

import java.util.Set;

import com.spaxon.commonthings.domain.ProductImage;

public class ProductAddedEvent extends AbstractEvent {
	
	private String name;
    private boolean saleable = false;
	private Set<ProductImage> productImages;

    public ProductAddedEvent() {
    }

    public ProductAddedEvent(String id, String name, boolean saleable, Set<ProductImage> productImages) {
        super(id);
        this.name = name;
        this.saleable = saleable;
        this.productImages = productImages;
    }

    public String getName() {
		return name;
	}

	public boolean getSaleable() {
		return saleable;
	}
	
    public Set<ProductImage> getProductImages() {
		return productImages;
	}

}
