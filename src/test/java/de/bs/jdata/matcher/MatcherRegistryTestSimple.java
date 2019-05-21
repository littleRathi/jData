package de.bs.jdata.matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatcherRegistryTestSimple {
	private MatcherRegistry testee;
	
	@Test
	public void testGetMatcherFromWithoutMatcher() {
		Matcher handler = testee.getMatcherFrom(NoMatcher.class);
		
		assertThat(handler, nullValue());
	}
	
	@Test
	public void testGetMatcherFromWithIncompleteMatcher() {
		Matcher handler = testee.getMatcherFrom(IncompleteMatcher.class);
		
		assertThat(handler, nullValue());
	}
	
	@Before
	public void setup() {
		testee = new MatcherRegistry();
	}
	
	@After
	public void teardown() {
		testee = null;
	}
	
	public class NoMatcher {
	}
	
	public class Converter<H extends Matcher> {
	}
	
	@SuppressWarnings("rawtypes")
	public class IncompleteMatcher extends Converter {
	}
}
