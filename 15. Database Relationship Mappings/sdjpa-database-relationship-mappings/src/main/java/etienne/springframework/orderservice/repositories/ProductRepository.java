/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByDescription(String description);
}
