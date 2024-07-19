/*
 * Crée par Etienne KOA, le 02/06/24.
 */

package etienne.springframework.sdjpa_intro;


import etienne.springframework.sdjpa_intro.domain.Book;
import etienne.springframework.sdjpa_intro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"etienne.springframework.sdjpa_intro.bootstrap"})
public class SpringBootJpaTestSlice {

	@Autowired
	BookRepository bookRepository;

	@Commit
	@Order(1)
	@Test
	void testJpaTestSplice() {
		long countBefore = bookRepository.count();
		assertThat(countBefore).isEqualTo(2);

		bookRepository.save(new Book("My Book", "1235555", "Self"));

		long countAfter = bookRepository.count();

		assertThat(countBefore).isLessThan(countAfter);
	}

	@Order(2)
	@Test
	void testJpaTestSpliceTransaction() {
		long countBefore = bookRepository.count();
		assertThat(countBefore).isEqualTo(3);

	}
}