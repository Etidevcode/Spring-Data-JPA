package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.Customer;
import etienne.springframework.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Etienne on 28/07/24.
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    List<OrderHeader> findAllByCustomer(Customer customer);
}
