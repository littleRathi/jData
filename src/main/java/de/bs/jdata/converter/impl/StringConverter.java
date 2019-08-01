package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.type.Exact;

/**
 * Simple Converter for {@link String}. Even String needs a converter.
 * 
 * @author little Rathi
 *
 */
public class StringConverter extends AbstractConverter<Type<Exact<String>>> {

	public StringConverter() {
		this(null, null);
	}

	private StringConverter(Class<?> actualType, Class<?> parentType) {
		super(actualType, parentType);
	}

	@Override
	public TypeConverter<?> instantiateFor(Class<?> actualType, Class<?> parentType) {
		checkInstantiation(actualType);
		return new StringConverter(actualType, parentType);
	}

	private void checkInstantiation(final Class<?> actualType) {
		if (actualType == null) {
			throw new IllegalArgumentException(
					"For parameter actualType is null as value and not allowed. Must be Class object for '"
							+ String.class.getName() + "'.");
		}
		if (!actualType.equals(String.class)) {
			throw new IllegalArgumentException("For parameter actualType is the value '" + actualType.getName()
					+ "' not allowed. Must be Class object of String.");
		}
	}

	@Override
	public Object decode(String representation) {
		return representation;
	}

	// TODO: Should it only work for String?
	@Override
	public Object decode(Object representation) {
		if (representation == null) {
			return null;
		}
		return representation.toString();
	}

	// TODO: Should it only work for String?
	@Override
	public String encode(Object object) {
		if (object == null) {
			return null;
		}
		return object.toString();
	}

}
