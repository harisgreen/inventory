package vita.house.inventory.service;

import com.google.common.collect.Lists;
import vita.house.inventory.model.Inventory;
import vita.house.inventory.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haris on 8/15/2018.
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    public List<Inventory> removeInventory(List<Long> inventoryIds, Long warehouseId) {
        for(Long id : inventoryIds) {
            Inventory deleteInventory = inventoryRepo.findById(id).get();
            inventoryRepo.delete(deleteInventory);
        }
        return Lists.newArrayList(inventoryRepo.findAllWithAvailableInventory(warehouseId));
    }

    public List<Inventory> listInventory(Long warehouseId) {return Lists.newArrayList(inventoryRepo.findAllWithAvailableInventory(warehouseId));}
}
