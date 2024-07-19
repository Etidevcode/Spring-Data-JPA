/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpa_intro.repositories;

import etienne.springframework.sdjpa_intro.domain.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}
