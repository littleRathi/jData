package de.bs.jdata.manager.property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import de.bs.jdata.JDataMode;

public class PropertyExtractor {
	public <OT> Map<String, PropertyManager<?, OT>> extract(final JDataMode mode) {
		
		
		return null;
	}
	
	private <FT, OT> PropertyManager<FT, OT> constructFromField(final Field propertyField) {
		return null;
	}
	
	private <FT, OT> PropertyManager<FT, OT> constructFromSetter(final Method propertyMethod) {
		return null;
	}
}
