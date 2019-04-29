package de.bs.jdata.manager.object;

import de.bs.jdata.configurator.Configurator;
import de.bs.jdata.manager.ObjectDefinition;
import de.bs.jdata.tracking.Tracking;

public class ObjectManagerBuilder<T> {
	private ObjectDefinition<T> objectDefinition;
	private Tracking tracker;
	
	public ObjectManagerBuilder(final ObjectDefinition<T> objectDefinition, final Tracking tracker) {
		this.objectDefinition = objectDefinition;
		this.tracker = tracker;
	}

	public ObjectManagerBuilder<T> bind(final Configurator configurator) {
		return this;
	}
	
	public ObjectManager<T> build() {
		return null;
	}
}
