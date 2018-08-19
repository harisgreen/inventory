package com.softbiz.inventory.controller;

import com.softbiz.inventory.model.SaleOrder;
import com.softbiz.inventory.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haris on 8/19/2018.
 */
@RestController
@RequestMapping("/sale/order")
public class SaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SaleOrder createSaleOrder(@RequestBody SaleOrder saleOrder) {
        SaleOrder saleOrder1 = saleOrderService.createSaleOrder(saleOrder);
        return saleOrder1;
    }

    @RequestMapping("/list")
    public List<SaleOrder> listSaleOrders() {
        return saleOrderService.listSaleOrders();
    }
}
