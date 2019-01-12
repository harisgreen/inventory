package com.softbiz.inventory.repository;

import com.softbiz.inventory.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by haris on 8/14/2018.
 */
public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

    @Query(value= "select IFNULL((SUM(i.received_stock) - SUM(i.issued_stock)), 0) as inventory_amt, " +
            " p.* from product p left join inventory i on p.id = i.product_id " +
            " and i.warehouse_id = ?1 group by p.id order by p.id desc", nativeQuery = true)
    List<Product> findAllWithInventory(Long warehouseId);
}
