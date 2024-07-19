/*
 * Crée par Etienne KOA, le 02/06/24
 */

package etienne.springframework.sdjpa_intro.repositories;

import etienne.springframework.sdjpa_intro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
