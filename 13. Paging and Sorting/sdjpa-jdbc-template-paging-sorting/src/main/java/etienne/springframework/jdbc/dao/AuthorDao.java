package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Etienne on 8/22/21.
 */
public interface AuthorDao {

    List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable);

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
