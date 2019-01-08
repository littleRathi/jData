package de.bs.jdata;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks a class as a JData object. It is not necessary but provides some
 * options, especially the {@link JDataObject#mode()}.
 * 
 * @author little Rathi
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface JDataObject {
	/**
	 * Description of the JData object.
	 * 
	 * @return String
	 */
	String desc() default "";

	/**
	 * Name for the JData object, can be relevant, when a programs has several JData
	 * objects. It make more easier to distinguish between them.
	 * 
	 * @return String
	 */
	String name() default "";

	/**
	 * Defines how the fields of this class are extracted. For possible options and
	 * there description {@link JDataMode}.
	 * 
	 * @return {@link JDataMode}
	 */
	JDataMode mode() default JDataMode.USE_ALL_FIELDS;
}
