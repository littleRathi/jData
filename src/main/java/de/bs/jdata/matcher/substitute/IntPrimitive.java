package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.init.GenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;

/**
 * Represents the primitive type from {@link Integer}.
 * 
 * @author little Rathi
 *
 */
public class IntPrimitive extends SimpleSubstitute {

	@Override
	public GenericDataPart getGenericDataPart() {
		return new TypeGenericDataPart("int", int.class);
	}

}
