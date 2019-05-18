package de.bs.jdata.matcher;

import de.bs.jdata.matcher.custom.CustomMatcher;
import de.bs.jdata.matcher.init.GenericData;

/**
 * Matcher that allows a custom matcher, for more complex code for matching.
 * <p>
 * First a interface that use the Matcher interface:
 * <p>
 * <code>
 * public interface Converter<Matcher> { ... }
 * </code>
 * <p>
 * A class that implements CustomMatcher:
 * <p>
 * <code>
 * public class MyPersonalMatcher implements CustomMatcher { ... }
 * </code>
 * <p>
 * And the usage:
 * <p>
 * <code>
 * public class SpecailConverter implements Converter<UseCustom<MyPersonalMatcher>> { ... }
 * </code>
 * @author little Rathi
 *
 * @param <H> is the custom handler, if the mostly simpler {@link Type} matcher is not enough
 */
public class UseCustom<H extends CustomMatcher> implements Matcher {
	private H customMatcher;

	@Override
	public void init(final GenericData data) {
		customMatcher = (H) data.getMatcher(0, CustomMatcher.class);
	}

	@Override
	public boolean check(final Class<?> type, final Class<?> parentType) {
		return customMatcher.check(type, parentType);
	}

}
