package de.bs.jdata.matcher.type;

import de.bs.jdata.matcher.MatcherElement;
import de.bs.jdata.matcher.init.GenericData;

/**
 * Simple abstract class for sharing same functionality between the
 * {@link TypeMatcher}.
 * 
 * @author little Rathi
 *
 */
public abstract class TypeMatcher implements MatcherElement {
	protected Class<?> type;

	@Override
	public void init(GenericData data) {
		type = data.getType(0);
	}

	public abstract boolean check(final Class<?> type, final Class<?> parentType);

}
