/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpa_intro.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
public class AuthorUuid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JdbcTypeCode(value = Types.VARCHAR)
	@Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
	private UUID id;

	private String firstName;

	private String lastName;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
