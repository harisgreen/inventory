package com.softbiz.inventory.repository;

import com.softbiz.inventory.model.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by haris on 8/15/2018.
 */
public interface InventoryRepo extends PagingAndSortingRepository<Inventory, Long> {

    @Query(value = "SELECT oi.id, oi.product_id, oi.comments, oi.received_stock, oi.issued_stock, oi.last_touched, " +
            "(SELECT SUM(ii.received_stock) - SUM(ii.issued_stock) FROM Inventory ii where ii.product_id = oi.product_id) " +
            "AS inventory_available FROM Inventory oi ORDER BY oi.ID DESC ", nativeQuery = true)
    List<Inventory> findAllWithAvailableInventory();
}
