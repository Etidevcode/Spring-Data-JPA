/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.creditcard.interceptors;

import etienne.springframework.creditcard.services.EncryptionService;
import org.springframework.stereotype.Component;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class EncryptionInterceptor extends EmptyInterceptor {

	private final EncryptionService encryptionService;

	public EncryptionInterceptor(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		if (currentState != null) {
			processFields(entity, currentState, propertyNames, "onFlushDirty");
		}
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (state != null) {
			processFields(entity, state, propertyNames, "onLoad");
		} else {
			System.err.println("State is null when loading entity: " + entity);
		}
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (state != null) {
			processFields(entity, state, propertyNames, "onSave");
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	private Object[] processFields(Object entity, Object[] state, String[] propertyNames, String type) {
		if (state == null) {
			throw new NullPointerException("State cannot be null when processing entity: " + entity);
		}

		List<String> annotationFields = getAnnotationFields(entity);

		for (String field : annotationFields) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (propertyNames[i].equals(field)) {
					if (state[i] != null && StringUtils.hasText(state[i].toString())) {
						if ("onSave".equals(type) || "onFlushDirty".equals(type)) {
							state[i] = encryptionService.encrypt(state[i].toString());
						} else if ("onLoad".equals(type)) {
							state[i] = encryptionService.decrypt(state[i].toString());
						}
					}
				}
			}
		}

		return state;
	}


	private List<String> getAnnotationFields(Object entity) {
		List<String> annotatedFields = new ArrayList<>();
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.getAnnotation(EncryptedString.class) != null) {
				annotatedFields.add(field.getName());
			}
		}
		return annotatedFields;
	}
}
