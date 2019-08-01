package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.LongPrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Long} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class LongConverter extends AbstractPrimitiveConverter<Type<Is<LongPrimitive, OrOf<Long>>>> {
	public LongConverter() {
		this(null, null);
	}

	private LongConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Long.class, long.class);
	}

	@Override
	protected TypeConverter<Type<Is<LongPrimitive, OrOf<Long>>>> createConverter(final Class<?> actualType,
			final Class<?> parentType) {
		return new LongConverter(actualType, parentType);
	}

	@Override
	public Object decode(final String representation) {
		if (representation == null) {
			return null;
		}
		return Long.valueOf(representation);
	}

}
