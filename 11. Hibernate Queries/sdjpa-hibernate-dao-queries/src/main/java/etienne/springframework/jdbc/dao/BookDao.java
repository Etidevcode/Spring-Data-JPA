/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Book;

import java.util.List;

public interface BookDao {

	Book findByISBN(String isbn);

	Book getById(Long id);

	Book findBookByTitle(String title);

	Book findBookByTitleCriteria(String title);

	Book saveNewBook(Book book);

	Book updateBook(Book book);

	void deleteBookById(Long id);

	List<Book> findAll();

	Book findBookByTitleNative(String title);
}