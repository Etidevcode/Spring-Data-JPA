package etienne.springframework.orderservice.bootstrap;

import etienne.springframework.orderservice.domain.OrderHeader;
import etienne.springframework.orderservice.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Etienne on 30/07/24.
 */
@Service
public class BootstrapOrderService {
    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    public void readOrderData(){
        OrderHeader orderHeader = orderHeaderRepository.findById(55L).get();

        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        });
    }

}
