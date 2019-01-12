package vita.house.inventory.model;

import javax.persistence.*;

/**
 * Created by haris on 8/14/2018.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String productName;
    private Long inventoryAmt;
    private Long price;

    public Product() {}

    public Product(String productName, Long inventoryAmt) {
        this.productName = productName;
        this.inventoryAmt = inventoryAmt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventoryAmt() {
        return inventoryAmt;
    }

    public void setInventoryAmt(Long inventoryAmt) {
        this.inventoryAmt = inventoryAmt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
