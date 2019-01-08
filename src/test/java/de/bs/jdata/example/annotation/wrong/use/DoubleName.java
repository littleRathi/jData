package de.bs.jdata.example.annotation.wrong.use;

import de.bs.jdata.JDataField;
import de.bs.jdata.JDataMode;
import de.bs.jdata.JDataObject;

/**
 * Wrong use: two fields get through the annotation the same name, and results
 * in an exception.
 * 
 * @author little Rathi
 *
 */
@JDataObject(mode = JDataMode.USE_ONLY_ANNOTATIONS)
public class DoubleName {
	@JDataField(name = "uniqueName")
	private String stringValue;
	@JDataField(name = "uniqueName")
	private int intValue;

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
}
