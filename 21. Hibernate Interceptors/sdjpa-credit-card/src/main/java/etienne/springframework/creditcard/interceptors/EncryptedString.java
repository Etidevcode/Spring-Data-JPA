/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.interceptors;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EncryptedString {
}
