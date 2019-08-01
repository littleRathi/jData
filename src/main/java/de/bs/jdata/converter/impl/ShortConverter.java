package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.ShortPrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Short} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class ShortConverter extends AbstractPrimitiveConverter<Type<Is<ShortPrimitive, OrOf<Short>>>> {

	public ShortConverter() {
		this(null, null);
	}

	private ShortConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Short.class, short.class);
	}

	@Override
	protected TypeConverter<Type<Is<ShortPrimitive, OrOf<Short>>>> createConverter(Class<?> actualType,
			Class<?> parentType) {
		return new ShortConverter(actualType, parentType);
	}

	@Override
	public Object decode(String representation) {
		if (representation == null) {
			return null;
		}
		return Short.valueOf(representation);
	}

}
