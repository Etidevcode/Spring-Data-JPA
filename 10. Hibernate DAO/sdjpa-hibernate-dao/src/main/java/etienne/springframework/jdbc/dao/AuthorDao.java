/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Author;
import org.springframework.stereotype.Component;

@Component
public interface AuthorDao {

	Author getById(Long id);

	Author findAuthorByName(String firstName, String lastName);

	Author saveNewAuthor(Author author);

	Author updateAuthor(Author author);

	void deleteAuthorById(Long id);
}
