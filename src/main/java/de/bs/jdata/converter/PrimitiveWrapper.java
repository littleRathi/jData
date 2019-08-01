package de.bs.jdata.converter;

import de.bs.jdata.matcher.Matcher;

/**
 * Simple wrapper for {@link TypeConverter}, it adds functionality for primitive
 * types, like boolean or int. It calls the methods of the wrapped
 * {@link TypeConverter} and decode and encode methods containing additional
 * code for handling <code>null</code>.
 * 
 * @author little Rathi
 *
 * @param <T> see generic type of {@link TypeConverter}
 */
public class PrimitiveWrapper<T extends Matcher> extends TypeConverter<T> {
	private final TypeConverter<T> converter;

	public PrimitiveWrapper(final TypeConverter<T> typeConverter) {
		if (typeConverter == null) {
			throw new IllegalArgumentException("Parameter typeConverter is null, but should be a ");
		}
		this.converter = typeConverter;
	}

	@Override
	public TypeConverter<?> instantiateFor(Class<?> actualType, Class<?> parentType) {
		return converter.instantiateFor(actualType, parentType);
	}

	@Override
	public Object decode(String representation) {
		Object decoded = converter.decode(representation);
		if (decoded == null) {
			throw new IllegalArgumentException("Cannot decode null for 'primitive " + getActualType()
					+ "' type, try use '" + getActualType() + "' instead");
		}
		return decoded;
	}

	@Override
	public Object decode(Object representation) {
		Object decoded = converter.decode((Object) representation);
		if (decoded == null) {
			throw new IllegalArgumentException("Cannot decode null for 'primitive " + getActualType()
					+ "' type, try use '" + getActualType() + "' instead");
		}
		return decoded;
	}

	@Override
	public String encode(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Cannot decode null for 'primitive " + getActualType()
					+ "' type, try use '" + getActualType() + "' instead");
		}
		return converter.encode(object);
	}

	@Override
	public Class<?> getActualType() {
		return converter.getActualType();
	}

	@Override
	public Class<?> getParentType() {
		return converter.getParentType();
	}

}
