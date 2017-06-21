package com.spaxon.commonthings.events;


public class ProductSaleableEvent extends AbstractEvent {

    public ProductSaleableEvent() {
    }

    public ProductSaleableEvent(String id) {
        super(id);
    }
}
