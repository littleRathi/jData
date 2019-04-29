package de.bs.jdata.tracking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import de.bs.jdata.tracking.examples.CallExample;
import de.bs.jdata.tracking.examples.Caller01ExampleMulti;
import de.bs.jdata.tracking.examples.Example;
import de.bs.jdata.tracking.examples.FailingExample;

public class TrackingTest {
	@Test
	public void testFindWithExample() {
		Example example = new Example();

		StackTraceElement result = example.test();

		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(TrackingTest.class.getName()));
		assertThat(result.getMethodName(), equalTo("testFindWithExample"));
	}
	
	
	@Test
	public void testFindWithCallExample() {
		CallExample example = new CallExample();
		
		StackTraceElement result = example.call();
		
		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(CallExample.class.getName()));
		assertThat(result.getMethodName(), equalTo("call"));
	}
	
	@Test
	public void testFindWithExampleIndirect() {
		Example example = new Example();

		StackTraceElement result = example.indrectCall();

		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(TrackingTest.class.getName()));
		assertThat(result.getMethodName(), equalTo("testFindWithExampleIndirect"));
	}
	
	
	@Test
	public void testFindWithCallExampleIndirect() {
		CallExample example = new CallExample();
		
		StackTraceElement result = example.callOverIndirect();
		
		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(CallExample.class.getName()));
		assertThat(result.getMethodName(), equalTo("callOverIndirect"));
	}
	
	@Test
	public void testFindWithCall01AndCallFirst() {
		Caller01ExampleMulti example = new Caller01ExampleMulti();
		
		StackTraceElement result = example.callThisFirst();
		
		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(TrackingTest.class.getName()));
		assertThat(result.getMethodName(), equalTo("testFindWithCall01AndCallFirst"));
	}
	
	@Test
	public void testFindWithCall01AndCallLast() {
		Caller01ExampleMulti example = new Caller01ExampleMulti();
		
		StackTraceElement result = example.callThisLast();
		
		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(TrackingTest.class.getName()));
		assertThat(result.getMethodName(), equalTo("testFindWithCall01AndCallLast"));
	}
	
	@Test
	public void testFindWithCall01AndCallFirstIndirect() {
		Caller01ExampleMulti example = new Caller01ExampleMulti();
		
		StackTraceElement result = example.callThisFirstIndirect();
		
		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(TrackingTest.class.getName()));
		assertThat(result.getMethodName(), equalTo("testFindWithCall01AndCallFirstIndirect"));
	}
	
	@Test
	public void testFindWithCall01AndCallLastIndirect() {
		Caller01ExampleMulti example = new Caller01ExampleMulti();
		
		StackTraceElement result = example.callThisLastIndirect();
		
		assertThat(result, notNullValue());
		assertThat(result.getClassName(), equalTo(TrackingTest.class.getName()));
		assertThat(result.getMethodName(), equalTo("testFindWithCall01AndCallLastIndirect"));
	}
	
	@Test
	public void testFindReturnsNull() {
		FailingExample example = new FailingExample();
		
		StackTraceElement result = example.test();
		
		assertThat(result, nullValue());
	}
	// testen mit mehreren Übergebenen Class<?> bei Tracking.find
	// da der if(match) wohl noch nicht ganz korrekt ist!
}
