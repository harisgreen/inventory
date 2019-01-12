package vita.house.inventory.repository;

import vita.house.inventory.model.SaleOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by haris on 8/19/2018.
 */
public interface SaleOrderRepo extends PagingAndSortingRepository<SaleOrder, Long> {

}
