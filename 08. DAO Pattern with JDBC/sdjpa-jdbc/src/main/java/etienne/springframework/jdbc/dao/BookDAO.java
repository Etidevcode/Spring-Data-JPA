/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Book;

public interface BookDAO {

	Book getById(Long id);

	Book findBookByTitle(String title);

	Book saveNewBook(Book author);

	Book updateBook(Book author);

	void deleteBookById(Long id);
}
