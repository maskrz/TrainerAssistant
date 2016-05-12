package com.herokuapp.tassistant.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.herokuapp.tassistant.util.LambdaExceptionUtil.*;

import org.springframework.stereotype.Component;

@Component("reflectionUtil")
public class ReflectionUtilImpl implements ReflectionUtil {

	@Override
	public Map<String, Object> getAnnotatedFieldsValues(Object object, Class<? extends Annotation>... annotationClasses)
			throws ReflectionException {
		Map<String, Object> values = new HashMap<String, Object>();
		try {
			List<Field> fields = getAnnotatedFields(object, annotationClasses);
			values = fields.stream().peek(field -> field.setAccessible(true))
					.collect(Collectors.toMap(e -> e.getName(), rethrowFunction(e -> e.get(object))));
		} catch (Exception ex) {
			throw new ReflectionException(ex);
		}
		return values;
	}

	@Override
	public Map<String, Annotation> getAnnotationsForField(Class<?> clazz,
			Class<? extends Annotation>... annotationClasses) {
		Map<String, Annotation> annotations = new HashMap<String, Annotation>();
		Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
			Arrays.asList(annotationClasses).forEach(annotationClass -> {
				Annotation annotation = field.getAnnotation(annotationClass);
				if (annotation != null) {
					annotations.put(field.getName(), annotation);
				}
			});
		});		
		return annotations;
	}

	@Override
	public Annotation getAnnotationForClass(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		return clazz.getAnnotation(annotationClass);
	}

	@Override
	public Object getFieldValue(Object object, String fieldName) throws ReflectionException {
		if (object != null) {
			try {
				Field field = object.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				return field.get(object);
			} catch (Exception e) {
				throw new ReflectionException(e);
			}
		}
		return null;
	}

	/**
	 * Return fields annotated by given annotations
	 * 
	 * @param obj
	 * @param annotationClasses
	 * @return list of fields
	 */
	private List<Field> getAnnotatedFields(Object obj, Class<? extends Annotation>... annotationClasses) {
		Class<?> elementClass = obj.getClass();
		List<Field> annotatedFields = new ArrayList<Field>();
		while (elementClass.getSuperclass() != null) {
			for (Field field : elementClass.getDeclaredFields()) {
				for (Class<? extends Annotation> annotationClass : annotationClasses) {
					if (field.isAnnotationPresent(annotationClass)) {
						annotatedFields.add(field);
						break;
					}
				}
			}
			elementClass = elementClass.getSuperclass();
		}
		return annotatedFields;
	}
}
