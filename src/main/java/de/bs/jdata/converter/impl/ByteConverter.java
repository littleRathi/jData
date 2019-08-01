package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.BytePrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Byte} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class ByteConverter extends AbstractPrimitiveConverter<Type<Is<BytePrimitive, OrOf<Byte>>>> {

	public ByteConverter() {
		this(null, null);
	}

	private ByteConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Byte.class, byte.class);
	}

	@Override
	protected TypeConverter<Type<Is<BytePrimitive, OrOf<Byte>>>> createConverter(final Class<?> actualType,
			final Class<?> parentType) {
		return new ByteConverter(actualType, parentType);
	}

	@Override
	public Object decode(String representation) {
		if (representation == null) {
			return null;
		}
		return Byte.valueOf(representation);
	}

}
