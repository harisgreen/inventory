package com.softbiz.inventory.service;

import com.google.common.collect.Lists;
import com.softbiz.inventory.model.Inventory;
import com.softbiz.inventory.model.Product;
import com.softbiz.inventory.model.Warehouse;
import com.softbiz.inventory.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haris on 8/14/2018.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private InventoryService inventoryService;

    public Product createProduct(Product product, Long warehouseId) {
        boolean newProdut = false;
        if(product.getId() == null) newProdut = true;
        Product product1 = productRepo.save(product);
        if(newProdut) {
            Inventory inventory = new Inventory();
            inventory.setReceivedStock(product.getInventoryAmt());
            inventory.setIssuedStock(0L);
            inventory.setProduct(product1);
            Warehouse wh = new Warehouse();
            wh.setId(warehouseId);
            inventory.setWarehouse(wh);
            inventoryService.createInventory(inventory);
        }
        return product1;
    }

    public List<Product> findAll() { return Lists.newArrayList(productRepo.findAll(new Sort(Sort.Direction.DESC, "id"))); }

    public List<Product> findAllWithInventory(Long warehouseId) {return productRepo.findAllWithInventory(warehouseId); }
}
