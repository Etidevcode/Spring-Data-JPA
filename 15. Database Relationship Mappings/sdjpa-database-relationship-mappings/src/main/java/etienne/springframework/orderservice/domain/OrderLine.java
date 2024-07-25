/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {

	private Integer quantityOrdered;

	@ManyToOne
	private OrderHeader orderHeader;

	@ManyToOne
	private Product product;

	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderLine orderLine)) return false;
		if (!super.equals(o)) return false;

		return Objects.equals(quantityOrdered, orderLine.quantityOrdered) && Objects.equals(orderHeader, orderLine.orderHeader) && Objects.equals(product, orderLine.product);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(quantityOrdered);
		result = 31 * result + Objects.hashCode(product);
		return result;
	}
}
