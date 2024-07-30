/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpainheritence.domain.repositories;

import etienne.springframework.sdjpainheritence.domain.joined.ElectricGuitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Long> {
}
