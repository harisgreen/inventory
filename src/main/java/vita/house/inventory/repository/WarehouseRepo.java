package vita.house.inventory.repository;

import vita.house.inventory.model.Warehouse;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by haris on 8/18/2018.
 */
public interface WarehouseRepo extends PagingAndSortingRepository<Warehouse, Long> {

}
