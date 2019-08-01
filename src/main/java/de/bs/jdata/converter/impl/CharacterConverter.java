package de.bs.jdata.converter.impl;

import de.bs.jdata.converter.AbstractPrimitiveConverter;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.matcher.Type;
import de.bs.jdata.matcher.substitute.CharPrimitive;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.OrOf;

/**
 * Simple Converter for {@link Character} and for the primitive.
 * 
 * @author little Rathi
 *
 */
public class CharacterConverter extends AbstractPrimitiveConverter<Type<Is<CharPrimitive, OrOf<Character>>>> {
	public CharacterConverter() {
		this(null, null);
	}

	private CharacterConverter(final Class<?> actualType, final Class<?> parentType) {
		super(actualType, parentType, Character.class, char.class);
	}

	@Override
	protected TypeConverter<Type<Is<CharPrimitive, OrOf<Character>>>> createConverter(final Class<?> actualType,
			final Class<?> parentType) {
		return new CharacterConverter(actualType, parentType);
	}

	@Override
	public Object decode(final String representation) {
		if (representation == null || representation.length() == 0) {
			return null;
		}
		if (representation.length() > 1) {
			throw new IllegalArgumentException(
					"Cannot decode String with length of '" + representation.length() + "' to a single char.");
		}
		return representation.charAt(0);
	}

}
