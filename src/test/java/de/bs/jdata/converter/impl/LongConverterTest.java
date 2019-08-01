package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.converter.PrimitiveWrapper;
import de.bs.jdata.converter.TypeConverter;

public class LongConverterTest {
	private LongConverter testee;
	
	@Test
	public void testInstantiateForPrimitive() {
		TypeConverter<?> result = testee.instantiateFor(long.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(PrimitiveWrapper.class));
	}
	
	@Test
	public void testInstantiateForClass() {
		TypeConverter<?> result = testee.instantiateFor(Long.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(LongConverter.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInstantiateForInteger() {
		testee.instantiateFor(Integer.class, null);
	}
	
	@Test
	public void testDecodeStringWithRightString() {
		TypeConverter<?> converter = testee.instantiateFor(Long.class, null);
		
		Object result = converter.decode("123");
		
		assertThat(result, ofType(Long.class));
		assertThat((Long)result, equalTo(123l));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDecodeStringWithWrongString() {
		TypeConverter<?> converter = testee.instantiateFor(Long.class, null);
		
		converter.decode("abc");
	}
	
	@Test
	public void testDecodeStringWithNull() {
		TypeConverter<?> converter = testee.instantiateFor(Long.class, null);
		
		Object result = converter.decode((String)null);
		
		assertThat(result, nullValue());
	}
	
	@Before
	public void setUp() {
		testee = new LongConverter();
	}
	
	@After
	public void tearDown() {
		testee = null;
	}

}
