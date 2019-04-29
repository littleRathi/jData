package de.bs.jdata;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Ignore;
import org.junit.Test;

import de.bs.jdata.example.annotation.correct.ClassWithTarget;
import de.bs.jdata.example.annotation.correct.WithAnnotation;
import de.bs.jdata.example.annotation.wrong.use.DoubleName;
import de.bs.jdata.example.annotation.wrong.use.FinalField;
import de.bs.jdata.example.annotation.wrong.use.StaticField;
import de.bs.jdata.example.annotation.wrong.use.WrongClass;
import de.bs.jdata.example.no.annotations.correct.WithoutAnnotation;
import de.bs.jdata.example.no.annotations.no.fields.NoFields;
import de.bs.jdata.manager.object.ObjectManagerBuilder;

public class JDataTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void forObjectClassWithNull() {
		JData.forObjectClass(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void forObjectClassFromJavaLangPackage() {
		JData.forObjectClass(String.class);
	}
	
	@Test//(expected=IllegalArgumentException.class)
	public void forObjectClassWithProg() {
		JData.forObjectClass(WrongClass.class);
	}
	
	@Ignore
	@Test
	public void forObjectClassWithSingleExample() {
		JData.forObjectClass(WithoutAnnotation.class);
	}
	
	@Ignore
	@Test
	public void forObjectWithWithAnnotation() {
		ObjectManagerBuilder<WithAnnotation> objectManagerBuilder = JData.forObjectClass(WithAnnotation.class);
	}

	@Ignore
	@Test
	public void forObjectWithWithoutAnnotation() {
		ObjectManagerBuilder<WithoutAnnotation> objectManagerBuilder = JData.forObjectClass(WithoutAnnotation.class);
	}

	@Ignore
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithNoFields() {
		ObjectManagerBuilder<NoFields> objectManagerBuilder = JData.forObjectClass(NoFields.class);
	}

	@Ignore
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithDoubleName() {
		ObjectManagerBuilder<DoubleName> objectManagerBuilder = JData.forObjectClass(DoubleName.class);
	}

	@Ignore
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithFinalField() {
		ObjectManagerBuilder<FinalField> objectManagerBuilder = JData.forObjectClass(FinalField.class);
	}
	
	@Ignore
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithStaticField() {
		ObjectManagerBuilder<StaticField> objectManagerBuilder = JData.forObjectClass(StaticField.class);
	}
	
	@Ignore
	@Test
	public void testForObjectClass() {
		ObjectManagerBuilder jdata = mock(ObjectManagerBuilder.class);
		
//		when(jdata.bind(notNull(Configurator.class))).thenReturn(null);
//		ObjectManager<Simple> simpleManager = JData.forObjectClass(Simple.class).bind(null).build();
		
		assertThat(4, is(equalTo(4)));
	}
	
	@Ignore
	@Test
	public void forTargetWithClassWithTarget() {
		ObjectManagerBuilder<?> objectManagerBuilder = JData.forTarget(new ClassWithTarget());
	}

	@Ignore
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder 
	public void forTargetWithNoTarget() {
		JData.forTarget(new Object());
	}
	
	private void abc() {
		JData.addConverter(null);
		JData.addTransformator(null);
	}
}
