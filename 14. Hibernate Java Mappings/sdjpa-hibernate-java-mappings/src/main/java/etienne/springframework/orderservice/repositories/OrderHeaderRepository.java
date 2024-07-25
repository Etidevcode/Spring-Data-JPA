package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 25/07/24.
 */
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
