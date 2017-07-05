package com.spaxon.commandside.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spaxon.commandside.aggregates.Product;

@Entity
public class ProductImage {

	private Long id;
	private Product product;
    private String name;
	private String url;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
    @ManyToOne
    @JoinColumn(name = "product_id")	
    public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	    
}
