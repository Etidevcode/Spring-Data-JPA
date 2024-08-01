/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.domain;

import etienne.springframework.creditcard.config.SpringContexteHelper;
import etienne.springframework.creditcard.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {
	@Override
	public String convertToDatabaseColumn(String attribute) {
		return getEncryptionService().encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return getEncryptionService().decrypt(dbData);
	}

	private EncryptionService getEncryptionService() {
		return SpringContexteHelper.getApplicationContext().getBean(EncryptionService.class);
	}
}
