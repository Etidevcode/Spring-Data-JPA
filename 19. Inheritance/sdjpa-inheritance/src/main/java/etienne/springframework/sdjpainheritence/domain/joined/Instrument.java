/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpainheritence.domain.joined;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
