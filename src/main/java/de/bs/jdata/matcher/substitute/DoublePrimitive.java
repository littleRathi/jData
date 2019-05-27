package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.init.GenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;

/**
 * Represents the primitive type from {@link Double}.
 * 
 * @author little Rathi
 *
 */
public class DoublePrimitive extends SimpleSubstitute {

	@Override
	public GenericDataPart getGenericDataPart() {
		return new TypeGenericDataPart("double", double.class);
	}

}
