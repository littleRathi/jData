package de.bs.jdata.converter;

import de.bs.jdata.matcher.Matcher;

/**
 * Simple abstraction that provides simple constructor, and declare needed
 * object variables and implements methods from {@link TypeConverter} that can
 * be implemented at that point.
 * 
 * @author little Rathi
 *
 * @param <T> generic for the {@link Matcher} class, that will passed to the
 *        {@link TypeConverter}
 */
public abstract class AbstractConverter<T extends Matcher> extends TypeConverter<T> {
	protected final Class<?> actualType;
	protected final Class<?> parentType;

	/**
	 * Simple constructor that takes the needed data.
	 * 
	 * @param actualType contains the type the converter is for
	 * @param parentType contains the parent type, can be used in more advanced {@link TypeConverter}, can be <code>null</code>, also see {@link TypeConverter#instantiateFor(Class, Class)}
	 */
	public AbstractConverter(final Class<?> actualType, final Class<?> parentType) {
		this.actualType = actualType;
		this.parentType = parentType;
	}

	@Override
	public Class<?> getActualType() {
		return actualType;
	}

	@Override
	public Class<?> getParentType() {
		return parentType;
	}
}
