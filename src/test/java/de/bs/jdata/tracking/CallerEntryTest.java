package de.bs.jdata.tracking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class CallerEntryTest {
	private CalledEntry testee;
	
	@Test(expected=NullPointerException.class)
	public void testCtorSourceNull() {
		testee = new CalledEntry(null, "id", "called", "varName", "varType");
	}
	
	@Test
	public void testCtorFine() {
		testee = new CalledEntry(new String(), "id", "call", "varName", "varType");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), notNullValue());
	}
	
	@Test
	public void testToStringNoParameter() {
		testee = new CalledEntry(new String(), "id", "call");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call()"));
	}
	
	@Test
	public void testToStringOneParameter() {
		testee = new CalledEntry(new String(), "id", "call", "var0", "type0");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call(var0: type0)"));
	}
	
	@Test
	public void testToStringMoreParameter() {
		testee = new CalledEntry(new String(), "id", "call", "var0", "type0", "var1", "type1");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call(var0: type0, var1: type1)"));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testToStringUnfittingParameter() {
		testee = new CalledEntry(new String(), "id", "call", "var0", "type0", "var1");
		
		assertThat(testee, notNullValue());
		assertThat(testee.toString(), equalTo(String.class.getName() + "[id].call(var0: type0, var1: type1)"));
	}
}
