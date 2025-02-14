/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

	private final EntityManagerFactory emf;

	public AuthorDaoImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Author getById(Long id) {
		EntityManager em = getEntityManager();
		Author author = em.find(Author.class, id);
		em.close();
		return author;
	}

	@Override
	public Author findAuthorByName(String firstName, String lastName) {
		TypedQuery<Author> query = getEntityManager().createQuery("SELECT a FROM Author a " +
				"WHERE a.firstName = :first_name and a.lastName= :last_name", Author.class);
		query.setParameter("first_name", firstName);
		query.setParameter("last_name", lastName);

		Author author = query.getSingleResult();
		return author;
	}

	@Override
	public Author saveNewAuthor(Author author) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.joinTransaction();
		em.persist(author);
		em.flush();
		em.getTransaction().commit();
		return author;
	}

	@Override
	public Author updateAuthor(Author author) {
		EntityManager em = getEntityManager();

		try {
			em.joinTransaction();
			em.merge(author);
			em.flush();
			em.clear();
			Author savedAuthor = em.find(Author.class, author.getId());
			return savedAuthor;
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteAuthorById(Long id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Author author = em.find(Author.class, id);
		em.remove(author);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
