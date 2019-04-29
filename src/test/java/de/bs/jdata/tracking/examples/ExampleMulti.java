package de.bs.jdata.tracking.examples;

import de.bs.jdata.tracking.Tracking;

public class ExampleMulti {
	public StackTraceElement test(Class<?>... clazzes) {
		return Tracking.findCallerOf(clazzes);
	}
	
	public StackTraceElement testIndirect(Class<?>... clazzes) {
		return test(clazzes);
	}
}
