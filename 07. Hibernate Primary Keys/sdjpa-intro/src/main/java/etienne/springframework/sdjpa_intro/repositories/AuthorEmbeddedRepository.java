/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpa_intro.repositories;

import etienne.springframework.sdjpa_intro.domain.composite.AuthorEmbedded;
import etienne.springframework.sdjpa_intro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
