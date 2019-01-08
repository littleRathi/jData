package de.bs.jdata;

/**
 * Defines how fields should be extract. It contains two options:
 * <ul>
 * <li>{@link JDataMode#USE_ALL_FIELDS}</li>
 * <li>{@link JDataMode#USE_ONLY_ANNOTATIONS}</li>
 * </ul>
 * that are available. The third option is (only) for internal use.
 * 
 * @author little Rathi
 *
 */
public enum JDataMode {
	/**
	 * All fields a class contains will be used as JData field, exceptions are
	 * described in {@link JDataField}. Methods should be JData fields needs the
	 * {@link JDataField} annotation.
	 */
	USE_ALL_FIELDS,
	/**
	 * All fields and method need the {@link JDataField} annotation, all other will
	 * be ignored.
	 */
	USE_ONLY_ANNOTATIONS,
	/**
	 * For internal use only, and is the default value for
	 * {@link JDataField#submode()}. That defines, that the subclass use the same
	 * mode as the superclass.
	 */
	USE_HERITAGE,
}
