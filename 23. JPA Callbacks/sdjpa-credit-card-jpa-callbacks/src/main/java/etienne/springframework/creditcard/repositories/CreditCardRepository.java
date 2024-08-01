package etienne.springframework.creditcard.repositories;

import etienne.springframework.creditcard.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Etienne on 01/08/24.
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
