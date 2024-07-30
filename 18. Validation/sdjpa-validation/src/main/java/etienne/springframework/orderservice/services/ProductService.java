package etienne.springframework.orderservice.services;

import etienne.springframework.orderservice.domain.Product;

/**
 * Created by Etienne on 30/07/24.
 */
public interface ProductService {

    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
}
