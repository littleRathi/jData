package de.bs.jdata;

import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.bs.jdata.configurator.Configurator;
import de.bs.jdata.example.annotation.correct.ClassWithTarget;
import de.bs.jdata.example.annotation.correct.WithAnnotation;
import de.bs.jdata.example.annotation.wrong.use.DoubleName;
import de.bs.jdata.example.annotation.wrong.use.FinalField;
import de.bs.jdata.example.annotation.wrong.use.StaticField;
import de.bs.jdata.example.no.annotations.correct.WithoutAnnotation;
import de.bs.jdata.example.no.annotations.no.fields.NoFields;
import de.bs.jdata.manager.object.ObjectManager;
import de.bs.jdata.manager.object.ObjectManagerBuilder;

public class JDataTest {
	@Test
	public void forTargetWithClassWithTarget() {
		ObjectManagerBuilder<?> objectManagerBuilder = JData.forTarget(new ClassWithTarget());
	}
	
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder 
	public void forTargetWithNoTarget() {
		JData.forTarget(new Object());
	}
	
	@Test
	public void forObjectWithWithAnnotation() {
		ObjectManagerBuilder<WithAnnotation> objectManagerBuilder = JData.forObjectClass(WithAnnotation.class);
	}
	
	@Test
	public void forObjectWithWithoutAnnotation() {
		ObjectManagerBuilder<WithoutAnnotation> objectManagerBuilder = JData.forObjectClass(WithoutAnnotation.class);
	}
	
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithNoFields() {
		ObjectManagerBuilder<NoFields> objectManagerBuilder = JData.forObjectClass(NoFields.class);
	}
	
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithDoubleName() {
		ObjectManagerBuilder<DoubleName> objectManagerBuilder = JData.forObjectClass(DoubleName.class);
	}
	
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithFinalField() {
		ObjectManagerBuilder<FinalField> objectManagerBuilder = JData.forObjectClass(FinalField.class);
	}
	
	@Test(expected=RuntimeException.class) // TODO: Exception is a placeholder
	public void forObjectWithStaticField() {
		ObjectManagerBuilder<StaticField> objectManagerBuilder = JData.forObjectClass(StaticField.class);
	}
	
	//
	@Test
	public void testForObjectClass() {
		ObjectManagerBuilder jdata = mock(ObjectManagerBuilder.class);
		
		when(jdata.bind(notNull(Configurator.class))).thenReturn(null);
//		ObjectManager<Simple> simpleManager = JData.forObjectClass(Simple.class).bind(null).build();
		
		assertThat(4, is(equalTo(4)));
	}
	
	public void forObjectClassWithNull() {
		JData.forObjectClass(null);
	}
	
	/*
	 * Possible Cases:
	 * - JData, if it contains one or more DataField -> fine; no JData or no DataField -> Exception
	 * - Need DataField Annotation (min. 1); if presents -> fine; not presents -> Exception
	 * - Default all Attributes as DataField?
	 */
	public void forObjectClassWithProg() {
		JData.forObjectClass(null/*WrongClass.class*/);
	}
	
	public void forObjectClassWithSingleExample() {
		JData.forObjectClass(WithoutAnnotation.class);
	}
	
	private void abc() {
		JData.addConverter(null);
		JData.addTransformator(null);
	}
}
