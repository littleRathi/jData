package de.bs.jdata.converter;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.example.all.converters.EmptyConverter;

public class AbstractPrimitiveConverterTest {
	private TypeConverter<?> testee;

//	.instantiateFor(Class<?>, Class<?>
	@Test
	public void testInstantiateForSimple() {
		AbstractPrimitiveConverter<EmptyConverter> converter = createAnonyConverter(Integer.class, String.class, null);
		testee = createAnonyConverter(int.class, null, converter);

		TypeConverter<?> result = testee.instantiateFor(Integer.class, null);

		assertThat(result, notNullValue());
		assertThat(result, not(instanceOf(PrimitiveWrapper.class)));
		assertThat(result.getActualType(), equalToType(Integer.class));
		assertThat(result.getParentType(), equalToType(String.class));
	}

	@Test
	public void testInstantiateForPrimitiveWrapper() {
		AbstractPrimitiveConverter<EmptyConverter> converter = createAnonyConverter(int.class, Long.class, null);
		testee = createAnonyConverter(int.class, null, converter);

		TypeConverter<?> result = testee.instantiateFor(int.class, null);

		assertThat(result, notNullValue());
		assertThat(result, instanceOf(PrimitiveWrapper.class));
		assertThat(result.getActualType(), equalToType(int.class));
		assertThat(result.getParentType(), equalToType(Long.class));
	}

//	.decode(Object)
	@Test
	public void testDecodeObjectStringFineNoWrapper() {
		testee = createConverter(Integer.class);

		Object result = testee.decode((Object) "123");

		assertThat(result, ofType(Integer.class));
		assertThat((Integer) result, equalTo(123));
	}

	@Test
	public void testDecodeObjectStringFineWithWrapper() {
		testee = createConverter(int.class);

		Object result = testee.decode((Object) "123");

		assertThat(result, ofType(Integer.class));
		assertThat((Integer) result, equalTo(123));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeObjectStringWrongNoWrapper() {
		testee = createConverter(Integer.class);

		testee.decode((Object) "abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeObjectStringWrongWithWrapper() {
		testee = createConverter(int.class);

		testee.decode((Object) "abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeObjectLongNoWrapper() {
		testee = createConverter(Integer.class);

		testee.decode(Long.valueOf("123"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeObjectLongWithWrapper() {
		testee = createConverter(int.class);

		testee.decode(Long.valueOf("123"));
	}

	@Test
	public void testDecodeObjectIntNoWrapper() {
		testee = createConverter(Integer.class);

		Object result = testee.decode(123);

		assertThat(result, notNullValue());
		assertThat(result, ofType(Integer.class));
		assertThat((Integer) result, equalTo(123));
	}

	@Test
	public void testDecodeObjectIntWithWrapper() {
		testee = createConverter(int.class);

		Object result = testee.decode(123);

		assertThat(result, notNullValue());
		assertThat(result, ofType(Integer.class));
		assertThat((Integer) result, equalTo(123));
	}

	@Test
	public void testDecodeObjectNullNoWrapper() {
		testee = createConverter(Integer.class);

		Object result = testee.decode((Object) null);

		assertThat(result, nullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeObjectNullWithWrapper() {
		testee = createConverter(int.class);

		testee.decode((Object) null);
	}

//	.encode(Object)
	@Test
	public void testEncodeNullNoWrapper() {
		testee = createConverter(Integer.class);

		String result = testee.encode(null);

		assertThat(result, nullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEncodeNullWithWrapper() {
		testee = createConverter(int.class);

		testee.encode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEncodeStringNoWrapper() {
		testee = createConverter(Integer.class);

		testee.encode("abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEncodeStringWithWrapper() {
		testee = createConverter(int.class);

		testee.encode("abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEncodeDoubleNoWrapper() {
		testee = createConverter(Integer.class);

		testee.encode(123d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEncodeDoubleWithWrapper() {
		testee = createConverter(int.class);

		testee.encode(123d);
	}

	@Test
	public void testEncodeIntNoWrapper() {
		testee = createConverter(Integer.class);

		String result = testee.encode(123);

		assertThat(result, equalTo("123"));
	}

	@Test
	public void testEncodeIntWithWrapper() {
		testee = createConverter(int.class);

		String result = testee.encode(123);

		assertThat(result, equalTo("123"));
	}

	private TypeConverter<?> createConverter(final Class<?> actualType) {
		AbstractPrimitiveConverter<EmptyConverter> converter = createAnonyConverter(int.class, Long.class, null);
		return createAnonyConverter(actualType, null, converter).instantiateFor(actualType, null);
	}

	private AbstractPrimitiveConverter<EmptyConverter> createAnonyConverter(final Class<?> actualType,
			final Class<?> parentType, final TypeConverter<EmptyConverter> converter) {
		return new AbstractPrimitiveConverter<EmptyConverter>(actualType, parentType, Integer.class, int.class) {

			@Override
			protected TypeConverter<EmptyConverter> createConverter(Class<?> actualType, Class<?> parentType) {
				return converter;
			}

			@Override
			public Object decode(String representation) {
				return Integer.valueOf(representation);
			}

		};
	}

	@After
	public void tearDown() {
		testee = null;
	}
}
