/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.listeners;

import etienne.springframework.creditcard.services.EncryptionService;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.springframework.stereotype.Component;

@Component
public class PostLoadListener extends AbstractEncryptionListener implements PostLoadEventListener {

	public PostLoadListener(EncryptionService encryptionService) {
		super(encryptionService);
	}

	@Override
	public void onPostLoad(PostLoadEvent event) {

		System.out.println("In Post Load");

		this.decrypt(event.getEntity());
	}
}
