package de.bs.jdata.manager.property;

/**
 * 
 * TODO: check method names (Signatures)
 * 
 * @author little Rathi
 *
 * @param <FT> is the field type that this class is managing
 * @param <OT> is the class type that of the property
 */
public abstract class PropertyManager<FT, OT> {
	public abstract Object get(final OT object);
	
	public abstract void set(final OT object, final Object value);
	
	public abstract String getAsString(final OT object);
	
	public abstract void setAsString(final OT object, final String value);
	
	public abstract Class<FT> getFieldType();
}
