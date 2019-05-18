package de.bs.jdata.matcher.type;

import de.bs.jdata.matcher.MatcherElement;
import de.bs.jdata.matcher.init.GenericData;

/**
 * Matches if the first parameter matches the generic parameter <code>T</code>.
 * 
 * @author little Rathi
 *
 * @param <T> concrete type
 */
public class OrOf<T> implements MatcherElement {
	protected Class<?> type;

	@Override
	public void init(GenericData data) {
		type = data.getType(0);
	}

	public boolean check(final Class<?> type, final Class<?> parentType) {
		return this.type.equals(type);
	}

}
