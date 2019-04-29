package de.bs.jdata.tracking.examples;

public class Caller01ExampleMulti {
	public StackTraceElement callThisFirst() {
		return new ExampleMulti().test(Caller01ExampleMulti.class, Caller02ExampleMulti.class);
	}
	
	public StackTraceElement callThisLast() {
		return new ExampleMulti().test(Caller02ExampleMulti.class, Caller01ExampleMulti.class);
	}
	
	public StackTraceElement callThisFirstIndirect() {
		return new ExampleMulti().testIndirect(Caller01ExampleMulti.class, Caller02ExampleMulti.class);
	}
	
	public StackTraceElement callThisLastIndirect() {
		return new ExampleMulti().testIndirect(Caller02ExampleMulti.class, Caller01ExampleMulti.class);
	}
}
