package de.bs.jdata.matcher.init;

import java.util.List;

import de.bs.jdata.matcher.MatcherElement;

/**
 * Data class for {@link MatcherElement#init(GenericData)} method, it contains
 * all parts that are assigned to the given {@link MatcherElement}.
 * 
 * @author little Rathi
 *
 */
public class GenericData {
	private GenericDataPart[] genericDataParts;

	/**
	 * Simple constructor that takes the parts that will be managed through this
	 * class
	 * 
	 * @param genericDataParts that the concrete object will manage
	 */
	public GenericData(final GenericDataPart... genericDataParts) {
		this.genericDataParts = genericDataParts;
	}

	/**
	 * Number of parts that the object contains, like/similar {@link List#size()}.
	 * 
	 * @return number of parts in this objects
	 */
	public int size() {
		return genericDataParts.length;
	}

	/**
	 * Functionality see at {@link GenericDataPart#getGenericType()}.
	 * 
	 * @param index position of the {@link GenericDataPart} that will be used
	 */
	public String getGenericType(final int index) {
		return genericDataParts[index].getGenericType();
	}

	/**
	 * Functionality see at {@link GenericDataPart#getTypeName()}.
	 * 
	 * @param index position of the {@link GenericDataPart} that will be used
	 */
	public String getTypeName(final int index) {
		return genericDataParts[index].getTypeName();
	}

	/**
	 * Functionality see at {@link GenericDataPart#getType()}.
	 * 
	 * @param index position of the {@link GenericDataPart} that will be used
	 */
	public Class<?> getType(final int index) {
		return genericDataParts[index].getType();
	}

	/**
	 * Functionality see at {@link GenericDataPart#getMatcher(Class)}.
	 * 
	 * @param index position of the {@link GenericDataPart} that will be used
	 */
	public <T extends MatcherElement> T getMatcher(final int index, final Class<T> matcherType) {
		return genericDataParts[index].getMatcher(matcherType);
	}

}
