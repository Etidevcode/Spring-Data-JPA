/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Author;
import etienne.springframework.jdbc.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AuthorDaoImpl implements AuthorDao {

	private final AuthorRepository authorRepository;

	public AuthorDaoImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}


	@Override
	public Author getById(Long id) {
		return authorRepository.getById(id);
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
				.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Author saveNewAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	@Transactional
	public Author updateAuthor(Author author) {

		Author foundAuthor = authorRepository.getById(author.getId());
		foundAuthor.setFirstName(author.getFirstName());
		foundAuthor.setLastName(author.getLastName());
		return authorRepository.save(foundAuthor);
	}

	@Override
	public void deleteAuthorById(Long id) {
		authorRepository.deleteById(id);
	}
}
