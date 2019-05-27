package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.MatcherElement;
import de.bs.jdata.matcher.init.GenericDataPart;

/**
 * Class for that represents something different, that cannot simple represent
 * through the direct element. Example are the primitive types, like int,
 * double, byte, etc.
 * 
 * @author little Rathi
 *
 */
public interface Substitute extends MatcherElement {
	/**
	 * Return the {@link GenericDataPart} that will contains the data, and return
	 * the wanted representation.
	 * 
	 * @return that represents the substitute with fitting data 
	 */
	GenericDataPart getGenericDataPart();
}
