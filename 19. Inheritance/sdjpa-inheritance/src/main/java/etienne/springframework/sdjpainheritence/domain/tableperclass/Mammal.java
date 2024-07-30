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

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Mammal {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long id;

	private Integer bodyTemp;

	private String species;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(Integer bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
}
