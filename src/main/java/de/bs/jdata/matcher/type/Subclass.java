package de.bs.jdata.matcher.type;

/**
 * Matches if the first parameter from {@link #check(Class, Class)} is a
 * subclass of the given generic parameter <code>T</code>, no match if the parameter is
 * the same as the generic parameter <code>T</code>.
 * 
 * @author little Rathi
 *
 * @param <T> the concrete type
 */
public class Subclass<T> extends TypeMatcher {

	@Override
	public boolean check(final Class<?> type, final Class<?> parentType) {
		if (this.type.equals(type)) {
			return false;
		}
		return this.type.isAssignableFrom(type);
	}

}
