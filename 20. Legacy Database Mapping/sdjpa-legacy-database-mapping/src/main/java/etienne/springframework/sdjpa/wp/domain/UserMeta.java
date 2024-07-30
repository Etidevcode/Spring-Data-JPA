/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.sdjpa.wp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "wp_usermeta")
public class UserMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "umeta_id")
	private Long id;

	// private Long userId;

	@ManyToOne
	private User user;

	@Size(max = 255)
	@Column(columnDefinition = "longtext")
	private String metaKey;

	@Lob
	@Column(name = "meta_value", columnDefinition = "longtext")
	private String metaValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	*/

	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public String getMetaValue() {
		return metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}
}