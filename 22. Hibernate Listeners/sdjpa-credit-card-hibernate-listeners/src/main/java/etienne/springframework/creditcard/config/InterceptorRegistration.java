package etienne.springframework.creditcard.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;

import java.util.Map;

/**
 * Created by Etienne on 01/08/24.
 */
//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

  //  @Autowired
   // EncryptionInterceptor interceptor;

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
   //     hibernateProperties.put("hibernate.session_factory.interceptor", interceptor);
    }
}
