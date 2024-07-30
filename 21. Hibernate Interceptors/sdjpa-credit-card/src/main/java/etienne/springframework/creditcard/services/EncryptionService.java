/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.services;

public interface EncryptionService {

	String encrypt(String freeText);

	String decrypt(String encryptedText);
}
