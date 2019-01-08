package de.bs.jdata.example.annotation.wrong.use;

import de.bs.jdata.JDataField;
import de.bs.jdata.JDataMode;
import de.bs.jdata.JDataObject;

/**
 * Wrong use: a JData field cannot be static and will result in an exception.
 * 
 * @author little Rathi
 *
 */
@JDataObject(mode = JDataMode.USE_ONLY_ANNOTATIONS)
public class StaticField {
	@JDataField(name = "failsStatic", desc = "Field cannot be static")
	private static int someValue;

	public static int getSomeValue() {
		return someValue;
	}

	public static void setSomeValue(int someValue) {
		StaticField.someValue = someValue;
	}
}
