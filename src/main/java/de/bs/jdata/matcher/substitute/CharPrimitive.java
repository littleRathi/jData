package de.bs.jdata.matcher.substitute;

import de.bs.jdata.matcher.init.GenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;

/**
 * Represents the primitive type from {@link Char}.
 * 
 * @author little Rathi
 *
 */
public class CharPrimitive extends SimpleSubstitute {

	@Override
	public GenericDataPart getGenericDataPart() {
		return new TypeGenericDataPart("char", char.class);
	}

}
