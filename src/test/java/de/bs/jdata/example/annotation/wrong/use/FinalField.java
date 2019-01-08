package de.bs.jdata.example.annotation.wrong.use;

import de.bs.jdata.JDataField;

/**
 * Wrong use: a JData field cannot be final and will results in an exception.
 * 
 * @author little Rathi
 *
 */
public class FinalField {
	@JDataField
	private final String stringValue;

	public FinalField() {
		stringValue = "abc";
	}

	public String getStringValue() {
		return stringValue;
	}
}
