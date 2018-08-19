package com.softbiz.inventory.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by haris on 8/19/2018.
 */
@Entity
public class SaleOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String orderNum;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleOrderProduct> orderProductList;

    public SaleOrder() {}

    public List<SaleOrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<SaleOrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
