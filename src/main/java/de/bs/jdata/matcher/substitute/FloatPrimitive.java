package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.init.GenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;

/**
 * Represents the primitive type from {@link Float}.
 * 
 * @author little Rathi
 *
 */
public class FloatPrimitive extends SimpleSubstitute {

	@Override
	public GenericDataPart getGenericDataPart() {
		return new TypeGenericDataPart("float", float.class);
	}

}
