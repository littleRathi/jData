package de.bs.jdata.manager;

import java.util.Arrays;
import java.util.List;

import de.bs.jdata.JData;
import de.bs.jdata.converter.ConverterRegistry;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.object.ObjectExtractor;
import de.bs.jdata.manager.object.ObjectManager;
import de.bs.jdata.manager.object.builder.ObjectManagerBuilder;
import de.bs.jdata.tracking.CalledEntry;
import de.bs.jdata.tracking.StackTraceElementEntry;
import de.bs.jdata.tracking.Tracking;
import de.bs.jdata.transformator.Transformator;

public class DataManager {
	public static final String IDENTIFIER = "defaultDataManager";

	private ConverterRegistry converterRegistry = new ConverterRegistry();
	
	private final List<String> unallowedPackages = Arrays.asList("java.*", "javax.*");
	private final Tracking tracker = new Tracking();

	private final ObjectExtractor extractor = new ObjectExtractor();
	
	/**
	 * Adds a TypeConverter to JData. <code>null</code> or an already existing
	 * converter leads to an exception.
	 *
	 * @param converter that will be added, that last converter will be first checked
	 * @return the active DataManager
	 */
	// TODO: rethinking of throws exception here? good would be throw only on error or warning, these two would be more warning
	public DataManager addConverter(final Class<? extends TypeConverter<?>> converter) {
		tracker.add(new StackTraceElementEntry(DataManager.class, JData.class));
		tracker.add(new CalledEntry(this, IDENTIFIER, "addConverter", "converter", (converter == null ? null : converter.getName())));
		if (converter == null) {
			throw new IllegalArgumentException("Pass parameter for 'converter' was null: " + tracker.toString());
		}
		if (!converterRegistry.addConverter(converter)) {
			throw new IllegalArgumentException("Could not add Converter '" + converter.getClass() + ": " + tracker.toString()); // TODO: Exception
		}
		return this;
	}

	// to define if parameter type should be Transformator or Class<? Extends
	// Transformator>
	public DataManager addTransformator(final Transformator transformator) {
		return this;
	}

	/**
	 * Method to create a build a {@link ObjectManager} to the given {@link Class}
	 * 
	 * @param objectClass
	 * @return
	 */
	public <T> ObjectManagerBuilder<T> forObjectClass(final Class<T> objectClass) {
		Tracking builderTracker = new Tracking(tracker);

		builderTracker.add(new StackTraceElementEntry(JData.class, DataManager.class));
		builderTracker.add(new CalledEntry(this, IDENTIFIER, "forObjectClass", "objectClass",
				(objectClass == null ? null : objectClass.getName())));

		checkIfClassIsValid(objectClass, builderTracker);

		ObjectDefinition<T> definition = extractor.extract(objectClass);

		return new ObjectManagerBuilder<T>(definition, converterRegistry, builderTracker);
	}

	private void checkIfClassIsValid(final Class<?> objectClass, final Tracking builderTracker) {
		if (objectClass == null) {
			throw new IllegalArgumentException("null as parameter is not allowed: " + builderTracker.toString());
		}
		String fullQualifiedName = objectClass.getName();
		for (final String pattern : unallowedPackages) {
			String regex = pattern.replaceAll("\\*", ".*?");
			if (fullQualifiedName.matches(regex)) {
				throw new IllegalArgumentException("class '" + fullQualifiedName + "' is not allowed "
						+ " because it matches following package pattern: '" + pattern + "': "
						+ builderTracker.toString());
			}
		}
	}
}
