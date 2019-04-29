package de.bs.jdata.tracking.examples;

import de.bs.jdata.tracking.Tracking;

public class FailingExample {
	public StackTraceElement test() {
		return Tracking.findCallerOf(ExampleMulti.class);
	}
}
