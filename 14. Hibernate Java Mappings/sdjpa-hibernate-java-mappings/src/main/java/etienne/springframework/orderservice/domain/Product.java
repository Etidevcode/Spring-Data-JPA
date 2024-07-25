/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Entity
public class Product extends BaseEntity {

	private String description;

	@Enumerated(EnumType.STRING)
	private ProductStatus productStatus;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductStatus getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product product)) return false;
		if (!super.equals(o)) return false;

		return Objects.equals(description, product.description) && productStatus == product.productStatus;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(description);
		result = 31 * result + Objects.hashCode(productStatus);
		return result;
	}
}
