package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.converter.TypeConverter;

public class StringConverterTest {
	private StringConverter generator = new StringConverter();
	private TypeConverter<?> testee;
	
//	.instantiateFor
	@Test
	public void testInstantiateForWithString() {
		assertThat(testee, notNullValue());
		assertThat(testee, ofType(StringConverter.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInstantiateForWithLong() {
		testee.instantiateFor(Long.class, null);
	}
	
//	.decode(String)
	@Test
	public void testDecodeStringWithString() {
		Object result = testee.decode("abc");
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String)result, equalTo("abc"));
	}
	
	@Test
	public void testDecodeStringWithNull() {
		Object result = testee.decode((String)null);
		
		assertThat(result, nullValue());
	}
	
//	.decode(Object)
	@Test
	public void testDecodeObjectString() {
		Object result = testee.decode((Object)"efg");
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String)result, equalTo("efg"));
	}
	
	@Test
	public void testDecodeObjectNull() {
		Object result = testee.decode((Object)null);
		
		assertThat(result, nullValue());
	}
	
	@Test
	public void testDecodeObjectLong() {
		Object result = testee.decode(123l);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String)result, equalTo("123"));
	}
	
//	.encode
	@Test
	public void testEncodeWithNull() {
		String result = testee.encode(null);
		
		assertThat(result, nullValue());
	}
	
	@Test
	public void testEncodeWithString() {
		String result = testee.encode("hij");
		
		assertThat(result, notNullValue());
		assertThat(result, equalTo("hij"));
	}
	
	@Test
	public void testEncodeWIthDouble() {
		String result = testee.encode(123.5d);
		
		assertThat(result, notNullValue());
		assertThat(Double.valueOf(result), equalTo(123.5d));
	}
	
	@Before
	public void setUp() {
		testee = generator.instantiateFor(String.class, null);
	}
	
	@After
	public void tearDown() {
		testee = null;
	}
}
