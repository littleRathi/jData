package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.converter.PrimitiveWrapper;
import de.bs.jdata.converter.TypeConverter;

public class FloatConverterTest {
	private FloatConverter testee;
	
	@Test
	public void testInstantiateForPrimitive() {
		TypeConverter<?> result = testee.instantiateFor(float.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(PrimitiveWrapper.class));
	}
	
	@Test
	public void testInstantiateForClass() {
		TypeConverter<?> result = testee.instantiateFor(Float.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(FloatConverter.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInstantiateForInteger() {
		testee.instantiateFor(Integer.class, null);
	}
	
	@Test
	public void testDecodeStringWithRightString() {
		TypeConverter<?> converter = testee.instantiateFor(Float.class, null);
		
		Object result = converter.decode("123.456");
		
		assertThat(result, ofType(Float.class));
		assertThat((Float)result, equalTo(123.456f));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDecodeStringWithWrongString() {
		TypeConverter<?> converter = testee.instantiateFor(Float.class, null);
		
		converter.decode("abc");
	}
	
	@Test
	public void testDecodeStringWithNull() {
		TypeConverter<?> converter = testee.instantiateFor(Float.class, null);
		
		Object result = converter.decode((String)null);
		
		assertThat(result, nullValue());
	}
	
	@Before
	public void setUp() {
		testee = new FloatConverter();
	}
	
	@After
	public void tearDown() {
		testee = null;
	}

}
