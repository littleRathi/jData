package de.bs.jdata.matcher;

import java.util.LinkedList;
import java.util.List;

/**
 * Element that is used as a container for generic element while the analyze
 * process in {@link GenericAnalyzer} class and return these for further
 * processing.
 * 
 * @author little Rathi
 *
 */
public class GenericElement {
	private String elementName;
	private List<GenericElement> generics = new LinkedList<GenericElement>();

	/**
	 * Constructor that expect the name and amount of other {@link GenericElement}
	 * to create a nested structure.
	 * 
	 * @param elementName name of this class
	 * @param elements    amount of elements to create a nested structure
	 */
	public GenericElement(final String elementName, final GenericElement... elements) {
		this(elementName);
		for (final GenericElement element : elements) {
			generics.add(element);
		}
	}

	/**
	 * Simple constructor that only expect only the name of this element.
	 * 
	 * @param elementName name of this class
	 */
	public GenericElement(final String elementName) {
		this.elementName = elementName;
	}

	public String getElementName() {
		return elementName;
	}

	public void add(final GenericElement genericElement) {
		generics.add(genericElement);
	}

	public List<GenericElement> getGenerics() {
		return generics;
	}

	@Override
	public String toString() {
		return toString("", "");
	}

	private String toString(final String indent, final String newLine) {
		StringBuffer sb = new StringBuffer(newLine).append(indent).append("[").append(elementName).append("]");
		for (GenericElement generic : generics) {
			sb.append(generic.toString(indent + "    ", "\n"));
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || object.getClass() != getClass()) {
			return false;
		}
		GenericElement element = (GenericElement) object;

		if (!elementName.equals(element.getElementName())) {
			return false;
		}

		List<GenericElement> elementList = element.getGenerics();
		if (generics.size() != elementList.size()) {
			return false;
		}

		for (int i = 0; i < generics.size(); i++) {
			if (!generics.get(i).equals(elementList.get(i))) {
				return false;
			}
		}

		return true;
	}

}
