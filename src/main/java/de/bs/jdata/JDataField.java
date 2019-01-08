package de.bs.jdata;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used on fields or methods, to mark them for JData, it is optional, when
 * {@link JDataMode#USE_ALL_FIELDS} is used and required, when
 * {@link JDataMode#USE_ONLY_ANNOTATIONS} is used.
 * <p>
 * A field or method should not be static, volatile or final. If such a field
 * has this annotation, JData will throw an exception on building time.
 * 
 * @author little Rathi
 * 
 */
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface JDataField {
	/**
	 * a name for this field, when left empty, the normal field name is used.
	 * 
	 * @return String
	 */
	String name() default "";

	/**
	 * a more precise description to the field. For example more details of the
	 * field (use, ranges, etc).
	 * 
	 * @return String
	 */
	String desc() default "";

	/**
	 * make only sense, when the type of the field is a custom class. Make it
	 * possible to change the modes {@link JDataMode#USE_ALL_FIELDS} and
	 * {@link JDataMode#USE_ONLY_ANNOTATIONS} from superclass to subclass. When not
	 * used, it will use the mode of the superclass. {@link JDataMode#USE_HERITAGE}
	 * should not be used, it is the default value.
	 * 
	 * @return {@link JDataMode}
	 */
	JDataMode submode() default JDataMode.USE_HERITAGE;
}
