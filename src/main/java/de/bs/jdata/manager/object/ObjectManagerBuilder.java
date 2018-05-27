package de.bs.jdata.manager.object;

import de.bs.jdata.configurator.Configurator;
import de.bs.jdata.transformator.Transformator;

public class ObjectManagerBuilder<T> {
	public ObjectManagerBuilder<T> bind(final Configurator configurator) {
		return this;
	}
	
	// TODO: perhaps not necessary.
	public ObjectManagerBuilder<T> with(final Transformator transformator) {
		return this;
	}
	
	public ObjectManager<T> build() {
		return null;
	}
}
