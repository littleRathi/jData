package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.init.GenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;

/**
 * Represents the primitive type from {@link Byte}.
 * 
 * @author little Rathi
 *
 */
public class BytePrimitive extends SimpleSubstitute {

	@Override
	public GenericDataPart getGenericDataPart() {
		return new TypeGenericDataPart("byte", byte.class);
	}

}
