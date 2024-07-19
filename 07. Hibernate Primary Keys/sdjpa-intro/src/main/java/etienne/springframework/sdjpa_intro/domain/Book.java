/*
 * Crée par Etienne KOA, le 02/06/24.
 */

package etienne.springframework.sdjpa_intro.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name ="book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String isbn;
	private String publisher;
	private Long authorId;


	public Book() {
	}


	public Book(String title, String isbn, String publisher, Long authorId) {
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.authorId = authorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book book)) return false;

		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
