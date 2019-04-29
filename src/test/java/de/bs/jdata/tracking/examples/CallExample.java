package de.bs.jdata.tracking.examples;

public class CallExample {
	public StackTraceElement call() {
		return new Example().test();
	}
	
	public StackTraceElement callOverIndirect() {
		return new Example().indrectCall();
	}
}
