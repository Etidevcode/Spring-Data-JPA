package etiennespringframework.sdjpamultidb.repositories.creditcard;

import etiennespringframework.sdjpamultidb.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
