package etiennespringframework.sdjpamultidb.services;

import etiennespringframework.sdjpamultidb.domain.creditcard.CreditCard;

/**
 * Created by Etienne on 01/08/24.
 */
public interface CreditCardService {

    CreditCard getCreditCardById(Long id);

	CreditCard saveCreditCard(CreditCard cc);
}
