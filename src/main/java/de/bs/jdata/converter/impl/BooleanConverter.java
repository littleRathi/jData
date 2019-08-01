package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.BooleanPrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Boolean} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class BooleanConverter extends AbstractPrimitiveConverter<Type<Is<BooleanPrimitive, OrOf<Boolean>>>> {

	public BooleanConverter() {
		this(null, null);
	}

	private BooleanConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Boolean.class, boolean.class);
	}

	@Override
	protected TypeConverter<Type<Is<BooleanPrimitive, OrOf<Boolean>>>> createConverter(final Class<?> actualType,
			final Class<?> parentType) {
		return new BooleanConverter(actualType, parentType);
	}

	@Override
	public Object decode(String representation) {
		if (representation == null) {
			return null;
		}
		return Boolean.valueOf(representation);
	}

}
