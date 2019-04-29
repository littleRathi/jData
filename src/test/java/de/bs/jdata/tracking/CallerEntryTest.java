package de.bs.jdata.tracking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class CallerEntryTest {
	private CallerEntry testee;
	
	@Test(expected=NullPointerException.class)
	public void testCtorSourceNull() {
		testee = new CallerEntry(null, "id", "called", "varName", "varType");
	}
	
	@Test
	public void testCtorFine() {
		testee = new CallerEntry(new String(), "id", "call", "varName", "varType");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), notNullValue());
	}
	
	@Test
	public void testToStringNoParameter() {
		testee = new CallerEntry(new String(), "id", "call");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call()"));
	}
	
	@Test
	public void testToStringOneParameter() {
		testee = new CallerEntry(new String(), "id", "call", "var0", "type0");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call(var0: type0)"));
	}
	
	@Test
	public void testToStringMoreParameter() {
		testee = new CallerEntry(new String(), "id", "call", "var0", "type0", "var1", "type1");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call(var0: type0, var1: type1)"));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testToStringUnfittingParameter() {
		testee = new CallerEntry(new String(), "id", "call", "var0", "type0", "var1");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call(var0: type0, var1: type1)"));
	}
}
