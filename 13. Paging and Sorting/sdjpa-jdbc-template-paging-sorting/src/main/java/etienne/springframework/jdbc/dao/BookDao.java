package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Etienne on 8/29/21.
 */
public interface BookDao {

    List<Book> findAllBooksSortByTitle(Pageable pageable);

    List<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooks(int pageSize, int offset);

    List<Book> findAllBooks();
    
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
