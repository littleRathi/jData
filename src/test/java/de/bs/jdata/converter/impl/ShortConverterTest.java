package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.converter.PrimitiveWrapper;
import de.bs.jdata.converter.TypeConverter;

public class ShortConverterTest {
	private ShortConverter testee;
	
	@Test
	public void testInstantiateForPrimitive() {
		TypeConverter<?> result = testee.instantiateFor(short.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(PrimitiveWrapper.class));
	}
	
	@Test
	public void testInstantiateForClass() {
		TypeConverter<?> result = testee.instantiateFor(Short.class, null);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(ShortConverter.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInstantiateForInteger() {
		testee.instantiateFor(Integer.class, null);
	}
	
	@Test
	public void testDecodeStringWithRightString() {
		TypeConverter<?> converter = testee.instantiateFor(Short.class, null);
		
		Object result = converter.decode("55");
		
		assertThat(result, ofType(Short.class));
		assertThat((Short)result, equalTo((short)55));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDecodeStringWithWrongString() {
		TypeConverter<?> converter = testee.instantiateFor(Short.class, null);
		
		converter.decode("abc");
	}
	
	@Test
	public void testDecodeStringWithNull() {
		TypeConverter<?> converter = testee.instantiateFor(Short.class, null);
		
		Object result = converter.decode((String)null);
		
		assertThat(result, nullValue());
	}
	
	@Before
	public void setUp() {
		testee = new ShortConverter();
	}
	
	@After
	public void tearDown() {
		testee = null;
	}

}
