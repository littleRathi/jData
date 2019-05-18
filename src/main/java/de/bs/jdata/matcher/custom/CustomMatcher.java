package de.bs.jdata.matcher.custom;

import de.bs.jdata.matcher.Matcher;
import de.bs.jdata.matcher.MatcherElement;
import de.bs.jdata.matcher.UseCustom;

/**
 * Interface for {@link UseCustom} that simply force the
 * {@link #check(Class, Class)} method, like {@link Matcher#check(Class, Class)}
 * 
 * @author little Rathi
 *
 */
public interface CustomMatcher extends MatcherElement {
	/**
	 * See {@link Matcher#check(Class, Class)}
	 */
	boolean check(final Class<?> type, final Class<?> parentType);
}
