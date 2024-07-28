/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.services;

import etienne.springframework.orderservice.domain.Product;

public interface ProductService {

	Product savedProduct(Product product);

	Product updateQOH(Long id, Integer quantityOnHand);
}
