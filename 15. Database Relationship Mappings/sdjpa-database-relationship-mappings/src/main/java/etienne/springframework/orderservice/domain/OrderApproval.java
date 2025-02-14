/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class OrderApproval extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "order_header_id")
	private OrderHeader orderHeader;

	private String approvedBy;

	public String getApprovedBy() {
		return approvedBy;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
}
