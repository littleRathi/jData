package de.bs.jdata.example.annotation.correct;

import de.bs.jdata.JDataTarget;

/**
 * Class that contains the data object itself, works as a container, that make
 * is possible to replace a changed configuration as a whole (when a changed
 * configuration file changes and the new created data object should replace the
 * old one, in one step).
 * 
 * @author little Rathi
 *
 */
public class ClassWithTarget {
	@JDataTarget(name = "mainDataObject")
	private static WithAnnotation dataObject;
}
