/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}