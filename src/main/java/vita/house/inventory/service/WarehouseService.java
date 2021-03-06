package vita.house.inventory.service;

import com.google.common.collect.Lists;
import vita.house.inventory.model.Warehouse;
import vita.house.inventory.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haris on 8/18/2018.
 */
@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepo warehouseRepo;

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public List<Warehouse> findAll() {
        return Lists.newArrayList(warehouseRepo.findAll(new Sort(Sort.Direction.DESC, "id")));
    }
}
