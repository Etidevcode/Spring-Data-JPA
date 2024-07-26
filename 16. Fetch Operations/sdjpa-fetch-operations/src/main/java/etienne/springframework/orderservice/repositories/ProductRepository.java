package etienne.springframework.orderservice.repositories;

import etienne.springframework.orderservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Etienne on 26/07/24.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByDescription(String description);
}
