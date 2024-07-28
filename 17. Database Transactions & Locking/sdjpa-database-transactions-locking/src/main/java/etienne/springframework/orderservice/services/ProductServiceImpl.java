/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.services;

import etienne.springframework.orderservice.domain.Product;
import etienne.springframework.orderservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}


	@Override
	public Product savedProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Transactional
	@Override
	public Product updateQOH(Long id, Integer quantityOnHand) {
		Product product = productRepository.findById(id)
				.orElseThrow();

		product.setQuantityOnHand(quantityOnHand);

		return productRepository.saveAndFlush(product);
	}
}