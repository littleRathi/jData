package de.bs.jdata.matcher.type;

import de.bs.jdata.matcher.init.GenericData;

/**
 * Matches if the first parameter matches the first generic parameter
 * <code>T</code> or the matcher given in the second generic parameter
 * <code>TM</code>.
 * 
 * @author little Rathi
 *
 * @param <T> the concrete type
 * @param <TM> a matcher that is {@link OrOf} or a subclass of it
 */
public class Or<T, TM extends OrOf<?>> extends OrOf<T> {
	private OrOf<?> orOf;

	@Override
	public void init(GenericData data) {
		super.init(data);
		orOf = data.getMatcher(1, OrOf.class);
	}

	@Override
	public boolean check(final Class<?> type, final Class<?> parentType) {
		return this.type.equals(type) || orOf.check(type, parentType);
	}

}
