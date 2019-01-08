package de.bs.jdata;

import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.DataManager;
import de.bs.jdata.manager.object.ObjectManagerBuilder;
import de.bs.jdata.transformator.Transformator;

public class JData {
	private static DataManager defaultManager = new DataManager();
	
	public static DataManager addConverter(final Class<? extends TypeConverter> converter) {
		return defaultManager.addConverter(converter);
	}
	
	// to define if parameter type should be Transformator or Class<? Extends Transformator>
	public static DataManager addTransformator(final Transformator transformator) {
		return defaultManager.addTransformator(transformator);
	}
	
	public static <T> ObjectManagerBuilder<T> forObjectClass(final Class<T> objectClass) {
		return null;
	}
	
	// within the target object, must be a object attribute (not static) annotated with @Target
	public static <T> ObjectManagerBuilder<T> forTarget(Object target) {
		return null;
	}
}
