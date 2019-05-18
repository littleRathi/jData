package de.bs.jdata.matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.matcher.type.Exact;
import de.bs.jdata.matcher.type.Extends;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.Or;
import de.bs.jdata.matcher.type.OrOf;
import de.bs.jdata.matcher.type.Subclass;

@SuppressWarnings("unchecked")
public class MatcherRegistryTestWithType {
	public static final List<Class<?>> LIST_OF_TYPES = Arrays.asList(Short.class, Integer.class, Long.class,
			Float.class, Double.class, String.class, A.class, B.class, C.class, D.class, E.class, F.class, G.class,
			H.class, I.class, J.class, K.class);

	private MatcherRegistry testee;
	private List<Class<?>> failingClasses;

	@Before
	public void setup() {
		testee = new MatcherRegistry();
	}

	@After
	public void teardown() {
		testee = null;
		failingClasses = null;
	}

	@Test
	public void testTypeIsWithOrAndOrOf() {
		Matcher handler = testee.getMatcherFrom(ExampleIsOrOrOf.class);

		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Short.class, null), equalTo(true));
		assertThat(handler.check(Integer.class, null), equalTo(true));
		assertThat(handler.check(Long.class, null), equalTo(true));

		failingClasses = failingClasses(Short.class, Integer.class, Long.class);
		
		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}

	@Test
	public void testTypeIsWithSubOrAndOrOf() {
		Matcher handler = testee.getMatcherFrom(SubExampleIsOrOrOf.class);

		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Short.class, null), equalTo(true));
		assertThat(handler.check(Integer.class, null), equalTo(true));
		assertThat(handler.check(Long.class, null), equalTo(true));

		failingClasses = failingClasses(Short.class, Integer.class, Long.class);
		
		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}
	
	@Test
	public void testTypeIsWithExampleIsOrOf() {
		Matcher handler = testee.getMatcherFrom(ExampleIsOrOf.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Float.class, null), equalTo(true));
		assertThat(handler.check(Double.class, null), equalTo(true));
		
		failingClasses = failingClasses(Float.class, Double.class);
		
		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}
	
	@Test
	public void testTypeExactWithExampleExact() {
		Matcher handler = testee.getMatcherFrom(ExampleExact.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(String.class, null), equalTo(true));
		
		failingClasses = failingClasses(String.class);
		
		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}
	
	@Test
	public void testTypeExtendsExampleExtends() {
		Matcher handler = testee.getMatcherFrom(ExampleExtends.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(C.class, null), equalTo(true));
		assertThat(handler.check(G.class, null), equalTo(true));
		assertThat(handler.check(H.class, null), equalTo(true));
		assertThat(handler.check(I.class, null), equalTo(true));
		
		failingClasses = failingClasses(C.class, G.class, H.class, I.class);
		
		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}
	
	@Test
	public void testTypeSubclassExampleSubclass() {
		Matcher handler = testee.getMatcherFrom(ExampleSubclass.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(J.class, null), equalTo(true));
		assertThat(handler.check(K.class, null), equalTo(true));
		
		failingClasses = failingClasses(J.class, K.class);
		
		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}
	
	@Test
	public void testTypeIsWithIExampleIsOrOf() {
		Matcher handler = testee.getMatcherFrom(IExampleIsOrOf.class);

		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Float.class, null), equalTo(true));
		assertThat(handler.check(Double.class, null), equalTo(true));
		
		failingClasses = failingClasses(Float.class, Double.class);

		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}

	@Test
	public void testTypeIsWithISubExampleIsOrOf() {
		Matcher handler = testee.getMatcherFrom(ISubExampleIsOrOf.class);

		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Float.class, null), equalTo(true));
		assertThat(handler.check(Double.class, null), equalTo(true));
		
		failingClasses = failingClasses(Float.class, Double.class);

		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}

	@Test
	public void testTypeIsWithIIExampleIsOrOf() {
		Matcher handler = testee.getMatcherFrom(IIExampleIsOrOf.class);

		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Float.class, null), equalTo(true));
		assertThat(handler.check(Double.class, null), equalTo(true));
		
		failingClasses = failingClasses(Float.class, Double.class);

		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}

	@Test
	public void testTypeIsWithIISubExampleIsOrOf() {
		Matcher handler = testee.getMatcherFrom(IISubExampleIsOrOf.class);

		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Float.class, null), equalTo(true));
		assertThat(handler.check(Double.class, null), equalTo(true));
		
		failingClasses = failingClasses(Float.class, Double.class);

		for (final Class<?> failing: failingClasses) {
			assertThat(handler.check(failing, null), equalTo(false));
		}
	}
	
	public List<Class<?>> failingClasses(final Class<?>... classesToRemove) {
		List<Class<?>> failingList = new LinkedList<Class<?>>();
		failingList.removeAll(Arrays.asList(classesToRemove));
		return failingList;
	}

	@SuppressWarnings("hiding")
	public class Converter<H extends Matcher> {
	}

	public class ExampleIsOrOrOf extends Converter<Type<Is<Short, Or<Integer, OrOf<Long>>>>> {
	}

	public class SubExampleIsOrOrOf extends ExampleIsOrOrOf {
	}

	public class ExampleIsOrOf extends Converter<Type<Is<Float, OrOf<Double>>>> {
	}

	public class ExampleExact extends Converter<Type<Exact<String>>> {
	}

	public class ExampleExtends extends Converter<Type<Extends<C>>> {
	}

	public class ExampleSubclass extends Converter<Type<Subclass<D>>> {
	}

	@SuppressWarnings("hiding")
	public interface Converting<H extends Matcher> {
	}

	public interface AdvancedConvertig extends Converting<Type<Is<Float, OrOf<Double>>>> {
	}

	public class IExampleIsOrOf implements Converting<Type<Is<Float, OrOf<Double>>>> {
	}

	public class ISubExampleIsOrOf extends IExampleIsOrOf {
	}

	public class IIExampleIsOrOf implements AdvancedConvertig {
	}

	public class IISubExampleIsOrOf extends ISubExampleIsOrOf {
	}

	public class A {
	}

	public class B extends A {
	}

	public class C extends A {
	}

	public class D extends A {
	}

	public class E extends B {
	}

	public class F extends B {
	}

	public class G extends C {
	}

	public class H extends C {
	}

	public class I extends C {
	}

	public class J extends D {
	}

	public class K extends D {
	}
}
