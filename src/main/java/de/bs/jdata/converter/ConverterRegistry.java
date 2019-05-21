package de.bs.jdata.converter;

import java.util.HashSet;
import java.util.Set;

import de.bs.jdata.matcher.Matcher;
import de.bs.jdata.matcher.MatcherRegistry;
import de.bs.jdata.util.ClassUtil;

/**
 * Registry class for handling {@link TypeConverter}. Allows add
 * {@link TypeConverter}, but only one time and add them to the chain for
 * finding fitting {@link TypeConverter} for passed {@link Class} object to the
 * specific method.
 * 
 * @author little Rathi
 *
 */
public class ConverterRegistry {
	private MatcherRegistry matcherRegistry = new MatcherRegistry();
	private Set<Class<?>> registeredConverter = new HashSet<Class<?>>();

	private ConverterHandler<?> chainHandler = new ConverterHandler<TypeConverter<?>>(null, null, null) {
		@Override
		public TypeConverter<?> findConverter(final Class<?> type, final Class<?> parentType) {
			return null;
		}
	};

	/**
	 * Add a {@link TypeConverter} to the registry. Extracting the {@link Matcher}
	 * and add it to the chain handling. If a {@link TypeConverter} is already
	 * registered, it will be ignored <code>false</code> will be returned.
	 * <p>
	 * Remember that the order of adding {@link TypeConverter} matters, especially
	 * when there are more then one {@link TypeConverter} that matches the given
	 * {@link Class} types in {@link #getConverterFor(Class, Class)}, the last added
	 * will match.
	 * 
	 * @param converterClass as a {@link Class} object, instantiating will be done
	 *                       internally
	 * @return <code>true</code> if the {@link TypeConverter} what successfully
	 *         added
	 */
	public <T extends TypeConverter<?>> boolean addConverter(final Class<T> converterClass) {
		if (registeredConverter.contains(converterClass)) {
			return false;
		}
		registeredConverter.add(converterClass);

		Matcher converterMatcher = matcherRegistry.getMatcherFrom(converterClass);

		chainHandler = new ConverterHandler<T>(converterClass, converterMatcher, chainHandler);

		return true;
	}

	/**
	 * Method that will be passed {@link Class} objects and the registry will return
	 * a {@link TypeConverter} that match the passed data or return
	 * <code>null</code>.
	 * 
	 * @param type       is the main type and cannot be <code>null</code>, all
	 *                   predefined {@link TypeConverter} use only this parameter
	 * @param parentType defines the enclosed type and can be <code>null</code>
	 * @return a instance of the {@link TypeConverter} that matched the parameter
	 *         given to this method
	 */
	public TypeConverter<?> getConverterFor(final Class<?> type, final Class<?> parentType) {
		if (chainHandler != null) {
			return chainHandler.findConverter(type, parentType);
		}
		return null;
	}

	/**
	 * Internal helper class, that takes the {@link TypeConverter} {@link Class}
	 * object and the {@link Matcher} that belongs to the {@link TypeConverter}
	 * 
	 * @author little Rathi
	 *
	 * @param <T> that this class is only handling {@link TypeConverter}
	 */
	private class ConverterHandler<T extends TypeConverter<?>> {
		private Matcher matcher;
		private Class<T> converter;

		private ConverterHandler<?> next;

		public ConverterHandler(final Class<T> converter, final Matcher matcher, final ConverterHandler<?> next) {
			this.matcher = matcher;
			this.converter = converter;
			this.next = next;
		}

		/**
		 * Use the Matcher object the identify if the stored {@link TypeConverter} is a
		 * match. Method is similar to {@link ConverterRegistry#getConverterFor(Class, Class)}.
		 * 
		 * @param type for description see {@link ConverterRegistry#getConverterFor(Class, Class)}
		 * @param parentType for description see {@link ConverterRegistry#getConverterFor(Class, Class)}
		 * @return for description see {@link ConverterRegistry#getConverterFor(Class, Class)}
		 */
		public TypeConverter<?> findConverter(final Class<?> type, final Class<?> parentType) {
			if (matcher.check(type, parentType)) {
				return ClassUtil.instantiate(converter);
			}
			return next.findConverter(type, parentType);
		}
	}
}
