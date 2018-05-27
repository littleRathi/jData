package de.bs.jdata;

import org.junit.Test;

import de.bs.jdata.example.Simple;
import de.bs.jdata.manager.object.ObjectManager;

public class JDataTest {
	@Test
	public void testForObjectClass() {
		ObjectManager<Simple> simpleManager = JData.forObjectClass(Simple.class).bind(null).build();
	}
}
