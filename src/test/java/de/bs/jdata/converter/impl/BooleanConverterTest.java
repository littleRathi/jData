package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.converter.impl.BooleanConverter;

public class BooleanConverterTest {
	private BooleanConverter creator = new BooleanConverter();
	private TypeConverter<?> testee;

	@Test
	public void testInstantiateForBooleanClass() {
		testee = creator.instantiateFor(Boolean.class, null);

		assertThat(testee.getActualType(), equalToType(Boolean.class));
	}

	@Test
	public void testInstantiateForBooleanPrimitive() {
		testee = creator.instantiateFor(boolean.class, null);

		assertThat(testee.getActualType(), equalToType(boolean.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateForString() {
		testee = creator.instantiateFor(String.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateForLong() {
		testee = creator.instantiateFor(Long.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateForNull() {
		testee = creator.instantiateFor(null, null);
	}

	@Test
	public void testPrimitiveDecodeStringWithTrue() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode("true");
		assertThat(result, equalTo((Object) Boolean.TRUE));

		result = testee.decode("True");
		assertThat(result, equalTo((Object) Boolean.TRUE));

		result = testee.decode("TRUE");
		assertThat(result, equalTo((Object) Boolean.TRUE));
	}

	@Test
	public void testClassDecodeStringWithTrue() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode("true");
		assertThat(result, equalTo((Object) Boolean.TRUE));

		result = testee.decode("True");
		assertThat(result, equalTo((Object) Boolean.TRUE));

		result = testee.decode("TRUE");
		assertThat(result, equalTo((Object) Boolean.TRUE));
	}

	@Test
	public void testPrimitiveDecodeStringWithFalse() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode("false");
		assertThat(result, equalTo((Object) Boolean.FALSE));

		result = testee.decode("False");
		assertThat(result, equalTo((Object) Boolean.FALSE));

		result = testee.decode("FALSE");
		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test
	public void testClassDecodeStringWithFalse() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode("false");
		assertThat(result, equalTo((Object) Boolean.FALSE));

		result = testee.decode("False");
		assertThat(result, equalTo((Object) Boolean.FALSE));

		result = testee.decode("FALSE");
		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrimitiveDecodeStringWithNull() {
		testee = creator.instantiateFor(boolean.class, null);

		testee.decode(null);
	}

	@Test
	public void testClassDecodeStringWithNull() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode(null);

		assertThat(result, nullValue());
	}

	@Test
	public void testPrimitiveDecodeObjectWithStringTrue() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode((Object) "True");

		assertThat(result, equalTo((Object) Boolean.TRUE));
	}

	@Test
	public void testClasseDecodeObjectWithStringTrue() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode((Object) "True");

		assertThat(result, equalTo((Object) Boolean.TRUE));
	}

	@Test
	public void testPrimitiveDecodeObjectWithStringFalse() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode((Object) "False");

		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test
	public void testClasseDecodeObjectWithStringFalse() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode((Object) "False");

		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test
	public void testPrimitiveDecodeObjectWithStringAbc() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode((Object) "Abc");

		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test
	public void testClassDecodeObjectWithStringAbc() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode((Object) "Abc");

		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test
	public void testPrimitiveDecodeObjectWithBooleanTrue() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode(Boolean.TRUE);

		assertThat(result, equalTo((Object) Boolean.TRUE));
	}

	@Test
	public void testClassDecodeObjectWithBooleanTrue() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode(Boolean.TRUE);

		assertThat(result, equalTo((Object) Boolean.TRUE));
	}

	@Test
	public void testPrimitiveDecodeObjectWithBooleanFalse() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode(Boolean.FALSE);

		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test
	public void testClassDecodeObjectWithBooleanFalse() {
		testee = creator.instantiateFor(Boolean.class, null);

		Object result = testee.decode(Boolean.FALSE);

		assertThat(result, equalTo((Object) Boolean.FALSE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitiveDecodeObjectWithInteger() {
		testee = creator.instantiateFor(boolean.class, null);

		testee.decode(123);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testClasseDecodeObjectWithInteger() {
		testee = creator.instantiateFor(Boolean.class, null);

		testee.decode(123);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitiveDecodeObjectWithNull() {
		testee = creator.instantiateFor(boolean.class, null);

		testee.decode((Object) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testClassDecodeObjectWithNull() {
		testee = creator.instantiateFor(boolean.class, null);

		Object result = testee.decode((Object) null);
		
		assertThat(result, nullValue());
	}
	
	// .encode
	@Test
	public void testPrimitiveEncodeTrue() {
		testee = creator.instantiateFor(boolean.class, null);
		
		Object result = testee.encode(Boolean.TRUE);
		
		assertThat(result, equalTo((Object)"true"));
	}
	
	@Test
	public void testClassEncodeTrue() {
		testee = creator.instantiateFor(Boolean.class, null);
		
		Object result = testee.encode(Boolean.TRUE);
		
		assertThat(result, equalTo((Object)"true"));
	}
	
	@Test
	public void testPrimitiveEncodeFalse() {
		testee = creator.instantiateFor(boolean.class, null);
		
		Object result = testee.encode(Boolean.FALSE);
		
		assertThat(result, equalTo((Object)"false"));
	}
	
	@Test
	public void testClassEncodeFalse() {
		testee = creator.instantiateFor(Boolean.class, null);
		
		Object result = testee.encode(Boolean.FALSE);
		
		assertThat(result, equalTo((Object)"false"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrimitiveEncodeNull() {
		testee = creator.instantiateFor(boolean.class, null);
		
		testee.encode(null);
	}
	
	@Test
	public void testClassEncodeNull() {
		testee = creator.instantiateFor(Boolean.class, null);
		
		Object result = testee.encode(null);
		
		assertThat(result, nullValue());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrimitiveEncodeLong() {
		testee = creator.instantiateFor(boolean.class, null);
		
		testee.encode(Long.valueOf(123));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClassEncodeLong() {
		testee = creator.instantiateFor(Boolean.class, null);
		
		testee.encode(Long.valueOf(123));
	}

	@After
	public void tearDown() {
		testee = null;
	}
}
