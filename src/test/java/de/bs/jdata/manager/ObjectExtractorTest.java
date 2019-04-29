package de.bs.jdata.manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.bs.jdata.JDataMode;
import de.bs.jdata.example.annotation.correct.SubDataUseAllFields;
import de.bs.jdata.example.annotation.correct.WithAnnotation;
import de.bs.jdata.manager.object.ObjectExtractor;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ObjectExtractorTest {
	private ObjectExtractor testee;
	
	/*
	 * 1) no annotation
	 * 2) with annotation
	 * 3) missing fields
	 * ?) sämgliche Fehlerfehle noch mal?
	 */
	@Test
	public void testExtractWithoutAnnotation() {
		ObjectDefinition<SubDataUseAllFields> result = testee.extract(SubDataUseAllFields.class);
		
		assertThat(result, notNullValue());
		assertThat(result.getDefinedObjekt(), is((Object)SubDataUseAllFields.class));
		assertThat(result.getDataMode(), equalTo(JDataMode.USE_ALL_FIELDS));
		assertThat(result.getDesc(), equalTo(""));
		assertThat(result.getName(), equalTo(String.format(ObjectExtractor.DEFAULT_FOR_EMPTY_NAME_FORMAT, SubDataUseAllFields.class.getName())));
		assertThat(result.getFields().size(), equalTo(1));
		assertThat(result.getFields(), hasKey("onlyOneField"));
	}
	
	@Test
	public void testExtractWithAnnotation() {
		ObjectDefinition<WithAnnotation> result = testee.extract(WithAnnotation.class);
		
		assertThat(result, notNullValue());
		assertThat(result.getDefinedObjekt(), is((Object)WithAnnotation.class));
		assertThat(result.getDataMode(), equalTo(JDataMode.USE_ONLY_ANNOTATIONS));
		assertThat(result.getDesc(), equalTo("Example using all existing annotations of this library.. Meaningful to this example"));
		assertThat(result.getName(), equalTo("Annotation Example"));
		assertThat(result.getFields().size(), equalTo(4));
//		assertThat(result.getFields(), allOf(hasKey("abc"), hasKey("def"), hasKey("ghi"), hasKey("subDataObject")));
		assertThat(result.getFields(), allOf(hasEntry(is("abc"), notNullValue()), hasEntry(is("def"), notNullValue()), hasEntry(is("ghi"), notNullValue()), hasEntry(is("subDataObject"), notNullValue())));
	}
	
	@Before
	public void setup() {
		testee = new ObjectExtractor();
	}
	
	@After
	public void teardown() {
		testee = null;
	}

}
