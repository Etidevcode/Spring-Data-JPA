/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Book;
import etienne.springframework.jdbc.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookDaoImpl implements BookDao {

	private final BookRepository bookRepository;

	public BookDaoImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	@Override
	public Book getById(Long id) {
		return bookRepository.getById(id);
	}

	@Override
	public Book findBookByTitle(String title) {
		return bookRepository.findBookByTitle(title)
				.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Book saveNewBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public Book updateBook(Book book) {
		Book foundBook = bookRepository.getById(book.getId());
		foundBook.setIsbn(book.getIsbn());
		foundBook.setPublisher(book.getPublisher());
		foundBook.setAuthorId(book.getAuthorId());
		foundBook.setTitle(book.getTitle());
		return bookRepository.save(foundBook);
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}
}
