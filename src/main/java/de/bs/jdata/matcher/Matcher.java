package de.bs.jdata.matcher;

/**
 * Main element from the matcher package. It marks the root element, but cannot
 * used directly, for that use {@link Type} or {@link UseCustom}.
 * 
 * TODO: Should be Generic, to ensure that the implementing class has minimum one generic element. See Type and UseCustom
 * 
 * @author little Rathi
 *
 */
public interface Matcher extends MatcherElement {
	/**
	 * Method that checks if given parameters matches to this {@link Matcher}.
	 * 
	 * @param type that is the main element for the validation
	 * @param parentType additional element for the validation, it can be <code>null</code>
	 * @return <code>true</code> if the validation is successful, otherwise <code>false</code>
	 */
	boolean check(final Class<?> type, final Class<?> parentType);
}
