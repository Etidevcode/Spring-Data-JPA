/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc;



import etienne.springframework.jdbc.dao.AuthorDao;
import etienne.springframework.jdbc.dao.BookDao;
import etienne.springframework.jdbc.domain.Author;
import etienne.springframework.jdbc.domain.Book;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"etienne.springframework.jdbc.dao"})
public class DaoIntegrationTest {

	@Autowired
	AuthorDao authorDao;


	@Autowired
	BookDao bookDao;

	@Test
	void testFindAllAuthors() {

		List<Author> authors = authorDao.findAll();

		assertThat(authors).isNotNull();
		assertThat(authors.size()).isGreaterThan(0);
	}

	@Test
	void testFindBookByISBN() {

		Book book = new Book();
		book.setIsbn("1234" + RandomString.make());
		book.setTitle("ISBN TEST");

		Book saved = bookDao.saveNewBook(book);

		Book fetched = bookDao.findByISBN(book.getIsbn());
		assertThat(fetched).isNotNull();
	}

	@Test
	void testListAuthorByLastNameLike() {
		List<Author> authors = authorDao.listAuthorByLastNameLike("Wall");

		assertThat(authors).isNotNull();
		assertThat(authors.size()).isGreaterThan(0);
	}

	@Test
	void testDeleteBook() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");
		Book saved = bookDao.saveNewBook(book);

		bookDao.deleteBookById(saved.getId());

		Book deleted = 	bookDao.getById(saved.getId());
		assertThat(deleted).isNull();

	}

	@Test
	void updateBookTest() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");
		book.setAuthorId(1L);
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
		book.setAuthorId(1L);

		Book saved = bookDao.saveNewBook(book);

		assertThat(saved).isNotNull();
	}

	@Test
	void testGetBookByName() {
		Book book = bookDao.findBookByTitle("Clean Code");

		assertThat(book).isNotNull();
	}

	@Test
	void testGetBookByTitleCriteria() {
		Book book = bookDao.findBookByTitleCriteria("Clean Code");

		assertThat(book).isNotNull();
	}

	@Test
	void testGetBookByNameNative() {
		Book book = bookDao.findBookByTitleNative("Clean Code");

		assertThat(book).isNotNull();
	}

	@Test
	void testGetAuthorByNameNative() {
		Author author = authorDao.findAuthorByNameNative("Craig", "Walls");

		assertThat(author).isNotNull();
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

		Author saved = authorDao.saveNewAuthor(author);

		authorDao.deleteAuthorById(saved.getId());

		Author deleted = authorDao.getById(saved.getId());
		assertThat(deleted).isNull();

		assertThat(authorDao.getById(saved.getId()));

	}

	@Test
	void testUpdateAuthor() {
		Author author = new Author();
		author.setFirstName("john");
		author.setLastName("t");

		Author saved = authorDao.saveNewAuthor(author);

		saved.setLastName("Thompson");
		Author updated = authorDao.updateAuthor(saved);

		assertThat(updated.getLastName()).isEqualTo("Thompson");
	}

	@Test
	void testSaveAuthor() {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Thompson");
		Author saved = authorDao.saveNewAuthor(author);

		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isNotNull();
	}

	@Test
	void testFindAllBooks() {
		List<Book> books = bookDao.findAll();

		assertThat(books).isNotNull();
		assertThat(books.size()).isGreaterThan(0);
	}

	@Test
	void testFindBookByTile() {

		Book book = new Book();
		book.setIsbn("1235" + RandomString.make());
		book.setTitle("TITLETEST3");

		Book saved = bookDao.saveNewBook(book);

		Book fetched = bookDao.findBookByTitle(book.getTitle());
		assertThat(fetched).isNotNull();

		bookDao.deleteBookById(saved.getId());
	}

	@Test
	void testGetAuthorByNameCriteria() {
		Author author = authorDao.findAuthorByNameCriteria("Craig", "Walls");

		assertThat(author).isNotNull();
	}

	@Test
	void testGetAuthorByName() {
		Author author = authorDao.findAuthorByName("Craig", "Walls");

		assertThat(author).isNotNull();
	}

	@Test
	void testGetAuthor() {

		Author author = authorDao.getById(1l);

		assertThat(author.getId()).isNotNull();
	}
}