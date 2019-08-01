package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.IntPrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Integer} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class IntConverter extends AbstractPrimitiveConverter<Type<Is<IntPrimitive, OrOf<Integer>>>> {
	public IntConverter() {
		this(null, null);
	}

	private IntConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Integer.class, int.class);
	}

	@Override
	protected TypeConverter<Type<Is<IntPrimitive, OrOf<Integer>>>> createConverter(final Class<?> actualType,
			final Class<?> parentType) {
		return new IntConverter(actualType, parentType);
	}

	@Override
	public Object decode(final String representation) {
		if (representation == null) {
			return null;
		}
		return Integer.valueOf(representation);
	}

}
