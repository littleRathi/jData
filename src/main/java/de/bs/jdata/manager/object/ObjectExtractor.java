package de.bs.jdata.manager.object;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import de.bs.jdata.JDataMode;
import de.bs.jdata.JDataObject;
import de.bs.jdata.manager.ObjectDefinition;

/**
 * For extracting data from a {@link Class} object
 * 
 * @author little Rathi
 *
 */
public class ObjectExtractor {
	public static final String DEFAULT_FOR_EMPTY_NAME_FORMAT = "<%s>";
	
	private final Map<Class<?>, ObjectDefinition<?>> cache = new HashMap<Class<?>, ObjectDefinition<?>>();
	
	public <T> ObjectDefinition<T> extract(final Class<T> objectClass) {
		if (cache.containsKey(objectClass)) {
			return (ObjectDefinition<T>)cache.get(objectClass);
		}
		JDataObject dataObject = getDataAnnotation(objectClass);
		
		ObjectDefinition<T> newObjectDef = construct(dataObject, objectClass);
//		validate(newObjectDef)
		
		cache.put(objectClass, newObjectDef);
		return newObjectDef;
	}
	
	private JDataObject getDataAnnotation(final Class<?> objectClass) {
		JDataObject jData = objectClass.getAnnotation(JDataObject.class);
		
		if (jData == null) {
			jData = new JDataObject() {
				
				@Override
				public Class<? extends Annotation> annotationType() {
					return JDataObject.class;
				}
				
				@Override
				public String name() {
					return String.format(DEFAULT_FOR_EMPTY_NAME_FORMAT, objectClass.getName());
				}
				
				@Override
				public JDataMode mode() {
					return JDataMode.USE_ALL_FIELDS;
				}
				
				@Override
				public String desc() {
					return "";
				}
			};
		}
		
		return jData;
	}
	
	private <T> ObjectDefinition<T> construct(final JDataObject dataObject, final Class<T> objectClass) {
		ObjectDefinition<T> objectManager = new ObjectDefinition<T>();
		
		objectManager.setName(dataObject.name());
		objectManager.setDataMode(dataObject.mode());
		objectManager.setDesc(dataObject.desc());
		
		objectManager.setDefinedObjekt(objectClass);
		
		
		return objectManager;
	}
}
