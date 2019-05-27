package de.bs.jdata.matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.matcher.substitute.BooleanPrimitive;
import de.bs.jdata.matcher.substitute.BytePrimitive;
import de.bs.jdata.matcher.substitute.CharPrimitive;
import de.bs.jdata.matcher.substitute.DoublePrimitive;
import de.bs.jdata.matcher.substitute.FloatPrimitive;
import de.bs.jdata.matcher.substitute.IntPrimitive;
import de.bs.jdata.matcher.substitute.LongPrimitive;
import de.bs.jdata.matcher.substitute.ShortPrimitive;
import de.bs.jdata.matcher.type.Exact;
import de.bs.jdata.matcher.type.Is;
import de.bs.jdata.matcher.type.Or;
import de.bs.jdata.matcher.type.OrOf;

public class MatcherRegistryTestWithTypeAndSubstitute {
	private MatcherRegistry testee;
	
	@Test
	public void testTypeWithIntExample() {
		Matcher handler = testee.getMatcherFrom(IntExample.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Integer.class, null), equalTo(false));
		assertThat(handler.check(int.class, null), equalTo(true));
		assertThat(handler.check(Short.class, null), equalTo(false));
		assertThat(handler.check(short.class, null), equalTo(true));
		assertThat(handler.check(Long.class, null), equalTo(false));
		assertThat(handler.check(long.class, null), equalTo(true));
		assertThat(handler.check(Float.class, null), equalTo(false));
		assertThat(handler.check(float.class, null), equalTo(false));
		assertThat(handler.check(Double.class, null), equalTo(false));
		assertThat(handler.check(double.class, null), equalTo(false));
		assertThat(handler.check(Boolean.class, null), equalTo(false));
		assertThat(handler.check(boolean.class, null), equalTo(false));
		assertThat(handler.check(Byte.class, null), equalTo(false));
		assertThat(handler.check(byte.class, null), equalTo(false));
		assertThat(handler.check(Character.class, null), equalTo(false));
		assertThat(handler.check(char.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeWithIntOnlyExample() {
		Matcher handler = testee.getMatcherFrom(IntOnlyExample.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Integer.class, null), equalTo(true));
		assertThat(handler.check(int.class, null), equalTo(true));
		assertThat(handler.check(Short.class, null), equalTo(false));
		assertThat(handler.check(short.class, null), equalTo(false));
		assertThat(handler.check(Long.class, null), equalTo(false));
		assertThat(handler.check(long.class, null), equalTo(false));
		assertThat(handler.check(Float.class, null), equalTo(false));
		assertThat(handler.check(float.class, null), equalTo(false));
		assertThat(handler.check(Double.class, null), equalTo(false));
		assertThat(handler.check(double.class, null), equalTo(false));
		assertThat(handler.check(Boolean.class, null), equalTo(false));
		assertThat(handler.check(boolean.class, null), equalTo(false));
		assertThat(handler.check(Byte.class, null), equalTo(false));
		assertThat(handler.check(byte.class, null), equalTo(false));
		assertThat(handler.check(Character.class, null), equalTo(false));
		assertThat(handler.check(char.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeWithFloatingExample() {
		Matcher handler = testee.getMatcherFrom(FloatingExample.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Integer.class, null), equalTo(false));
		assertThat(handler.check(int.class, null), equalTo(false));
		assertThat(handler.check(Short.class, null), equalTo(false));
		assertThat(handler.check(short.class, null), equalTo(false));
		assertThat(handler.check(Long.class, null), equalTo(false));
		assertThat(handler.check(long.class, null), equalTo(false));
		assertThat(handler.check(Float.class, null), equalTo(false));
		assertThat(handler.check(float.class, null), equalTo(true));
		assertThat(handler.check(Double.class, null), equalTo(false));
		assertThat(handler.check(double.class, null), equalTo(true));
		assertThat(handler.check(Boolean.class, null), equalTo(false));
		assertThat(handler.check(boolean.class, null), equalTo(false));
		assertThat(handler.check(Byte.class, null), equalTo(false));
		assertThat(handler.check(byte.class, null), equalTo(false));
		assertThat(handler.check(Character.class, null), equalTo(false));
		assertThat(handler.check(char.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeWithBooleanExample() {
		Matcher handler = testee.getMatcherFrom(BooleanExample.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Integer.class, null), equalTo(false));
		assertThat(handler.check(int.class, null), equalTo(false));
		assertThat(handler.check(Short.class, null), equalTo(false));
		assertThat(handler.check(short.class, null), equalTo(false));
		assertThat(handler.check(Long.class, null), equalTo(false));
		assertThat(handler.check(long.class, null), equalTo(false));
		assertThat(handler.check(Float.class, null), equalTo(false));
		assertThat(handler.check(float.class, null), equalTo(false));
		assertThat(handler.check(Double.class, null), equalTo(false));
		assertThat(handler.check(double.class, null), equalTo(false));
		assertThat(handler.check(Boolean.class, null), equalTo(false));
		assertThat(handler.check(boolean.class, null), equalTo(true));
		assertThat(handler.check(Byte.class, null), equalTo(false));
		assertThat(handler.check(byte.class, null), equalTo(false));
		assertThat(handler.check(Character.class, null), equalTo(false));
		assertThat(handler.check(char.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeWithByteExample() {
		Matcher handler = testee.getMatcherFrom(ByteExample.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Integer.class, null), equalTo(false));
		assertThat(handler.check(int.class, null), equalTo(false));
		assertThat(handler.check(Short.class, null), equalTo(false));
		assertThat(handler.check(short.class, null), equalTo(false));
		assertThat(handler.check(Long.class, null), equalTo(false));
		assertThat(handler.check(long.class, null), equalTo(false));
		assertThat(handler.check(Float.class, null), equalTo(false));
		assertThat(handler.check(float.class, null), equalTo(false));
		assertThat(handler.check(Double.class, null), equalTo(false));
		assertThat(handler.check(double.class, null), equalTo(false));
		assertThat(handler.check(Boolean.class, null), equalTo(false));
		assertThat(handler.check(boolean.class, null), equalTo(false));
		assertThat(handler.check(Byte.class, null), equalTo(false));
		assertThat(handler.check(byte.class, null), equalTo(true));
		assertThat(handler.check(Character.class, null), equalTo(false));
		assertThat(handler.check(char.class, null), equalTo(false));
	}
	
	@Test
	public void testTypeWithCharExample() {
		Matcher handler = testee.getMatcherFrom(CharExample.class);
		
		assertThat(handler, instanceOf(Type.class));
		assertThat(handler.check(Integer.class, null), equalTo(false));
		assertThat(handler.check(int.class, null), equalTo(false));
		assertThat(handler.check(Short.class, null), equalTo(false));
		assertThat(handler.check(short.class, null), equalTo(false));
		assertThat(handler.check(Long.class, null), equalTo(false));
		assertThat(handler.check(long.class, null), equalTo(false));
		assertThat(handler.check(Float.class, null), equalTo(false));
		assertThat(handler.check(float.class, null), equalTo(false));
		assertThat(handler.check(Double.class, null), equalTo(false));
		assertThat(handler.check(double.class, null), equalTo(false));
		assertThat(handler.check(Boolean.class, null), equalTo(false));
		assertThat(handler.check(boolean.class, null), equalTo(false));
		assertThat(handler.check(Byte.class, null), equalTo(false));
		assertThat(handler.check(byte.class, null), equalTo(false));
		assertThat(handler.check(Character.class, null), equalTo(false));
		assertThat(handler.check(char.class, null), equalTo(true));
	}
	
	@Before
	public void setup() {
		testee = new MatcherRegistry();
	}
	
	
	@After
	public void teardown() {
		testee = null;
	}
	
	public class Converter<H extends Matcher> {
	}

	public class IntExample extends Converter<Type<Is<IntPrimitive, Or<ShortPrimitive, OrOf<LongPrimitive>>>>> {
	}

	public class IntOnlyExample extends Converter<Type<Is<Integer, OrOf<IntPrimitive>>>> {
	}
	
	public class FloatingExample extends Converter<Type<Is<FloatPrimitive, OrOf<DoublePrimitive>>>> {
	}
	
	public class BooleanExample extends Converter<Type<Exact<BooleanPrimitive>>> {
	}
	
	public class ByteExample extends Converter<Type<Exact<BytePrimitive>>> {
	}
	
	public class CharExample extends Converter<Type<Exact<CharPrimitive>>> {
	}
}
