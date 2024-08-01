package etienne.springframework.creditcard.services;

/**
 * Created by Etienne on 01/08/24.
 */
public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
