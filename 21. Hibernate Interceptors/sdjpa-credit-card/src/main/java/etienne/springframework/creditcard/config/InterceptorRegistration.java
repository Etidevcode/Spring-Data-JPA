/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.config;

import etienne.springframework.creditcard.interceptors.EncryptionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

	@Autowired
	EncryptionInterceptor interceptor;

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put("hibernate.session_factory.interceptor", interceptor);
	}
}
