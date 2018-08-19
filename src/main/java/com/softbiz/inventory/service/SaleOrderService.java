package com.softbiz.inventory.service;

import com.google.common.collect.Lists;
import com.softbiz.inventory.model.SaleOrder;
import com.softbiz.inventory.repository.SaleOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haris on 8/19/2018.
 */
@Service
public class SaleOrderService {

    @Autowired
    private SaleOrderRepo saleOrderRepo;

    public SaleOrder createSaleOrder(SaleOrder saleOrder) {
        saleOrder = saleOrderRepo.save(saleOrder);
        return saleOrder;
    }

    public List<SaleOrder> listSaleOrders() {
        return Lists.newArrayList(saleOrderRepo.findAll());
    }
}
