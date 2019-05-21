package de.bs.jdata.matcher.init;

import de.bs.jdata.matcher.MatcherElement;

/**
 * Generic data part, that represents a simple type.
 * 
 * @author little Rathi
 *
 */
public class TypeGenericDataPart implements GenericDataPart {
	private String type;
	private Class<?> clazz;

	public TypeGenericDataPart(final String type, final Class<?> clazz) {
		this.type = type;
		this.clazz = clazz;
	}

	@Override
	public String getGenericType() {
		return type;
	}

	@Override
	public String getTypeName() {
		return clazz.getName();
	}

	@Override
	public Class<?> getType() {
		return clazz;
	}

	@Override
	public <T extends MatcherElement> T getMatcher(Class<T> handlerType) {
		throw new RuntimeException(
				"This is " + TypeGenericDataPart.class.getName() + ", not " + MatcherGenericDataPart.class);
		// TODO: define correct exception type
	}

}
