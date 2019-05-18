package de.bs.jdata.matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.After;
import org.junit.Test;

import de.bs.jdata.matcher.init.GenericData;
import de.bs.jdata.matcher.init.MatcherGenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;
import de.bs.jdata.matcher.type.Exact;
import de.bs.jdata.matcher.type.Extends;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.Or;
import de.bs.jdata.matcher.type.OrOf;
import de.bs.jdata.matcher.type.Subclass;
import de.bs.jdata.tracking.examples.Example;

@SuppressWarnings("rawtypes")
public class TypeTest {
	private Type<?> testee;

	@Test
	public void testTypeWithIsOrOf() {
		OrOf<Double> orOf = new OrOf<Double>();
		orOf.init(new GenericData(new TypeGenericDataPart(null, Double.class)));
		
		Is<Float, ?> is = new Is();
		is.init(new GenericData(new TypeGenericDataPart(null, Float.class), new MatcherGenericDataPart(null, orOf)));
		
		testee = new Type<Is>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, is)));
		
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(true));
		assertThat(testee.check(Double.class, null), equalTo(true));
		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(false));
		
	}
	
	@Test
	public void testTypeWithIsOrOrOf() {
		OrOf<Short> orOf = new OrOf<Short>();
		orOf.init(new GenericData(new TypeGenericDataPart(null, Short.class)));
		
		Or<Long, ?> or = new Or();
		or.init(new GenericData(new TypeGenericDataPart(null, Long.class), new MatcherGenericDataPart(null, orOf)));
		
		Is<Integer, ?> is = new Is();
		is.init(new GenericData(new TypeGenericDataPart(null, Integer.class), new MatcherGenericDataPart(null, or)));
		
		testee = new Type<Is>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, is)));
		
		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(true));
		assertThat(testee.check(Long.class, null), equalTo(true));
		assertThat(testee.check(Short.class, null), equalTo(true));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(Double.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeExactWithString() {
		Exact<String> exact = new Exact<String>();
		exact.init(new GenericData(new TypeGenericDataPart(null, String.class)));
		
		testee = new Type<Exact>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, exact)));
		
		assertThat(testee.check(String.class, null), equalTo(true));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeExactWithInteger() {
		Exact<Integer> exact = new Exact<Integer>();
		exact.init(new GenericData(new TypeGenericDataPart(null, Integer.class)));
		
		testee = new Type<Exact>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, exact)));

		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(true));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeExtendsWithB() {
		Extends<B> extend = new Extends<B>();
		extend.init(new GenericData(new TypeGenericDataPart(null, B.class)));
		
		testee = new Type<Extends>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, extend)));

		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(true));
		assertThat(testee.check(C.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(true));
		assertThat(testee.check(F.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeExtendsWithC() {
		Extends<C> extend = new Extends<C>();
		extend.init(new GenericData(new TypeGenericDataPart(null, C.class)));
		
		testee = new Type<Extends>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, extend)));

		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(C.class, null), equalTo(true));
		assertThat(testee.check(E.class, null), equalTo(false));
		assertThat(testee.check(F.class, null), equalTo(true));
	}
	
	@Test
	public void testTypeSubclassWithB() {
		Subclass<B> subclass = new Subclass<TypeTest.B>();
		subclass.init(new GenericData(new TypeGenericDataPart(null, B.class)));
		
		testee = new Type<Subclass>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, subclass)));

		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(C.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(true));
		assertThat(testee.check(F.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeSubclassWithC() {
		Subclass<C> subclass = new Subclass<TypeTest.C>();
		subclass.init(new GenericData(new TypeGenericDataPart(null, C.class)));
		
		testee = new Type<Subclass>();
		testee.init(new GenericData(new MatcherGenericDataPart(null, subclass)));

		assertThat(testee.check(String.class, null), equalTo(false));
		assertThat(testee.check(Integer.class, null), equalTo(false));
		assertThat(testee.check(Float.class, null), equalTo(false));
		assertThat(testee.check(Example.class, null), equalTo(false));
		assertThat(testee.check(A.class, null), equalTo(false));
		assertThat(testee.check(B.class, null), equalTo(false));
		assertThat(testee.check(C.class, null), equalTo(false));
		assertThat(testee.check(E.class, null), equalTo(false));
		assertThat(testee.check(F.class, null), equalTo(true));
	}
	
	@After
	public void teardown() {
		testee = null;
	}
	
	class A {}
	class B extends A {}
	class C extends A {}
	class D extends B {}
	class E extends B {}
	class F extends C {}
}
