package de.bs.jdata.manager;

import java.util.Arrays;
import java.util.List;

import de.bs.jdata.JData;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.object.ObjectExtractor;
import de.bs.jdata.manager.object.ObjectManagerBuilder;
import de.bs.jdata.tracking.CallerEntry;
import de.bs.jdata.tracking.StackTraceElementEntry;
import de.bs.jdata.tracking.Tracking;
import de.bs.jdata.transformator.Transformator;

public class DataManager {
	public static final String IDENTIFIER = "defaultDataManager";
	
	private final List<String> unallowedPackages = Arrays.asList("java.*", "javax.*");
	private final Tracking tracker = new Tracking();

	private final ObjectExtractor extractor = new ObjectExtractor();
	
	public DataManager addConverter(final Class<? extends TypeConverter> converter) {
		return this;
	}

	// to define if parameter type should be Transformator or Class<? Extends
	// Transformator>
	public DataManager addTransformator(final Transformator transformator) {
		return this;
	}

	public <T> ObjectManagerBuilder<T> forObjectClass(final Class<T> objectClass) {
		Tracking builderTracker = new Tracking(tracker);

		StackTraceElement caller = Tracking.findCallerOf(JData.class, DataManager.class);
		builderTracker.add(new StackTraceElementEntry(caller));
		builderTracker.add(new CallerEntry(this, IDENTIFIER, "forObjectClass", "objectClass",
				(objectClass == null ? null : objectClass.getName())));

		checkIfClassIsValid(objectClass, builderTracker);

		/*
		 * Zentrale Stelle, in der die Objektdefinitionen gehalten werden. Diese
		 * erstellt auch die Objektdefinitionen, falls noch keine existiert. Möglihckeit
		 * eine eigene Klasse *Constructor (z. B. ObjectConstructor,
		 * PropertyConstructor,...). Diese kümmern sich um die Erzeugung der jeweiligen
		 * Elemente.
		 * 
		 * this.private ObjectConstructor oc = new OC();
		 * ObjectDefinition<T> def = oc.get(objectClass);
		 * 		im get: return from map or return this(oc).construct(objectClass)
		 * 			im oc.construct wird dann fd:FieldConstructor.construct(?) aufgerufen
		 * 				im fc.construct wird dann ggf. oc.construct wieder aufgerufen
		 * 
		 * FieldManager müssen auch auf ObjectDefinitions verweisen können (sogar schon bedacht?)
		 * Hinweis (schon längst so definiert, erinnerung): FieldManager, nutzen Converter!
		 */
		ObjectDefinition<T> definition = extractor.extract(objectClass);

		return new ObjectManagerBuilder<T>(definition, builderTracker);
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
