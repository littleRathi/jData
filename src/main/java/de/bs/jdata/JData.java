package de.bs.jdata;

import de.bs.jdata.configurator.Configurator;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.DataManager;
import de.bs.jdata.manager.object.ObjectManager;
import de.bs.jdata.manager.object.builder.ObjectManagerBuilder;
import de.bs.jdata.transformator.Transformator;

/**
 * Central class for the library for using. With the Annotations (like
 * {@link JDataField} and so on) it allows to create the ObjectManager. In
 * Subclasses are only relevant, when they are needed as parameter, like a
 * concrete {@link Configurator} or {@link TypeConverter}, when needed.
 * 
 * @author little Rathi
 *
 */
public class JData {
	private static DataManager dataManager = new DataManager();


	/**
	 * See {@link DataManager#addConverter(Class)}.
	 */
	public static DataManager addConverter(final Class<? extends TypeConverter<?>> converter) {
		return dataManager.addConverter(converter);
	}

	// TODO: to define if parameter type should be Transformator or Class<? Extends
	// Transformator>
	// TODO: seems not to be needed
	@Deprecated
	public static DataManager addTransformator(final Transformator transformator) {
		return dataManager.addTransformator(transformator);
	}

	/**
	 * See {@link DataManager#forObjectClass(Class)}.
	 */
	public static <T> ObjectManagerBuilder<T> forObjectClass(final Class<T> objectClass) {
		return dataManager.forObjectClass(objectClass);
	}

	// within the target object, must be a object attribute (not static) annotated
	// with @Target
	public static <T> ObjectManagerBuilder<T> forTarget(Object target) {
		return null;
	}
}
