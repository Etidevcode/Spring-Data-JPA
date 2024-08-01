/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.domain;

import etienne.springframework.creditcard.config.SpringContexteHelper;
import etienne.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.*;

public class CreditCardJPACallback {

	@PrePersist
	@PreUpdate
	public void beforeInsertOrUpdate(CreditCard creditCard) {
		System.out.println("before update was called ....");
		creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
	}

	@PostPersist
	@PostLoad
	@PostUpdate
	public void postLoad(CreditCard creditCard) {
		System.out.println("Post Load was called ...");
		creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
	}

	private EncryptionService getEncryptionService() {
		return SpringContexteHelper.getApplicationContext().getBean(EncryptionService.class);
	}
}
