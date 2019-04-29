package de.bs.jdata.tracking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class StackTraceElementEntryTest {
	private StackTraceElementEntry testee;
	
	@Test
	public void testToString() {
		StackTraceElement ste = new StackTraceElement("class", "call", "", 10);
		testee = new StackTraceElementEntry(ste);
		
		assertThat(testee.toString(), equalTo("[caller: class.call(10)]"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testToStringWithNull() {
		testee = new StackTraceElementEntry(null);
		
		assertThat(testee.toString(), nullValue());
	}
}
