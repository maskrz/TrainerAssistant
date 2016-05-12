package com.herokuapp.tassistant.util.reflection;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Tools for access to the classes and objects values by reflection
 * @author bplc904
 * @version 1.0
 */
public interface ReflectionUtil {

	/**
	 * Finds fields annotated by given annotations and return those fields names
	 * and values map
	 * @param object
	 * @param annotationClasses
	 * @return map of fields names and values
	 * @throws ReflectionException if no fields with given annotations
	 */
	public Map<String, Object> getAnnotatedFieldsValues(Object object,
			Class<? extends Annotation>... annotationClasses)
			throws ReflectionException;
	
	public Map<String, Annotation> getAnnotationsForField(Class<?> clazz,
			Class<? extends Annotation>... annotationClasses);
	
	public Annotation getAnnotationForClass(Class<?> clazz,
			Class<? extends Annotation> annotationClass);
	
	public Object getFieldValue(Object object, String fieldName)
			throws ReflectionException;
}
