package de.bs.jdata.matcher.init;

import de.bs.jdata.matcher.MatcherElement;

public interface GenericDataPart {

	/**
	 * Return the generic parts as String, like <code>MyClass&lt;Integer&gt;</code>.
	 * 
	 * @param index for which part the generic type should be returned for the
	 *              passed position
	 * @return the generic type or an empty {@link String}
	 */
	String getGenericType();

	/**
	 * The name of the type, simply {@link Class#getName()}.
	 * 
	 * @param index for which part the type name should be returned
	 * @return the type name of the {@link GenericDataPart} at the passed position
	 */
	String getTypeName();

	/**
	 * The Class of the given {@link GenericDataPart}.
	 * 
	 * @return the {@link Class} object for the given {@link GenericDataPart}
	 */
	Class<?> getType();

	/**
	 * Returns the matcher object, if the given class implements the
	 * {@link MatcherElement} interface.
	 * 
	 * @param matcherType to cast it given {@link Class} that implements
	 *                    {@link MatcherElement}
	 * @return the matcher object or throws an exception that
	 *         {@link GenericDataPart} does not contain a {@link MatcherElement}
	 */
	<T extends MatcherElement> T getMatcher(final Class<T> matcherType);
}
