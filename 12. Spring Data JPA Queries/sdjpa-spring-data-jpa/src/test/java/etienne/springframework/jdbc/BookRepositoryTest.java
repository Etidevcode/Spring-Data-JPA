/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc;

import etienne.springframework.jdbc.domain.Book;
import etienne.springframework.jdbc.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"etienne.springframework.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	BookRepository bookRepository;

	@Test
	void testBookJPANamedQuery() {
		Book book = bookRepository.findBookByTitleWithQueryNamed("Clean Code");
		assertThat(book).isNotNull();
	}

	@Test
	void testBookQueryNative() {
		Book book = bookRepository.findBookByTitleNativeQuery("Clean Code");
		assertThat(book).isNotNull();
	}

	@Test
	void testBookQueryNamed() {
		Book book = bookRepository.findBookByTitleWithQueryNamed("Clean Code");
		assertThat(book).isNotNull();
	}

	@Test
	void testBookQuery() {
		Book book = bookRepository.findBookByTitleWithQuery("Clean Code");

		assertThat(book).isNotNull();
	}

	@Test
	void testBookFuture() throws ExecutionException, InterruptedException {

		Future<Book> bookFuture = bookRepository.queryByTitle("Clean Code");

		Book book = bookFuture.get();

		assertNotNull(book);
	}

	@Test
	void testBookStream() {
		AtomicInteger count = new AtomicInteger();

		bookRepository.findAllByTitleNotNull().forEach(
				book -> {
					count.incrementAndGet();
				});
		assertThat(count.get()).isGreaterThan(5);
	}

	@Test
	void testEmptyResultException() {
		assertThrows(EmptyResultDataAccessException.class, () -> {
			Book book = bookRepository.readByTitle("foobar4");
		});
	}

	@Test
	void testNullParam() {
		assertNull(bookRepository.getByTitle(null));
	}

	@Test
	void testNotException() {
		assertNull(bookRepository.getByTitle("foo"));
	}
}
