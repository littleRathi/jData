package de.bs.jdata.example.annotation.correct;

import java.net.MalformedURLException;
import java.net.URL;

import de.bs.jdata.JDataField;
import de.bs.jdata.JDataMode;
import de.bs.jdata.JDataObject;

@JDataObject(name = "Annotation Example", desc = "Example using all existing annotations of this library.. Meaningful to this example")
public class WithAnnotation {
	@JDataField(name = "abc", desc = "some description to this field")
	private String field1;
	@JDataField(name = "def", desc = "description, that explains the difference to the other fields")
	private int field2;
	private URL field3;
	@JDataField(name = "subDataObject", submode = JDataMode.USE_ALL_FIELDS)
	private SubDataUseAllFields field4;

	@JDataField(name = "ghi", desc = "example that use the @JDataField annotation on a set method instead on a field")
	public void setField3(final String urlAsString) throws MalformedURLException {
		// transform the field
		field3 = new URL(urlAsString);
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public int getField2() {
		return field2;
	}

	public void setField2(int field2) {
		this.field2 = field2;
	}

	public SubDataUseAllFields getField4() {
		return field4;
	}

	public void setField4(SubDataUseAllFields field4) {
		this.field4 = field4;
	}

	public URL getField3() {
		return field3;
	}
}
