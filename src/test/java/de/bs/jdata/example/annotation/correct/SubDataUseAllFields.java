package de.bs.jdata.example.annotation.correct;

import de.bs.jdata.JDataFieldIgnore;
import de.bs.jdata.JDataMode;

/**
 * Represents a sub data object for the {@link WithAnnotation} data object, with
 * the {@link JDataMode#USE_ALL_FIELDS} mode.
 * 
 * @author little Rathi
 *
 */
public class SubDataUseAllFields {
	private int onlyOneField;

	@JDataFieldIgnore(desc = "This annotated field will be ignored, with this annotation")
	private String ignoredField;

	public int getOnlyOneField() {
		return onlyOneField;
	}

	public void setOnlyOneField(int onlyOneField) {
		this.onlyOneField = onlyOneField;
	}

	public String getIgnoredField() {
		return ignoredField;
	}

	public void setIgnoredField(String ignoredField) {
		this.ignoredField = ignoredField;
	}
}
