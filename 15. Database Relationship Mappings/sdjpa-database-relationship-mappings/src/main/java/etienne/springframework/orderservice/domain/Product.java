/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Product extends BaseEntity {

	private String description;

	@Enumerated(EnumType.STRING)
	private ProductStatus productStatus;

	@ManyToMany
	@JoinTable(name = "product_category",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
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
