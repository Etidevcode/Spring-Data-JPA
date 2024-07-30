/*
 * Etienne KOA :  18/07/2024
 */

/*
 * Etienne KOA :  18/07/2024
 */

/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpainheritence.domain.tableperclass;

import jakarta.persistence.Entity;

@Entity
public class Dolphin extends Mammal {

	private Boolean hasSpots;

	public Boolean getHasSpots() {
		return hasSpots;
	}

	public void setHasSpots(Boolean hasSpots) {
		this.hasSpots = hasSpots;
	}
}
