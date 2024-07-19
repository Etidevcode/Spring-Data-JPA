/*
 * Crée by Etienne KOA
 */

package etienne.springframework.sdjpa_intro.bootstrap;

import etienne.springframework.sdjpa_intro.domain.Book;
import etienne.springframework.sdjpa_intro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

	private final BookRepository bookRepository;

	public DataInitializer(BookRepository bookRepository){
		this.bookRepository = bookRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");

		System.out.println("Id: " + bookDDD.getId());

		Book saveDDD = bookRepository.save(bookDDD);

		Book bookSIA = new Book("Spring In Action", "234234", "Orielly");
		Book savedSIA = bookRepository.save(bookSIA);

		bookRepository.findAll().forEach(book -> {
			System.out.println("Book Id: " + book.getId());
			System.out.println("Book Title: " + book.getTitle());
		});
	}
}
