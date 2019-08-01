package de.bs.jdata.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.example.all.converters.EmptyConverter;

public class AbstractConverterTest {
	private AbstractConverter<?> testee;
	
	@Test
	public void testGetActualTypeString() {
		testee = createAnonymConverter(String.class, null);
		
		assertThat(testee.getActualType(), equalToType(String.class));
	}
	
	@Test
	public void testGetActualTypeBooleanPrimitive() {
		testee = createAnonymConverter(boolean.class, null);
		
		assertThat(testee.getActualType(), equalToType(boolean.class));
	}
	
	@Test
	public void testGetParentTypeNull() {
		testee = createAnonymConverter(int.class, null);
		
		assertThat(testee.getParentType(), nullValue());
	}
	
	@Test
	public void testGetParentTypeString() {
		testee = createAnonymConverter(int.class, String.class);
		
		assertThat(testee.getParentType(), equalToType(String.class));
	}
	
	@Test
	public void testGetParentTypeIntPrimitive() {
		testee = createAnonymConverter(int.class, int.class);
		
		assertThat(testee.getParentType(), equalToType(int.class));
	}
	
	private AbstractConverter<?> createAnonymConverter(final Class<?> actualType, final Class<?> parentType) {
		return new AbstractConverter<EmptyConverter>(actualType, parentType) {
			@Override
			public TypeConverter<?> instantiateFor(final Class<?> actualType, final Class<?> parentType) {
				return null;
			}

			@Override
			public Object decode(final String representation) {
				return null;
			}

			@Override
			public Object decode(final Object representation) {
				return null;
			}

			@Override
			public String encode(final Object object) {
				return null;
			}
		};
	}
	
	@After
	public void tearDown() {
		testee = null;
	}
}
