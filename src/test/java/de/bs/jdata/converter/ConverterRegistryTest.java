package de.bs.jdata.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.bs.jdata.example.all.converters.ConverterMockOne;
import de.bs.jdata.example.all.converters.ConverterMockTwo;
import de.bs.jdata.matcher.Matcher;
import de.bs.jdata.matcher.MatcherRegistry;
import de.bs.jdata.matcher.init.GenericData;

public class ConverterRegistryTest {
	@InjectMocks
	private ConverterRegistry testee;
	@Mock
	private MatcherRegistry matchers;

	private TypeConverter<?> result;

	@Test
	public void testAddConverterOne() {
		MockitoAnnotations.initMocks(this);
		
		when(matchers.getMatcherFrom(Matchers.<Class<?>>anyObject())).thenReturn(new SimpleMatcher(String.class));
		
		boolean added = testee.addConverter(ConverterMockOne.class);
		
		assertThat(added, equalTo(true));
	}
	
	@Test
	public void testAddConverterSameTwice() {
		MockitoAnnotations.initMocks(this);
		
		when(matchers.getMatcherFrom(Matchers.<Class<?>>anyObject())).thenReturn(new SimpleMatcher(String.class));
		
		boolean first = testee.addConverter(ConverterMockOne.class);
		boolean second = testee.addConverter(ConverterMockOne.class);
		
		assertThat(first, equalTo(true));
		assertThat(second, equalTo(false));
	}
	
	@Test
	public void testAddConverterSameTwiceWithDifferentInbetween() {
		MockitoAnnotations.initMocks(this);
		
		when(matchers.getMatcherFrom(Matchers.<Class<?>>anyObject())).thenReturn(new SimpleMatcher(String.class));
		
		boolean first = testee.addConverter(ConverterMockOne.class);
		boolean second = testee.addConverter(ConverterMockTwo.class);
		boolean three = testee.addConverter(ConverterMockOne.class);
		
		assertThat(first, equalTo(true));
		assertThat(second, equalTo(true));
		assertThat(three, equalTo(false));
	}


	@Test
	public void testEmptyChain() {
		result = testee.getConverterFor(String.class, null);

		assertThat(result, equalTo(null));
	}
	
	@Test
	public void testGetConverterForWithOneAddedConverter() {
		MockitoAnnotations.initMocks(this);
		
		when(matchers.getMatcherFrom(Matchers.<Class<?>>anyObject())).thenReturn(new SimpleMatcher(String.class));
		
		boolean added = testee.addConverter(ConverterMockOne.class);
		
		result = testee.getConverterFor(String.class, null);
		TypeConverter<?> result2 = testee.getConverterFor(Integer.class, null);
		
		
		assertThat(added, equalTo(true));
		assertThat(result, instanceOf(ConverterMockOne.class));
		assertThat(result2, nullValue());
	}
	
	@Test
	public void testGetConverterForWithTwoAddedConverter() {
		MockitoAnnotations.initMocks(this);
		
		when(matchers.getMatcherFrom(Matchers.<Class<?>>anyObject())).thenReturn(new SimpleMatcher(String.class))
			.thenReturn(new SimpleMatcher(Integer.class));
		
		boolean addedString = testee.addConverter(ConverterMockOne.class);
		boolean addedInteger = testee.addConverter(ConverterMockTwo.class);
		
		result = testee.getConverterFor(String.class, null);
		TypeConverter<?> result2 = testee.getConverterFor(Integer.class, null);
		TypeConverter<?> result3 = testee.getConverterFor(int.class, null);
		TypeConverter<?> result4 = testee.getConverterFor(Long.class, null);
		
		assertThat(addedString, equalTo(true));
		assertThat(addedInteger, equalTo(true));
		assertThat(result, instanceOf(ConverterMockOne.class));
		assertThat(result2, instanceOf(ConverterMockTwo.class));
		assertThat(result3, nullValue());
		assertThat(result4, nullValue());
	}
	
	@Test
	public void testGetConverterForWithTwoAddedConverterSameMatch() {
		MockitoAnnotations.initMocks(this);
		
		when(matchers.getMatcherFrom(Matchers.<Class<?>>anyObject())).thenReturn(new SimpleMatcher(String.class));
		
		boolean addFirst = testee.addConverter(ConverterMockOne.class);
		
		result = testee.getConverterFor(String.class, null);
		TypeConverter<?> result2 = testee.getConverterFor(Integer.class, null);
		
		boolean addSecond = testee.addConverter(ConverterMockTwo.class);
		
		TypeConverter<?> result3 = testee.getConverterFor(String.class, null);
		TypeConverter<?> result4 = testee.getConverterFor(Integer.class, null);
		
		assertThat(addFirst, equalTo(true));
		assertThat(addSecond, equalTo(true));
		assertThat(result, instanceOf(ConverterMockOne.class));
		assertThat(result2, nullValue());
		assertThat(result3, instanceOf(ConverterMockTwo.class));
		assertThat(result4, nullValue());
	}

	@Before
	public void setup() {
		testee = new ConverterRegistry();
	}

	@After
	public void teardown() {
		testee = null;
		result = null;
	}

	class SimpleMatcher implements Matcher {
		private Class<?> type;

		public SimpleMatcher(Class<?> type) {
			this.type = type;
		}

		@Override
		public void init(GenericData data) {
		}

		@Override
		public boolean check(Class<?> type, Class<?> parentType) {
			return this.type.equals(type);
		}

	}
}
