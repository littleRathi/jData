package de.bs.jdata.example.no.annotations.no.fields;

import de.bs.jdata.JDataField;
import de.bs.jdata.JDataMode;

/**
 * These fields are not allowed as {@link JDataField} and will be ignored when
 * {@link JDataMode#USE_ALL_FIELDS} is active. If the annotation
 * {@link JDataField} is used on such a field, a exception is thrown.
 * 
 * @author little Rathi
 *
 */
public class NoFields {
	private static String stringValue;
	private volatile int intValue;
	private final double doubleValue;

	public NoFields() {
		doubleValue = 3.4d;
	}

	public static String getStringValue() {
		return stringValue;
	}

	public static void setStringValue(String stringValue) {
		NoFields.stringValue = stringValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}
}
