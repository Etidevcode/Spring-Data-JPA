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
public class Dog extends Mammal {

	private String bread;

	public String getBread() {
		return bread;
	}

	public void setBread(String bread) {
		this.bread = bread;
	}
}
