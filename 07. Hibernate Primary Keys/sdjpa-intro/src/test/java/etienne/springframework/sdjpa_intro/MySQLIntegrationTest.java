package etienne.springframework.sdjpa_intro;


import etienne.springframework.sdjpa_intro.domain.AuthorUuid;
import etienne.springframework.sdjpa_intro.domain.BookNatural;
import etienne.springframework.sdjpa_intro.domain.BookUuid;
import etienne.springframework.sdjpa_intro.domain.composite.AuthorComposite;
import etienne.springframework.sdjpa_intro.domain.composite.AuthorEmbedded;
import etienne.springframework.sdjpa_intro.domain.composite.NameId;
import etienne.springframework.sdjpa_intro.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"etienne.springframework.sdjpa_intro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorUuidRepository authorUuidRepository;

	@Autowired
	BookUuidRepository bookUuidRepository;

	@Autowired
	BookNaturalRepository bookNaturalRepository;

	@Autowired
	AuthorCompositeRepository authorCompositeRepository;

	@Autowired
	AuthorEmbeddedRepository authorEmbeddedRepository;

	@Test
	void authorEmbeddedTest() {
		NameId nameId = new NameId("EtidevCode", "K");
		AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);

		AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
		assertThat(saved).isNotNull();

		AuthorEmbedded fetched = authorEmbeddedRepository.getById(nameId);
		assertThat(fetched).isNotNull();
	}

	@Test
	void authorCompositeTest() {

		NameId nameId = new NameId("EtidevCode", "K");
		AuthorComposite authorComposite = new AuthorComposite();
		authorComposite.setFirstName(nameId.getFirstName());
		authorComposite.setLastName(nameId.getLastName());
		authorComposite.setCountry("US");

		AuthorComposite saved = authorCompositeRepository.save(authorComposite);
		assertThat(saved).isNotNull();

		AuthorComposite fetched = authorCompositeRepository.getById(nameId);
		assertThat(fetched).isNotNull();

	}

	@Test
	void bookNaturalTest() {
		BookNatural bookNatural = new BookNatural();
		bookNatural.setTitle("My Book");
		BookNatural saved = bookNaturalRepository.save(bookNatural);

		BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
		assertThat(fetched).isNotNull();
	}

	@Test
	void testBookUuid() {
		BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
		assertThat(bookUuid).isNotNull();
		assertThat(bookUuid.getId());

		BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
		assertThat(fetched).isNotNull();

	}

	@Test
	void testAuthorUuid() {
		AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
		assertThat(authorUuid).isNotNull();
		assertThat(authorUuid.getId()).isNotNull();

		AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
		assertThat(fetched).isNotNull();
	}

	@Test
	void testMySQL() {

		long countBefore = bookRepository.count();;
		assertThat(countBefore).isEqualTo(2);
	}
}
