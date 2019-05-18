package de.bs.jdata.matcher;

import de.bs.jdata.matcher.init.GenericData;

/**
 * Marks a class in the generic hierarchy a class that can be instantiated and
 * be initialized through the {@link #init(GenericData)} method.
 * 
 * @author little Rathi
 *
 */
public interface MatcherElement {
	/**
	 * Initializer method, that get passed the generic elements that belongs to this
	 * class.
	 * 
	 * @param data that contains the generic elements that belongs to the class
	 */
	void init(final GenericData data);
}
