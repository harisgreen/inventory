package com.softbiz.inventory.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by haris on 8/15/2018.
 */
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    private Warehouse warehouse;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastTouched;

    private Long inventoryAvailable;
    private Long receivedStock;
    private Long issuedStock;

    @Column(nullable = true)
    private String comments;

    public Inventory() {}

    @PrePersist
    protected void onCreate() {
        if (lastTouched == null) { lastTouched = new Date(); }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getInventoryAvailable() {
        return inventoryAvailable;
    }

    public void setInventoryAvailable(Long inventoryAvailable) {
        this.inventoryAvailable = inventoryAvailable;
    }

    public Long getReceivedStock() {
        return receivedStock;
    }

    public void setReceivedStock(Long receivedStock) {
        this.receivedStock = receivedStock;
    }

    public Long getIssuedStock() {
        return issuedStock;
    }

    public void setIssuedStock(Long issuedStock) {
        this.issuedStock = issuedStock;
    }

    public Date getLastTouched() {
        return lastTouched;
    }

    public void setLastTouched(Date lastTouched) {
        this.lastTouched = lastTouched;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
