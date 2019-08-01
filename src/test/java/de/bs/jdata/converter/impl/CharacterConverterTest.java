package de.bs.jdata.converter.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static de.bs.hamcrest.ClassMatchers.*;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.converter.TypeConverter;
import de.bs.jdata.converter.impl.CharacterConverter;

public class CharacterConverterTest {
	private CharacterConverter creator = new CharacterConverter();
	private TypeConverter<?> testee;

	@Test
	public void testInstantiateForCharacterClass() {
		testee = creator.instantiateFor(Character.class, null);

		assertThat(testee.getActualType(), equalToType(Character.class));
	}

	@Test
	public void testInstantiateForCharacterPrimitive() {
		testee = creator.instantiateFor(char.class, null);

		assertThat(testee.getActualType(), equalToType(char.class));
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

// decode(String)
	@Test
	public void testPrimitveDecodeStringWithString() {
		testee = creator.instantiateFor(char.class, null);

		Object result = testee.decode("a");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('a'));

		result = testee.decode("\t");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('\t'));
	}

	@Test
	public void testClassDecodeStringWithString() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.decode("a");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('a'));

		result = testee.decode("\t");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('\t'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitveDecodeStringWithEmptyString() {
		testee = creator.instantiateFor(char.class, null);

		testee.decode("");
	}

	@Test
	public void testCöassDecodeStringWithEmptyString() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.decode("");

		assertThat(result, nullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitiveDecodeStringWithLongString() {
		testee = creator.instantiateFor(char.class, null);

		testee.decode("abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testClassDecodeStringWithLongString() {
		testee = creator.instantiateFor(Character.class, null);

		testee.decode("abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitiveDecodeStringWithNull() {
		testee = creator.instantiateFor(char.class, null);

		testee.decode((String) null);
	}

	@Test
	public void testClassDecodeStringWithNull() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.decode((String) null);

		assertThat(result, nullValue());
	}

//	decode(Object)
	@Test
	public void testPrimitiveDecodeObjectWithString() {
		testee = creator.instantiateFor(char.class, null);

		Object result = testee.decode((Object) "a");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('a'));

		result = testee.decode((Object) "\t");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('\t'));
	}

	@Test
	public void testClassDecodeObjectWithString() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.decode((Object) "a");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('a'));

		result = testee.decode((Object) "\t");

		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('\t'));
	}

	@Test
	public void testPrimitiveDecodeObjectWithChar() {
		testee = creator.instantiateFor(char.class, null);

		Object result = testee.decode('x');

		assertThat(result, notNullValue());
		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('x'));

		result = testee.decode('\t');

		assertThat(result, notNullValue());
		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('\t'));
	}

	@Test
	public void testClassDecodeObjectWithChar() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.decode('x');

		assertThat(result, notNullValue());
		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('x'));

		result = testee.decode('\t');

		assertThat(result, notNullValue());
		assertThat(result, ofType(Character.class));
		assertThat((Character) result, equalTo('\t'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitiveDecodeObjectWithLong() {
		testee = creator.instantiateFor(char.class, null);

		testee.decode(123l);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testClassDecodeObjectWithLong() {
		testee = creator.instantiateFor(Character.class, null);

		testee.decode(123l);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrimitiveDecodeObjectWithNull() {
		testee = creator.instantiateFor(char.class, null);

		testee.decode((Object) null);
	}

	@Test
	public void testClassDecodeObjectWithNull() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.decode((Object) null);

		assertThat(result, nullValue());
	}

//	.encode
	@Test
	public void testPrimitiveEncodeChar() {
		testee = creator.instantiateFor(char.class, null);

		Object result = testee.encode('a');

		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String) result, equalTo("a"));

		result = testee.encode('\t');

		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String) result, equalTo("\t"));
	}

	@Test
	public void testClassEncodeChar() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.encode('a');

		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String) result, equalTo("a"));

		result = testee.encode('\t');

		assertThat(result, notNullValue());
		assertThat(result, ofType(String.class));
		assertThat((String) result, equalTo("\t"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrimitiveEncodeInteger() {
		testee = creator.instantiateFor(char.class, null);

		testee.encode(456789l);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testClassEncodeInteger() {
		testee = creator.instantiateFor(Character.class, null);

		testee.encode(98765l);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrimitveEncodeNull() {
		testee = creator.instantiateFor(char.class, null);

		testee.encode(null);
	}

	@Test
	public void testClassEncodeNull() {
		testee = creator.instantiateFor(Character.class, null);

		Object result = testee.encode(null);

		assertThat(result, nullValue());
	}

	@After
	public void tearDown() {
		testee = null;
	}
}
