package de.bs.jdata.manager.element;

public abstract class ElementManager {
	public abstract Object get();
	
	public abstract void set(final Object value);
	
	public abstract String getAsString();
	
	public abstract void setAsString(final String value);
}
