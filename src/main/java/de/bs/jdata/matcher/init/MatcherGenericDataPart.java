package de.bs.jdata.matcher.init;

import java.lang.reflect.Type;

import de.bs.jdata.matcher.MatcherElement;

/**
 * Generic data part, that represents a matcher {@link MatcherElement}, that is
 * already instantiated for use.
 * 
 * @author little Rathi
 *
 */
public class MatcherGenericDataPart implements GenericDataPart {
	private Type type;
	private MatcherElement initializer;

	public MatcherGenericDataPart(final Type type, final MatcherElement initializer) {
		this.type = type;
		this.initializer = initializer;
	}

	@Override
	public String getGenericType() {
		return type.getTypeName();
	}

	@Override
	public String getTypeName() {
		return initializer.getClass().getName();
	}

	@Override
	public Class<?> getType() {
		return initializer.getClass();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends MatcherElement> T getMatcher(Class<T> handlerType) {
		if (handlerType.isAssignableFrom(initializer.getClass())) {
			return (T) initializer;
		}
		throw new RuntimeException(initializer.getClass() + " cannot be cast to " + handlerType);
		// TODO: define correct exception type
	}

}
