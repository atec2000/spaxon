package com.spaxon.commandside.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.spaxon.commandside.commands.AddProductCommand;
import com.spaxon.commandside.commands.MarkProductAsSaleableCommand;
import com.spaxon.commandside.commands.MarkProductAsUnsaleableCommand;
import com.spaxon.commandside.domain.Category;
import com.spaxon.commandside.domain.ProductImage;
import com.spaxon.commonthings.events.ProductAddedEvent;
import com.spaxon.commonthings.events.ProductSaleableEvent;
import com.spaxon.commonthings.events.ProductUnsaleableEvent;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import java.util.Set;


/**
 * ProductAggregate is essentially a DDD AggregateRoot (from the DDD concept). In event-sourced
 * systems, Aggregates are often stored and retreived using a 'Repository'. In the
 * simplest terms, Aggregates are the sum of their applied 'Events'.
 * <p/>
 * The Repository stores the aggregate's Events in an 'Event Store'. When an Aggregate
 * is re-loaded by the repository, the Repository re-applies all the stored events
 * to the aggregate thereby re-creating the logical state of the Aggregate.
 * <p/>
 * The ProductAggregate Aggregate can handle and react to 'Commands', and when it reacts
 * to these com.soagrowers.product.commands it creates and 'applies' Events that represent the logical changes
 * to be made. These Events are also handled by the ProductAggregate.
 * <p/>
 * Axon takes care of much of this via the CommandBus, EventBus and Repository.
 * <p/>
 * Axon delivers com.soagrowers.product.commands placed on the bus to the Aggregate. Axon supports the 'applying' of
 * Events to the Aggregate, and the handling of those events by the aggregate or any other
 * configured EventHandlers.
 */
@Aggregate(repository="productRepository")
@Entity
public class Product {

	private static final Logger LOG = LoggerFactory.getLogger(Product.class);

    /**
     * Aggregates that are managed by Axon must have a unique identifier.
     * Strategies similar to GUID are often used. The annotation 'AggregateIdentifier'
     * identifies the id field as such.
     */
    @AggregateIdentifier
    private String id;
	private String name;
    private boolean saleable = false;
    /*
    @AggregateMember
	private List<Category> categories;
    */

    //@AggregateMember
    //private Set<ProductImage> productImages;

	/**
     * This default constructor is used by the Repository to construct
     * a prototype ProductAggregate. Events are then used to set properties
     * such as the ProductAggregate's Id in order to make the Aggregate reflect
     * it's true logical state.
     */
    public Product() {
    }

    /**
     * This constructor is marked as a 'CommandHandler' for the
     * AddProductCommand. This command can be used to construct
     * new instances of the Aggregate. If successful a new ProductAddedEvent
     * is 'applied' to the aggregate using the Axon 'apply' method. The apply
     * method appears to also propagate the Event to any other registered
     * 'Event Listeners', who may take further action.
     *
     * @param command
     */
    @CommandHandler
    public Product(AddProductCommand command) {
        LOG.debug("Command: 'AddProductCommand' received.");
        LOG.debug("Queuing up a new ProductAddedEvent for product '{}'", command.getId());
        
        //command.getProduct().getId();
        ProductAddedEvent productAddedEvent = 
        		new ProductAddedEvent(command.getId(), command.getName(), command.getSaleable());
		//BeanUtils.copyProperties(command, productAddedEvent);		
        LOG.debug("productAddedEvent.getId(): {}", productAddedEvent.getId());

        apply(productAddedEvent);
    }

    @CommandHandler
    public void markSaleable(MarkProductAsSaleableCommand command) {
        LOG.debug("Command: 'MarkProductAsSaleableCommand' received.");
        if (!this.getSaleable()) {
            apply(new ProductSaleableEvent(id));
        } else {
            throw new IllegalStateException("This ProductAggregate (" + this.getId() + ") is already Saleable.");
        }
    }

    @CommandHandler
    public void markUnsaleable(MarkProductAsUnsaleableCommand command) {
        LOG.debug("Command: 'MarkProductAsUnsaleableCommand' received.");
        if (this.getSaleable()) {
            apply(new ProductUnsaleableEvent(id));
        } else {
            throw new IllegalStateException("This ProductAggregate (" + this.getId() + ") is already off-sale.");
        }
    }

    /**
     * This method is marked as an EventSourcingHandler and is therefore used by the Axon framework to
     * handle events of the specified type (ProductAddedEvent). The ProductAddedEvent can be
     * raised either by the constructor during ProductAggregate(AddProductCommand) or by the
     * Repository when 're-loading' the aggregate.
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(ProductAddedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.saleable = event.getSaleable();
        LOG.debug("Applied: 'ProductAddedEvent' [{}] '{}'", event.getId(), event.getName());
    }

    @EventSourcingHandler
    public void on(ProductSaleableEvent event) {
        this.saleable = true;
        LOG.debug("Applied: 'ProductSaleableEvent' [{}]", event.getId());
    }

    @EventSourcingHandler
    public void on(ProductUnsaleableEvent event) {
        this.saleable = false;
        LOG.debug("Applied: 'ProductUnsaleableEvent' [{}]", event.getId());
    }

    @Id
    public String getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    public boolean getSaleable() {
        return saleable;
    }
    
    public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSaleable(boolean saleable) {
		this.saleable = saleable;
	}    
    
	/*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_image", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))	
	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}
	*/

	/*
    public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	*/

}
