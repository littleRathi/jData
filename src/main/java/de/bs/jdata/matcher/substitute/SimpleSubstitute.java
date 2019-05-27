package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.MatcherElement;
import de.bs.jdata.matcher.init.GenericData;

/**
 * Simple abstract class that does not need the
 * {@link MatcherElement#init(GenericData)} method and simply has a empty
 * implementation.
 * 
 * @author little Rathi
 *
 */
public abstract class SimpleSubstitute implements Substitute {

	@Override
	public void init(GenericData data) {
	}

}
