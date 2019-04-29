package de.bs.jdata;

import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.DataManager;
import de.bs.jdata.manager.object.ObjectManagerBuilder;
import de.bs.jdata.transformator.Transformator;

public class JData {
	private static DataManager dataManager = new DataManager();
	
	public static DataManager addConverter(final Class<? extends TypeConverter> converter) {
		return dataManager.addConverter(converter);
	}
	
	// TODO: to define if parameter type should be Transformator or Class<? Extends Transformator>
	// TODO: seems not to be needed
	@Deprecated
	public static DataManager addTransformator(final Transformator transformator) {
		return dataManager.addTransformator(transformator);
	}
	
	public static <T> ObjectManagerBuilder<T> forObjectClass(final Class<T> objectClass) {
		return dataManager.forObjectClass(objectClass);
	}
	
	// within the target object, must be a object attribute (not static) annotated with @Target
	public static <T> ObjectManagerBuilder<T> forTarget(Object target) {
		return null;
	}
}
