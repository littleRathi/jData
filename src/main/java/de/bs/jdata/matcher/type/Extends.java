package de.bs.jdata.matcher.type;

/**
 * Matches if the first parameter from {@link #check(Class, Class)} is same or a
 * subclass of the given generic parameter <code>T</code>.
 * 
 * @author little Rathi
 *
 * @param <T> the concrete type
 */
public class Extends<T> extends TypeMatcher {

	@Override
	public boolean check(final Class<?> type, final Class<?> parentType) {
		return this.type.isAssignableFrom(type);
	}

}
