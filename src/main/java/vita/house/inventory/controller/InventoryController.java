package vita.house.inventory.controller;

import vita.house.inventory.model.Inventory;
import vita.house.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public List<Inventory> removeInventory(@RequestBody List<Long> ids,
                                           @PathParam("warehouseId") Long warehouseId) {
        List<Inventory> inventories = inventoryService.removeInventory(ids, warehouseId);
        return inventories;
    }

    @RequestMapping("/list")
    public List<Inventory> listInventories(@PathParam("warehouseId") Long warehouseId) {
        return inventoryService.listInventory(warehouseId);
    }
}
