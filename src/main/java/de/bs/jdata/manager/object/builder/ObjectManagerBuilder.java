package de.bs.jdata.manager.object.builder;

import java.util.LinkedList;
import java.util.List;

import de.bs.jdata.configurator.Configurator;
import de.bs.jdata.converter.ConverterRegistry;
import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.manager.ObjectDefinition;
import de.bs.jdata.manager.object.ObjectManager;
import de.bs.jdata.tracking.Tracking;


/**
 * 
 * @author little Rathi
 *
 * @param <T>
 */
public class ObjectManagerBuilder<T> {
	private ConverterRegistry converterRegistry;
	
	private ObjectDefinition<T> objectDefinition;
	private Tracking tracker;
	private List<Configurator> configs = new LinkedList<Configurator>();
	
	public ObjectManagerBuilder(final ObjectDefinition<T> objectDefinition, final ConverterRegistry converterRegistry, final Tracking tracker) {
		this.objectDefinition = objectDefinition;
		this.converterRegistry = converterRegistry;
		this.tracker = tracker;
		
		buildObjectManager();
	}

	/**
	 * Creates the {@link ObjectManager}.
	 * 
	 * 
	 * TODO: Logic
	 * 1) Create the Converter for given ObjectDefinition
	 * 2) Go through the fields 
	 * 		???
	 */
	private void buildObjectManager() {
		
		TypeConverter<?> thisConverter = converterRegistry.getConverterFor(objectDefinition.getDefinedObjekt(), null);
		
		
	}

	public ObjectManagerBuilder<T> bind(final Configurator configurator) {
		configs.add(configurator);
		return this;
	}
	
	public ObjectManager<T> build() {
		return null;
	}
}
