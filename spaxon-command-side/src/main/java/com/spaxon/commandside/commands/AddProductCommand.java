package com.spaxon.commandside.commands;

import java.util.Set;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.spaxon.commonthings.domain.ProductImage;

/**
 * Created by Ben on 07/08/2015.
 */
public class AddProductCommand {

    @TargetAggregateIdentifier
    private String id;
	private String name;
    private boolean saleable;
	private Set<ProductImage> productImages;

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
	
    public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}	
	
}
