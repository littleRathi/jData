package de.bs.jdata.manager;

import java.util.HashMap;
import java.util.Map;

import de.bs.jdata.JDataMode;
import de.bs.jdata.manager.property.PropertyManager;

/**
 * 
 * @author little Rathi
 *
 * @param <OT> is the object type that this class represents
 */
public class ObjectDefinition<OT> {
	private Class<OT> definedObjekt;
	private Map<String, PropertyManager<?, OT>> fields = new HashMap<String, PropertyManager<?, OT>>();
	
	private String name;
	private JDataMode dataMode;
	private String desc;
	
	public Class<OT> getDefinedObjekt() {
		return definedObjekt;
	}
	
	public void setDefinedObjekt(Class<OT> definedObjekt) {
		this.definedObjekt = definedObjekt;
	}
	
	public Map<String, PropertyManager<?, OT>> getFields() {
		return fields;
	}
	
	public void setFields(Map<String, PropertyManager<?, OT>> fields) {
		this.fields = fields;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public JDataMode getDataMode() {
		return dataMode;
	}
	
	public void setDataMode(JDataMode dataMode) {
		this.dataMode = dataMode;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
