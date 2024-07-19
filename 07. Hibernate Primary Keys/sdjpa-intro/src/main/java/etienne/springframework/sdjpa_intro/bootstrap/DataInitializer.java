/*
 * Crée by Etienne KOA
 */

package etienne.springframework.sdjpa_intro.bootstrap;

import etienne.springframework.sdjpa_intro.domain.AuthorUuid;
import etienne.springframework.sdjpa_intro.domain.Book;
import etienne.springframework.sdjpa_intro.domain.BookNatural;
import etienne.springframework.sdjpa_intro.domain.BookUuid;
import etienne.springframework.sdjpa_intro.repositories.AuthorUuidRepository;
import etienne.springframework.sdjpa_intro.repositories.BookNaturalRepository;
import etienne.springframework.sdjpa_intro.repositories.BookRepository;
import etienne.springframework.sdjpa_intro.repositories.BookUuidRepository;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final AuthorUuidRepository authorUuidRepository;
	private final BookUuidRepository bookUuidRepository;



	public DataInitializer(BookRepository bookRepository,
						   AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository){
		this.bookRepository = bookRepository;
		this.authorUuidRepository = authorUuidRepository;
		this.bookUuidRepository = bookUuidRepository;

	}

	@Override
	public void run(String... args) throws Exception {


		bookRepository.deleteAll();

		Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse", null);

		System.out.println("Id: " + bookDDD.getId());

		Book saveDDD = bookRepository.save(bookDDD);

		Book bookSIA = new Book("Spring In Action", "234234", "Orielly", null);
		Book savedSIA = bookRepository.save(bookSIA);

		bookRepository.findAll().forEach(book -> {
			System.out.println("Book Id: " + book.getId());
			System.out.println("Book Title: " + book.getTitle());
		});

		AuthorUuid authorUuid = new AuthorUuid();
		authorUuid.setFirstName("Joe");
		authorUuid.setLastName("Buck");
		AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
		System.out.printf("Saved Author UUID: " + savedAuthor.getId());

		BookUuid bookUuid = new BookUuid();
		bookUuid.setTitle("All About UUIDs");
		BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
		System.out.println("Saved Book UUID: " + savedBookUuid.getId());
	}
}
