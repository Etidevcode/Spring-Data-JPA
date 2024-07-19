/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpa_intro.repositories;

import etienne.springframework.sdjpa_intro.domain.BookUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
