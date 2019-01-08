package de.bs.jdata;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Can be used if a field should be not used as a JData field, on both modes:
 * {@link JDataMode#USE_ALL_FIELDS} or {@link JDataMode#USE_ONLY_ANNOTATIONS}.
 * In the second case it is not need, and can be used as a reminder.
 * 
 * Note that this annotation can only be used on fields, because methods need
 * explicit use of {@link JDataField} annotation.
 * 
 * @author little Rathi
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface JDataFieldIgnore {
	/**
	 * Can be used to describe the reason why the annotated field should be ignored.
	 * 
	 * @return String
	 */
	String desc() default "";
}
