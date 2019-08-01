package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.converter.PrimitiveWrapper;
import de.bs.jdata.converter.TypeConverter;

public class ByteConverterTest {
	private ByteConverter testee;

	@Test
	public void testInstantiateForPrimitive() {
		TypeConverter<?> result = testee.instantiateFor(byte.class, null);

		assertThat(result, notNullValue());
		assertThat(result, ofType(PrimitiveWrapper.class));
	}

	@Test
	public void testInstantiateForClass() {
		TypeConverter<?> result = testee.instantiateFor(Byte.class, null);

		assertThat(result, notNullValue());
		assertThat(result, ofType(ByteConverter.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateForInteger() {
		testee.instantiateFor(Integer.class, null);
	}

	@Test
	public void testDecodeStringWithRightString() {
		TypeConverter<?> converter = testee.instantiateFor(Byte.class, null);

		Object result = converter.decode("123");

		assertThat(result, ofType(Byte.class));
		assertThat((Byte) result, equalTo(Byte.valueOf("123")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeStringWithWrongString() {
		TypeConverter<?> converter = testee.instantiateFor(Byte.class, null);

		converter.decode("abc");
	}

	@Test
	public void testDecodeStringWithNull() {
		TypeConverter<?> converter = testee.instantiateFor(Byte.class, null);

		Object result = converter.decode((String)null);
		
		assertThat(result, nullValue());
	}

	@Before
	public void setUp() {
		testee = new ByteConverter();
	}

	@After
	public void tearDown() {
		testee = null;
	}
}
