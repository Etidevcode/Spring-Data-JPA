/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.orderservice.bootstrap;

import etienne.springframework.orderservice.domain.Customer;
import etienne.springframework.orderservice.domain.Product;
import etienne.springframework.orderservice.domain.ProductStatus;
import etienne.springframework.orderservice.repositories.CustomerRepository;
import etienne.springframework.orderservice.repositories.OrderHeaderRepository;
import etienne.springframework.orderservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	BootstrapOrderService bootstrapOrderService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductService productService;

	private void updateProduct() {

		Product product = new Product();
		product.setDescription("My Product");
		product.setProductStatus(ProductStatus.NEW);

		Product savedProduct = productService.savedProduct(product);

		Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

		System.out.println("Updated Qty: " + savedProduct2.getQuantityOnHand());
	}

	@Override
	public void run(String... args) throws Exception {

		updateProduct();

		bootstrapOrderService.readOrderData();
		Customer customer = new Customer();

		customer.setCustomerName("Testing Version");
		Customer savedCustomer = customerRepository.save(customer);
		System.out.println("Version is: " + savedCustomer.getVersion());

		savedCustomer.setCustomerName("Testing Version 2");
		Customer savedCustomer2 = customerRepository.save(customer);
		System.out.println("Version is: " + savedCustomer2.getVersion());

		savedCustomer2.setCustomerName("Testing Version 3");
		Customer savedCustomer3	 = customerRepository.save(savedCustomer2);
		System.out.println("Version is: " + savedCustomer3.getVersion());

		customerRepository.delete(savedCustomer3);
	}
}
