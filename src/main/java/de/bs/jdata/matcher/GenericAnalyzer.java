package de.bs.jdata.matcher;

/**
 * Class that analyze a {@link String} as a type definition with generic and
 * generates a object hierarchy for further processing of the generic elements.
 * 
 * @author little Rathi
 *
 */
public class GenericAnalyzer {
	/**
	 * Will create a {@link GenericElement} hierarchy for the given parameter that
	 * represents a type definition with generic in a {@link String}
	 * 
	 * @param typeAsString the complete {@link String} of the generic (no cuts or
	 *                     substrings)
	 * @return created {@link GenericElement} or <code>null</code> if a error
	 *         occurred or no generic is present
	 */
	public GenericElement getGenericStructure(final String typeAsString) {
		GenericElement genericElement = null;
		if (containsGeneric(typeAsString)) {
			int index = 0;

			for (; index < typeAsString.length(); index++) {
				if (typeAsString.charAt(index) == '<') {
					genericElement = new GenericElement(typeAsString.substring(0, index).strip());

					index = recursiveConstructGenericStructure(genericElement, index + 1, typeAsString);
				}
			}
		}
		return genericElement;
	}

	/**
	 * Recursive method for extracting generic elements. Works on the the characters
	 * <code>,</code>, <code>&lt;</code> and <code>&gt;<code>. It should be in one
	 * line, without any comments and line breaks.
	 * 
	 * @param parentElement parent element, where in this method new extracted
	 *                      {@link GenericElement} elements gets added
	 * @param index         position within the given generic {@link String}
	 * @param typeAsString  the complete {@link String} of the generic (no cuts or
	 *                      substrings)
	 * @return the position where caller method can return with processing
	 */
	private int recursiveConstructGenericStructure(final GenericElement parentElement, final int index,
			final String typeAsString) {
		int lastChangeIndex = index;
		int localIndex = index;

		char character;
		for (; localIndex < typeAsString.length(); localIndex++) {
			character = typeAsString.charAt(localIndex);
			if (character == ',') {
				if (lastChangeIndex < localIndex) {
					parentElement.add(create(typeAsString, lastChangeIndex, localIndex));
				}
				lastChangeIndex = localIndex + 1;
			} else if (character == '<') {
				GenericElement genericElement = create(typeAsString, lastChangeIndex, localIndex);

				parentElement.add(genericElement);
				localIndex = recursiveConstructGenericStructure(genericElement, localIndex + 1, typeAsString);
				lastChangeIndex = localIndex + 1;
			} else if (character == '>') {
				if (lastChangeIndex < localIndex) {
					parentElement.add(create(typeAsString, lastChangeIndex, localIndex));
				}
				break;
			}
		}

		return localIndex;
	}

	/**
	 * Method for simply creating a {@link GenericElement}, as a shortcut.
	 * 
	 * @param typeAsString the complete {@link String} of the generic (no cuts or
	 *                     substrings)
	 * @param beginIndex   the element name starts in the typeAsString parameter
	 * @param endIndex     the element name ends in the typeAsString parameter
	 * @return the created object with the given element name (whic
	 */
	private GenericElement create(final String typeAsString, final int beginIndex, final int endIndex) {
		return new GenericElement(typeAsString.substring(beginIndex, endIndex).strip());
	}

	/**
	 * Simply checks if a {@link String} contains a <code>&lt;</code> to identify if
	 * it contains generic elements.
	 * 
	 * @param string that will be checked
	 * @return <code>true</code> if the passed {@link String} argument contains a
	 *         <code>&lt;</code>, otherwise <code>false</code>
	 */
	private boolean containsGeneric(final String string) {
		return string.contains("<");
	}

}
