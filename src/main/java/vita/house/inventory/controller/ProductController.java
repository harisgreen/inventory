package vita.house.inventory.controller;

import vita.house.inventory.model.Product;
import vita.house.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by haris on 8/14/2018.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method= RequestMethod.POST, value = "/save")
    public Product saveProduct(@RequestBody Product product, @PathParam("warehouseId") Long warehouseId) {
        return productService.createProduct(product, warehouseId);
    }

    @RequestMapping(value = "/list")
    public List<Product> listProducts() {
        return productService.findAll();
    }

    @RequestMapping(value = "/list/inventory")
    public List<Product> listProductsWithInventory(@PathParam("warehouseId") Long warehouseId) {
        return productService.findAllWithInventory(warehouseId);
    }
}
