/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class AuthorDaoImpl implements AuthorDao {

	private final JdbcTemplate jdbcTemplate;

	public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Author getById(Long id) {

		String sql = "select author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher, book.title from author\n" +
				"left outer join book on author.id = book.author_id where author.id = ?";


		/*
		String sql = "SELECT author.id AS author_id, author.first_name, author.last_name, " +

				"book.id AS book_id, book.isbn, book.publisher, book.title " +
				"FROM author " +
				"LEFT OUTER JOIN book ON author.id = book.author_id " +
				"WHERE author.id = ?";
		*/
		return jdbcTemplate.query(sql, new AuthorExtractor(), id);
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		return jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? and last_name = ?",
				getRowMapper(),
				firstName, lastName);
	}

	@Override
	public Author saveNewAuthor(Author author) {
		jdbcTemplate.update("INSERT INTO author (first_name, last_name) VALUES (?, ?)",
				author.getFirstName(), author.getLastName());

		Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

		return this.getById(createdId);
	}

	@Override
	public Author updateAuthor(Author author) {

		jdbcTemplate.update("UPDATE author SET first_name = ?, last_name = ? WHERE id = ?",
				author.getFirstName(), author.getLastName(), author.getId());

		return this.getById(author.getId());
	}

	@Override
	public void deleteAuthorById(Long id) {
		jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
	}

	private RowMapper<Author> getRowMapper(){
		return new AuthorMapper();
	}

}