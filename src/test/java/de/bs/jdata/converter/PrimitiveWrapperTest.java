package de.bs.jdata.converter;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.example.all.converters.EmptyConverter;

public class PrimitiveWrapperTest {
	private PrimitiveWrapper<?> testee;

//	.ctor
	@Test(expected = IllegalArgumentException.class)
	public void testCtorNull() {
		new PrimitiveWrapper<EmptyConverter>(null);
	}

	@Test
	public void testCtor() {
		testee = createWrapper();

		assertThat(testee, notNullValue());
		assertThat(testee, ofType(PrimitiveWrapper.class));
	}

//	.instantiateFor
	@Test
	public void testInstantiateFor() {
		testee = createWrapper();

		TypeConverter<?> result = testee.instantiateFor(Integer.class, null);

		assertThat(result, notNullValue());
		assertThat(result, ofType(PrimitiveWrapper.class));
	}

//	.decode(String)
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeWithNull() {
		testee = createWrapper();

		testee.decode((String) null);
	}

	@Test
	public void testDecodeWithCorrectString() {
		testee = createWrapper();

		Object result = testee.decode("123");
		
		assertThat(result, ofType(Integer.class));
		assertThat((Integer)result, equalTo(123));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDecodeWithWrongString() {
		testee = createWrapper();
		
		testee.decode("abc");
	}

//	.decode(Object)
	@Test(expected=IllegalArgumentException.class)
	public void testDecodeObjectNull() {
		testee = createWrapper();
		
		testee.decode((Object)null);
	}
	
	@Test
	public void testDecodeObjectInteger() {
		testee = createWrapper();
		
		Object result = testee.decode(123);
		
		assertThat(result, notNullValue());
		assertThat(result, ofType(Integer.class));
		assertThat((Integer)result, equalTo(123));
	}

//	.encode
	@Test(expected=IllegalArgumentException.class)
	public void testEncodeNull() {
		testee = createWrapper();
		
		testee.encode(null);
	}
	
	@Test
	public void testEncodeInteger() {
		testee = createWrapper();
		
		String result = testee.encode(123);
		
		assertThat(result, notNullValue());
		assertThat(result, equalTo("123"));
	}

//	.getActualType
	@Test
	public void testGetActualType() {
		testee = createWrapper();
		
		Class<?> result = testee.getActualType();
		
		assertThat(result, notNullValue());
		assertThat(result, equalToType(Integer.class));
	}

//	.getParentType
	@Test
	public void testGetParentType() {
		testee = createWrapper();
		
		Class<?> result = testee.getParentType();
		
		assertThat(result, notNullValue());
		assertThat(result, equalToType(String.class));
	}

	private PrimitiveWrapper<EmptyConverter> createWrapper() {
		return new PrimitiveWrapper<EmptyConverter>(new TypeConverter<EmptyConverter>() {

			@Override
			public TypeConverter<?> instantiateFor(Class<?> actualType, Class<?> parentType) {
				return createWrapper();
			}

			@Override
			public Object decode(String representation) {
				return Integer.valueOf(representation);
			}

			@Override
			public Object decode(Object representation) {
				return representation;
			}

			@Override
			public String encode(Object object) {
				if (object == null) {
					return null;
				}
				return object.toString();
			}

			@Override
			public Class<?> getActualType() {
				return Integer.class;
			}

			@Override
			public Class<?> getParentType() {
				return String.class;
			}
		});
	}

	@After
	public void teatDown() {
		testee = null;
	}
}
