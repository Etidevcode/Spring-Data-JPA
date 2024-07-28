package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Etienne on 28/07/24.
 */
public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
