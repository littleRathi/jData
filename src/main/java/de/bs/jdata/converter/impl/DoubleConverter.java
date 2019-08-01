package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.DoublePrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Double} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class DoubleConverter extends AbstractPrimitiveConverter<Type<Is<DoublePrimitive, OrOf<Double>>>> {

	public DoubleConverter() {
		this(null, null);
	}

	private DoubleConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Double.class, double.class);
	}

	@Override
	protected TypeConverter<Type<Is<DoublePrimitive, OrOf<Double>>>> createConverter(Class<?> actualType,
			Class<?> parentType) {
		return new DoubleConverter(actualType, parentType);
	}

	@Override
	public Object decode(String representation) {
		if (representation == null) {
			return null;
		}
		return Double.valueOf(representation);
	}

}
