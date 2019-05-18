package de.bs.jdata.matcher;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.matcher.custom.CustomMatcher;
import de.bs.jdata.matcher.init.GenericData;
import de.bs.jdata.matcher.init.MatcherGenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UseCustomTest {
	private UseCustom<?> testee;

	@Test
	public void testUseCustomWithSimpleMatcher() {
		SimpleCustomerMatcher customMatcher = new SimpleCustomerMatcher();

		testee = new UseCustom<SimpleCustomerMatcher>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, customMatcher)));

		assertThat(testee.check(String.class, null), equalTo(true));
		assertThat(testee.check(StringBuilder.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(GenericCustomerMatcher.class, null), equalTo(false));
	}

	@Test
	public void testUseCustomWithGenericMatcher() {
		GenericCustomerMatcher<?, ?> customMatcher = new GenericCustomerMatcher<String, StringBuilder>();
		customMatcher.init(new GenericData(new TypeGenericDataPart(null, String.class),
				new TypeGenericDataPart(null, StringBuilder.class)));

		testee = new UseCustom<CustomMatcher>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, customMatcher)));

		assertThat(testee.check(String.class, null), equalTo(true));
		assertThat(testee.check(StringBuilder.class, null), equalTo(true));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(SimpleCustomerMatcher.class, null), equalTo(false));
	}

	@After
	public void teardown() {
		testee = null;
	}

	public class GenericCustomerMatcher<T0, T1> implements CustomMatcher {

		@Override
		public void init(GenericData data) {
			assertThat(data.size(), equalTo(2));
		}

		@Override
		public boolean check(Class<?> type, Class<?> parentType) {
			return String.class.equals(type) || StringBuilder.class.equals(type);
		}

	}

	public class SimpleCustomerMatcher implements CustomMatcher {

		@Override
		public void init(GenericData data) {
		}

		@Override
		public boolean check(Class<?> type, Class<?> parentType) {
			return String.class.equals(type);
		}

	}
}
