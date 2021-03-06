package vita.house.inventory.service;

import com.google.common.collect.Lists;
import vita.house.inventory.model.SaleOrder;
import vita.house.inventory.repository.SaleOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haris on 8/19/2018.
 */
@Service
public class SaleOrderService {

    @Autowired
    private SaleOrderRepo saleOrderRepo;

    public SaleOrder createSaleOrder(SaleOrder saleOrder) {
        saleOrder = saleOrderRepo.save(saleOrder);
        return saleOrder;
    }

    public List<SaleOrder> listSaleOrders() {
        return Lists.newArrayList(saleOrderRepo.findAll(new Sort(Sort.Direction.DESC, "id")));
    }
}
