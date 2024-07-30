/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpainheritence.domain.joined;

import jakarta.persistence.Entity;

@Entity
public class Piano extends Instrument {

	private Integer numberOfKeys;

	public Integer getNumberOfKeys() {
		return numberOfKeys;
	}

	public void setNumberOfKeys(Integer numberOfKeys) {
		this.numberOfKeys = numberOfKeys;
	}
}
