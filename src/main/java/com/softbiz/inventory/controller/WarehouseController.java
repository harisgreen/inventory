package com.softbiz.inventory.controller;

import com.softbiz.inventory.model.Warehouse;
import com.softbiz.inventory.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haris on 8/18/2018.
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Warehouse saveWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<Warehouse> listWarehouses() {
        return warehouseService.findAll();
    }
}
