  package de.bs.jdata.matcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GenericAnalyzerTest {
	private GenericAnalyzer testee;
	
	private String example;
	private GenericElement result;
	private GenericElement expected;
	
	@Test
	public void getGenericStructureSimple() {
		example = "a<b>";
		
		result = testee.getGenericStructure(example);
		
		expected = new GenericElement("a",
				new GenericElement("b"));
		
		assertThat(result, equalTo(expected));
		assertThat(result.toString(), equalTo(expected.toString()));
	}
	
	@Test
	public void getGenericStructure4Deep() {
		example = "a<b<c<d<e>>>>";
		
		result = testee.getGenericStructure(example);
		
		expected = new GenericElement("a", 
				new GenericElement("b", 
						new GenericElement("c", 
								new GenericElement("d", 
										new GenericElement("e")))));
		
		assertThat(result, equalTo(expected));
		assertThat(result.toString(), equalTo(expected.toString()));
	}
	
	@Test
	public void getGenericStructure2DeepDouble() {
		example = "a<b<d<e>>,c<d<e>>>";
		
		result  = testee.getGenericStructure(example);
		
		expected = new GenericElement("a",
				new GenericElement("b",
						new GenericElement("d", 
								new GenericElement("e"))),
				new GenericElement("c",
						new GenericElement("d",
								new GenericElement("e"))));
		
		assertThat(result, equalTo(expected));
		assertThat(result.toString(), equalTo(expected.toString()));
	}
	
	@Test
	public void getGenericStructureSimpleMulti() {
		example = "a<b,c,d,e>";
		
		result = testee.getGenericStructure(example);
		
		expected = new GenericElement("a",
				new GenericElement("b"),
				new GenericElement("c"),
				new GenericElement("d"),
				new GenericElement("e"));
		
		assertThat(result, equalTo(expected));
		assertThat(result.toString(), equalTo(expected.toString()));
	}
	
	@Test
	public void getGenericStructureSimpleMutliSecondLevel() {
		example = "a<b<d,e,f,g>,c<d,e,f,g>>";
		
		result = testee.getGenericStructure(example);
		
		expected = new GenericElement("a",
				new GenericElement("b",
						new GenericElement("d"),
						new GenericElement("e"),
						new GenericElement("f"),
						new GenericElement("g")),
				new GenericElement("c",
						new GenericElement("d"),
						new GenericElement("e"),
						new GenericElement("f"),
						new GenericElement("g")));
		
		assertThat(result, equalTo(expected));
		assertThat(result.toString(), equalTo(expected.toString()));
	}
	
	@Test
	public void getGenericStructureComplex() {
		example = "a<b<c,d<e,f>>,g<h,i<k>,j<l,m,n>>>";
		
		result = testee.getGenericStructure(example);
		
		expected = new GenericElement("a",
				new GenericElement("b",
						new GenericElement("c"),
						new GenericElement("d",
								new GenericElement("e"),
								new GenericElement("f"))),
				new GenericElement("g",
						new GenericElement("h"),
						new GenericElement("i",
								new GenericElement("k")),
						new GenericElement("j",
								new GenericElement("l"),
								new GenericElement("m"),
								new GenericElement("n"))));
		
		assertThat(result, equalTo(expected));
		assertThat(result.toString(), equalTo(expected.toString()));
	}
	
	@Before
	public void setup() {
		testee = new GenericAnalyzer();
	}
	
	@After
	public void teardown() {
		testee = null;
		example = "";
		result = null;
		expected = null;
	}
}
