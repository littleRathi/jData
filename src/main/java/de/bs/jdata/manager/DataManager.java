package de.bs.jdata.manager;

import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.object.ObjectManagerBuilder;
import de.bs.jdata.transformator.Transformator;

public class DataManager {
	public DataManager addConverter(final Class<? extends TypeConverter> converter) {
		return this;
	}
	
	// to define if parameter type should be Transformator or Class<? Extends Transformator>
	public DataManager addTransformator(final Transformator transformator) {
		return this;
	}
	
	public <T> ObjectManagerBuilder<T> forObjectClass(final Class<T> objectClass) {
		return null;
	}
}
