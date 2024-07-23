/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Book;

public interface BookDao {
	Book getById(Long id);

	Book findBookByTitle(String title);

	Book saveNewBook(Book book);

	Book updateBook(Book book);

	void deleteBookById(Long id);
}