package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.converter.PrimitiveWrapper;
import de.bs.jdata.converter.TypeConverter;

public class DoubleConverterTest {
	private DoubleConverter testee;
	
	@Test
	public void testInstantiateForPrimitive() {
		TypeConverter<?> result = testee.instantiateFor(double.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(PrimitiveWrapper.class));
	}
	
	@Test
	public void testInstantiateForClass() {
		TypeConverter<?> result = testee.instantiateFor(Double.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(DoubleConverter.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInstantiateForInteger() {
		testee.instantiateFor(Integer.class, null);
	}
	
	@Test
	public void testDecodeStringWithRightString() {
		TypeConverter<?> converter = testee.instantiateFor(Double.class, null);
		
		Object result = converter.decode("123.456");
		
		assertThat(result, ofType(Double.class));
		assertThat((Double)result, equalTo(123.456d));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDecodeStringWithWrongString() {
		TypeConverter<?> converter = testee.instantiateFor(Double.class, null);
		
		converter.decode("abc");
	}
	
	@Test
	public void testDecodeStringWithNull() {
		TypeConverter<?> converter = testee.instantiateFor(Double.class, null);
		
		Object result = converter.decode((String)null);
		
		assertThat(result, nullValue());
	}
	
	@Before
	public void setUp() {
		testee = new DoubleConverter();
	}
	
	@After
	public void tearDown() {
		testee = null;
	}
}
