package etienne.springframework.sdjpa_intro;

import etienne.springframework.sdjpa_intro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SdjpaIntroApplicationTests {

	@Autowired
	BookRepository bookRepository;


	@Test
	void testBookRepository() {

		long count = bookRepository.count();
		assertThat(count).isGreaterThan(0);
	}


	@Test
	void contextLoads() {
	}

}
