package de.bs.jdata.converter;

import de.bs.jdata.matcher.Matcher;

/**
 * Class that implements many functionality for primitive types and there object
 * representations. In most (perhaps every) cases it only needs the
 * implementation for {@link TypeConverter#decode(String)} and
 * {@link #createConverter(Class, Class)}. The implementation are considered as
 * the object representation. For the behavior of the primitive it will use the
 * {@link PrimitiveWrapper} class.
 * 
 * @author little Rathi
 *
 * @param <T> see generic type of {@link TypeConverter}
 */
public abstract class AbstractPrimitiveConverter<T extends Matcher> extends AbstractConverter<T> {
	private final Class<?> OBJECT_TYPE;
	private final Class<?> PRIMITIVE_TYPE;

	public AbstractPrimitiveConverter(final Class<?> actualType, final Class<?> parentType, final Class<?> objectType,
			final Class<?> primitiveType) {
		super(actualType, parentType);
		OBJECT_TYPE = objectType;
		PRIMITIVE_TYPE = primitiveType;
	}

	@Override
	public TypeConverter<?> instantiateFor(final Class<?> actualType, final Class<?> parentType) {
		checkInstantiation(actualType);
		TypeConverter<T> result = createConverter(actualType, parentType);
		if (actualType.equals(PRIMITIVE_TYPE)) {
			result = new PrimitiveWrapper<T>(result);
		}
		return result;
	}

	/**
	 * Creating a concrete instance of the Converter. This method gets used in
	 * mostly complete implemented {@link #instantiateFor(Class, Class)}.
	 * 
	 * 
	 * @param actualType see {@link TypeConverter#instantiateFor(Class, Class)}
	 * @param parentType see {@link TypeConverter#instantiateFor(Class, Class)}
	 * @return see {@link TypeConverter#instantiateFor(Class, Class)}
	 */
	protected abstract TypeConverter<T> createConverter(final Class<?> actualType, final Class<?> parentType);

	private void checkInstantiation(final Class<?> actualType) {
		if (actualType == null) {
			throw new IllegalArgumentException(
					"For parameter actualType is null as value and not allowed. Must be Class object for '"
							+ OBJECT_TYPE.getName() + "' or '" + PRIMITIVE_TYPE.getName() + "'.");
		}
		if (!actualType.equals(PRIMITIVE_TYPE) && !actualType.equals(OBJECT_TYPE)) {
			throw new IllegalArgumentException("For parameter actualType is the value '" + actualType.getName()
					+ "' not allowed. Must be Class object of '" + OBJECT_TYPE.getName() + "' or '"
					+ PRIMITIVE_TYPE.getName() + "'.");
		}
	}

	@Override
	public Object decode(final Object representation) {
		if (representation == null) {
			return null;
		}
		if (representation instanceof String) {
			return decode((String) representation);
		}
		if (representation.getClass().equals(OBJECT_TYPE)) {
			return representation;
		}
		throw new IllegalArgumentException("For parameter representation is a object with value '" + representation
				+ "' and type '" + representation.getClass().getName() + "' not allowed. Must be from type '"
				+ actualType.getName() + "'.");
	}

	@Override
	public String encode(final Object object) {
		if (object == null) {
			return null;
		}
		if (!object.getClass().equals(OBJECT_TYPE)) {
			throw new IllegalArgumentException("Cannot encode an object from type '" + object.getClass().getName()
					+ "' but exspected '" + actualType.getName() + ". You need a different converter for '"
					+ object.getClass().getName() + "'.");
		}
		return object.toString();
	}

}
