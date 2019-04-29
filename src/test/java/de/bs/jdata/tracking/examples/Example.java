package de.bs.jdata.tracking.examples;

import de.bs.jdata.tracking.Tracking;

public class Example {
	public StackTraceElement test() {
		return Tracking.findCallerOf(Example.class);
	}
	
	public StackTraceElement indrectCall() {
		return test();
	}
}
