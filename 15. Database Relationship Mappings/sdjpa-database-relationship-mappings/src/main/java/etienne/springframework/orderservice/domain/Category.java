/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Category extends BaseEntity {

	private String description;

	@ManyToMany
	@JoinTable(name = "product_category",
				joinColumns = @JoinColumn(name = "category_id"),
				inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Category category)) return false;
		if (!super.equals(o)) return false;

		return Objects.equals(description, category.description);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(description);
		return result;
	}
}
