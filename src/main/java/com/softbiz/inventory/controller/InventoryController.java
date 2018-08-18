package com.softbiz.inventory.controller;

import com.softbiz.inventory.model.Inventory;
import com.softbiz.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by haris on 8/15/2018.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    @RequestMapping("/list")
    public List<Inventory> listInventories(@PathParam("warehouseId") Long warehouseId) {
        return inventoryService.listInventory(warehouseId);
    }
}
