/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}
