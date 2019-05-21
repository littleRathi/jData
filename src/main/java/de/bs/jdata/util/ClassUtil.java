package de.bs.jdata.util;

/**
 * Util class for handling {@link Class}. Get a {@link Class} object from a full
 * qualified name from a String or instantiate objects.
 * 
 * @author little Rathi
 *
 */
public class ClassUtil {

	/**
	 * Create the {@link Class} object for the passed full qualified name of a
	 * class.
	 * 
	 * @param fullQualifiedName is the full qualified name of a class
	 * @return the {@link Class} object for the given full qualified name
	 */
	public static Class<?> getClass(final String fullQualifiedName) {
		try {
			return (Class<?>) Class.forName(fullQualifiedName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("description", e); // proper exception handling
		}
	}

	/**
	 * Create a instance of the given {@link Class} object. It will use the default
	 * constructor.
	 * 
	 * @param objectClass from that the object will be created
	 * @return the created object
	 */
	public static <T> T instantiate(final Class<T> objectClass) {
		try {
			return objectClass.getConstructor().newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("description", e); // proper exception handling
		}
	}

	/**
	 * Creates from the given full qualified name passed as {@link String} parameter
	 * the object.
	 * 
	 * @param fullQualifiedName is the full qualified name of a class
	 * @return the object from the given full qualified name
	 */
	public static Object instantiate(final String fullQualifiedName) {
		return instantiate(getClass(fullQualifiedName));
	}

}
