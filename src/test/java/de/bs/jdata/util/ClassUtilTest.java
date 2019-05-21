package de.bs.jdata.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;

public class ClassUtilTest {

	@Test
	public void testGetClassFromInteger() {
		Class<?> result = ClassUtil.getClass("java.lang.Integer");

		assertThat(result, Matchers.<Class<?>>equalTo(Integer.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetClassFromNotExistingClass() {
		ClassUtil.getClass("de.bs.jdata.util.Failing");
	}

	@Test
	public void testInstantiateWithString() {
		String result = ClassUtil.instantiate(String.class);

		assertThat(result, notNullValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateWrongCtor() {
		ClassUtil.instantiate(NoDefaultCtor.class);
	}

	@Test
	public void testInstantiateStringWithDefaultCtor() {
		Object result = ClassUtil.instantiate(EmptyClass.class.getName());

		assertThat(result, notNullValue());
		assertThat(result, instanceOf(EmptyClass.class));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateStringFromInteger() {
		ClassUtil.instantiate("java.lang.Integer");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiateStringFromFailing() {
		ClassUtil.instantiate("de.bs.jdata.util.Failing");
	}

	public static class NoDefaultCtor {
		public NoDefaultCtor(String a) {
		}
	}

	public static class PrivateCtor {
		public PrivateCtor() {
		}
	}

	public static class EmptyClass {
		public EmptyClass() {
		}
	}
}
