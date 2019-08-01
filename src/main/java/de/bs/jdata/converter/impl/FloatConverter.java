package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.FloatPrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Float} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class FloatConverter extends AbstractPrimitiveConverter<Type<Is<FloatPrimitive, OrOf<Float>>>> {

	public FloatConverter() {
		this(null, null);
	}

	private FloatConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Float.class, float.class);
	}

	@Override
	protected TypeConverter<Type<Is<FloatPrimitive, OrOf<Float>>>> createConverter(Class<?> actualType,
			Class<?> parentType) {
		return new FloatConverter(actualType, parentType);
	}

	@Override
	public Object decode(String representation) {
		if (representation == null) {
			return null;
		}
		return Float.valueOf(representation);
	}
}
