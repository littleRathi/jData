package de.bs.jdata.example.all.converters;

import de.bs.jdata.converter.TypeConverter;

/**
 * Simple class for tests.
 * 
 * @author little Rathi
 *
 */
@SuppressWarnings("rawtypes")
public class ConverterMockThree extends TypeConverter {

	@Override
	public TypeConverter instantiateFor(Class actualType, Class parentType) {
		return null;
	}

	@Override
	public Object decode(String representation) {
		return null;
	}

	@Override
	public Object decode(Object representation) {
		return null;
	}

	@Override
	public String encode(Object object) {
		return null;
	}

	@Override
	public Class getActualType() {
		return null;
	}

	@Override
	public Class getParentType() {
		return null;
	}

}
