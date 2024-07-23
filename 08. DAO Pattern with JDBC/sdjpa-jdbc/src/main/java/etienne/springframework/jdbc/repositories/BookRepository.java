package etienne.springframework.jdbc.repositories;


import etienne.springframework.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
