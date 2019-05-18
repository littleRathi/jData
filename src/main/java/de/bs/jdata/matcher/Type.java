package de.bs.jdata.matcher;

import de.bs.jdata.matcher.init.GenericData;
import de.bs.jdata.matcher.type.TypeMatcher;

/**
 * Matcher that simple for type that allows some simple expressions in generic
 * forms, targeting the types, like {@link String}, {@link Integer}, ...
 * <p>
 * First a interface that use the Matcher interface:
 * <p>
 * <code>
 * public interface Converter<Matcher> { ... }
 * </code>
 * <p>
 * That will be implement in a concrete implementation like:
 * <p>
 * <code>
 * public class MyConverter implements Converter<Type<Is<Double, OrOf<Float>>>> { ... }
 * </code>
 * 
 * @author little Rathi
 *
 * @param <TM> takes a matcher which aims directly to the types for simple use
 */
public class Type<TM extends TypeMatcher> implements Matcher {
	private TypeMatcher typeMatcher;

	@Override
	public void init(final GenericData data) {
		typeMatcher = data.getMatcher(0, TypeMatcher.class);
	}

	@Override
	public boolean check(final Class<?> type, final Class<?> parentType) {
		return typeMatcher.check(type, parentType);
	}

}
