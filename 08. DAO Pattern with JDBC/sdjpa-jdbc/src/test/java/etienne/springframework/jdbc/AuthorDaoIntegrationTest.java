/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc;

import etienne.springframework.jdbc.dao.AuthorDAO;
import etienne.springframework.jdbc.dao.AuthorDAOImpl;
import etienne.springframework.jdbc.dao.BookDAO;
import etienne.springframework.jdbc.dao.BookDAOImpl;
import etienne.springframework.jdbc.domain.Author;
import etienne.springframework.jdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
// @ComponentScan(basePackages = {"etienne.springframework.jdbc.dao"})
@Import({AuthorDAOImpl.class, BookDAOImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

	@Autowired
	AuthorDAO authorDAO;

	@Autowired
	BookDAO bookDao;

	@Test
	void testDeleteBook() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");
		Book saved = bookDao.saveNewBook(book);

		bookDao.deleteBookById(saved.getId());

		Book deleted = bookDao.getById(saved.getId());

		assertThat(deleted).isNull();
	}

	@Test
	void updateBookTest() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");

		Author author = new Author();
		author.setId(3L);

		book.setAuthor(author);
		Book saved = bookDao.saveNewBook(book);

		saved.setTitle("New Book");
		bookDao.updateBook(saved);

		Book fetched = bookDao.getById(saved.getId());

		assertThat(fetched.getTitle()).isEqualTo("New Book");
	}

	@Test
	void testSaveBook() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");

		Author author = new Author();
		author.setId(3L);

		book.setAuthor(author);
		Book saved = bookDao.saveNewBook(book);

		assertThat(saved).isNotNull();
	}

	@Test
	void testGetBookByName() {
		Book book = bookDao.findBookByTitle("Clean Code");

		assertThat(book).isNotNull();
	}

	@Test
	void testGetBook() {
		Book book = bookDao.getById(3L);

		assertThat(book.getId()).isNotNull();
	}

	@Test
	void testDeleteAuthor() {

		Author author = new Author();
		author.setFirstName("john");
		author.setLastName("t");

		Author saved = authorDAO.saveNewAuthor(author);

		authorDAO.deleteAuthorById(saved.getId());

		Author deleted = authorDAO.getById(saved.getId());

		assertThat(deleted).isNull();
	}

	@Test
	void testUpdateAuthor() {
		Author author = new Author();
		author.setFirstName("john");
		author.setLastName("t");

		Author saved = authorDAO.saveNewAuthor(author);

		saved.setLastName("Thompson");

		Author updated = authorDAO.updateAuthor(saved);

		assertThat(updated.getLastName()).isEqualTo("Thompson");
	}

	@Test
	void testSaveAuthor() {

		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Thompson");
		Author saved = authorDAO.saveNewAuthor(author);

		assertThat(saved).isNotNull();
	}

	@Test
	void testGetAuthorByName() {
		Author author = authorDAO.findAuthorByName("Craig", "Walls");

		assertThat(author).isNotNull();
	}

	@Test
	void testGetAuthor() {

		Author author = authorDAO.getById(1L);

		assertThat(author).isNotNull();
	}
}
