package etienne.springframework.creditcard.interceptors;

import java.lang.annotation.*;

/**
 * Created by Etienne on 01/08/24.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EncryptedString {
}
