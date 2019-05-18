package de.bs.jdata.matcher.type;

/**
 * Matches only when the first parameter from {@link #check(Class, Class)} is the same as the 
 * generic parameter <code>T</code>.
 * 
 * @author little Rathi
 *
 * @param <T> the concrete type
 */
public class Exact<T> extends TypeMatcher {

	@Override
	public boolean check(final Class<?> type, final Class<?> parentType) {
		return this.type.equals(type);
	}

}
