package de.bs.jdata.converter;

import de.bs.jdata.matcher.Matcher;

/**
 * Basic class for converter between String and another type. For that it
 * provides following methods:
 * <ul>
 * <li>decode(String)</li>
 * <li>decode(Object)</li>
 * <li>encode(Object)</li>
 * </ul>
 * First two, let convert it from String/Object (generic) to specific type. The
 * last one it is to convert from the given Type to the generic type String.
 * 
 * @author little Rathi
 *
 * @param <Matcher> defines the Matcher to identify for which type the converter
 *        can be used
 */
public abstract class TypeConverter<M extends Matcher> {
	/**
	 * Instantiating a working {@link TypeConverter} object. ConverterRegistry will
	 * create a object of each {@link TypeConverter} that will be passed, for
	 * creating the right instances of {@link TypeConverter}.
	 * 
	 * @param actualType is the relevant type,
	 * @param parentType additional information, that can be <code>null</code> and
	 *                   will be not used the provided {@link TypeConverter}
	 * @return
	 */
	public abstract TypeConverter<?> instantiateFor(final Class<?> actualType, final Class<?> parentType);

	/**
	 * To decode a String to the {@link TypeConverter} type.
	 * 
	 * @param representation contains the {@link String} representation that the
	 *                       {@link TypeConverter} can convert
	 * @return passed value converted to the specific type
	 */
	public abstract Object decode(String representation);

	/**
	 * TODO: own exception, to let other know when implementing what to do when it
	 * cannot be transformed, or other problems/exceptions occur. Eespecially here,
	 * when wrong types are put in
	 * 
	 * To decode the to the right type, it is possible that the passed object
	 * is already the right type and will be simply returned.
	 * 
	 * @param representation object that have to be converted to the right
	 *                       {@link TypeConverter} type, could be already the right
	 *                       type
	 * @return passed value converted to the specific type
	 */
	public abstract Object decode(Object representation);

	/**
	 * To encode the type to String representation.
	 * 
	 * @param object that will be encode to the String representation
	 * @return String representation of the passed Object, can be <code>null</code>
	 *         when passed object is null
	 */
	public abstract String encode(Object object);

	/**
	 * Type for that {@link TypeConverter}.
	 * 
	 * @return the {@link Class} object, cannot be <code>null</code>
	 */
	public abstract Class<?> getActualType();

	/**
	 * Type of the Parent of the TypeConverter, that can be used for different
	 * behaviors.
	 * 
	 * @return the {@link Class} object, when set. It can be <code>null</code>
	 */
	public abstract Class<?> getParentType();
}
