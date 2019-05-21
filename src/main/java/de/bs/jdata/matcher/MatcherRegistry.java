package de.bs.jdata.matcher;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.bs.jdata.matcher.init.GenericData;
import de.bs.jdata.matcher.init.GenericDataPart;
import de.bs.jdata.matcher.init.MatcherGenericDataPart;
import de.bs.jdata.matcher.init.TypeGenericDataPart;
import de.bs.jdata.util.ClassUtil;

/**
 * Creates and cache Matcher.
 * <p>
 * Method {@link #getMatcherFrom(Class)} creates the matcher from the passed
 * {@link Class} object and caches it to avoid recreation.
 * 
 * @author little Rathi
 *
 */
public class MatcherRegistry {
	private GenericAnalyzer genericAnalyzer = new GenericAnalyzer();

	private Map<Class<?>, Matcher> matcher = new HashMap<Class<?>, Matcher>();

	/**
	 * Checks if the Matcher already exist and if not it creates the matcher, then
	 * returns it.
	 * 
	 * @param definition that contains in the inheritance the {@link Matcher}
	 *                   generic elements
	 * @return the Matcher if it is cached or can be created from the passed
	 *         {@link Class} object, otherwise <code>null</code>
	 */
	public Matcher getMatcherFrom(final Class<?> definition) {
		if (matcher.containsKey(definition)) {
			return matcher.get(definition);
		}

		GenericElement elementMatcher = null;
		try {
			elementMatcher = findConverterMatcher(definition);
		} catch (ClassNotFoundException e) {
			// TODO: Proper exception handling
		}

		if (elementMatcher == null) {
			return null;
		}

		Matcher newMatcher = instantiateMatcher(elementMatcher);
		matcher.put(definition, newMatcher);
		return newMatcher;
	}

	/**
	 * Walk through the class hierarchy (class and interfaces) to find generic
	 * declaration that contains one element with inherit from {@link Matcher}, like
	 * {@link Type} and {@link UseCustom}.
	 * 
	 * @param definition that will be searched for the {@link Matcher} class in the
	 *                   class hierarchy
	 * @return found {@link GenericElement} from the element, that implements the
	 *         {@link Matcher}, otherwise <code>null</code>
	 * @throws ClassNotFoundException when a class that is in the generic part
	 *                                cannot be loaded
	 */
	private GenericElement findConverterMatcher(final Class<?> definition) throws ClassNotFoundException {
		if (definition == null || definition.equals(Object.class)) {
			return null;
		}

		List<Class<?>> classDefinitions = new LinkedList<Class<?>>();
		classDefinitions.add(definition);

		for (int i = 0; i < classDefinitions.size(); i++) {
			Class<?> workingClass = classDefinitions.get(i);

			GenericElement element = getMatcherGenericElement(workingClass.getGenericSuperclass(),
					workingClass.getGenericInterfaces());

			if (element != null) {
				return element;
			}

			if (workingClass.getSuperclass() != null && !workingClass.getSuperclass().equals(Object.class)) {
				classDefinitions.add(workingClass.getSuperclass());
			}

			for (final Class<?> interfaceClass : workingClass.getInterfaces()) {
				classDefinitions.add(interfaceClass);
			}
		}
		return null;
	}

	/**
	 * Process from a {@link java.lang.reflect.Type} the generic superclass and
	 * generic interfaces, and search the {@link Matcher}.
	 * 
	 * @param genericSuperclass that represents the generic part from the superclass
	 * @param genericInterfaces that represents the generic part from the
	 *                          implemented interfaces
	 * @return a {@link GenericElement} when find a element that implements the
	 *         {@link Matcher} interface, otherwise <code>null</code>
	 * @throws ClassNotFoundException when a class that is in the generic part
	 *                                cannot be loaded
	 */
	private GenericElement getMatcherGenericElement(final java.lang.reflect.Type genericSuperclass,
			final java.lang.reflect.Type[] genericInterfaces) throws ClassNotFoundException {
		GenericElement result = null;
		if (genericSuperclass != null) {
			result = getMatcherGenericElement(genericSuperclass.getTypeName());
		}

		if (result == null) {
			for (final java.lang.reflect.Type genericInterface : genericInterfaces) {
				result = getMatcherGenericElement(genericInterface.getTypeName());
				if (result != null) {
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Transforms the generic, represented as a {@link String} into a
	 * {@link GenericElement} structure and search in the structure for a
	 * {@link GenericElement} that inherit from {@link Matcher}
	 * 
	 * @param genericString that represents the generic definition in a
	 *                      {@link String}
	 * @return the found {@link GenericElement} that inherit from {@link Matcher},
	 *         independent where it is in the original structure, otherwise
	 *         <code>null</code>
	 * @throws ClassNotFoundException when a class cannot be loaded from the generic
	 *                                {@link String}
	 */
	private GenericElement getMatcherGenericElement(final String genericString) throws ClassNotFoundException {
		GenericElement result = genericAnalyzer.getGenericStructure(genericString);
		if (result != null) {
			result = findMatcherInStructure(result);
		}
		return result;
	}

	/**
	 * Recursive method that search in the passed {@link GenericElement} structure
	 * for a {@link GenericElement} that inherit from {@link Matcher}
	 * 
	 * @param structure that will be check and from it the generic list also will
	 *                  also be searched recursive
	 * @return the found {@link GenericElement}, otherwise <code>null</code>
	 * @throws ClassNotFoundException if from the {@link GenericElement} the
	 *                                {@link Class} cannot be loaded
	 */
	private GenericElement findMatcherInStructure(final GenericElement structure) throws ClassNotFoundException {
		Class<?> structureClass = Class.forName(structure.getElementName());
		if (Matcher.class.isAssignableFrom(structureClass)) {
			return structure;
		}
		for (final GenericElement genericStructure : structure.getGenerics()) {
			GenericElement subGeneric = findMatcherInStructure(genericStructure);
			if (subGeneric != null) {
				return subGeneric;
			}
		}
		return null;
	}

	/**
	 * Instantiate the {@link Matcher} object, that is referenced in the passed
	 * {@link GenericElement}.
	 * 
	 * @param element that reference a class that extends the {@link Matcher} class
	 * @return the usable {@link Matcher} instance object
	 */
	private Matcher instantiateMatcher(final GenericElement element) {
		Matcher matcher = (Matcher) ClassUtil.instantiate(element.getElementName());

		List<GenericDataPart> parts = processGeneric(element.getGenerics());
		matcher.init(new GenericData(parts.toArray(new GenericDataPart[parts.size()])));

		return matcher;
	}

	/**
	 * Process generic elements from a {@link GenericElement}. It distinguish
	 * between a simple generic definition and the one, that implements the
	 * {@link MatcherElement} and process them differently
	 * 
	 * @param generics list of {@link GenericElement} that comes from a "super"
	 *                 {@link GenericElement}
	 * @return list of generated {@link GenericDataPart} for using for the
	 *         {@link MatcherElement#init(GenericData)} method in the "super"
	 *         {@link GenericElement}
	 */
	private List<GenericDataPart> processGeneric(final List<GenericElement> generics) {
		List<GenericDataPart> parts = new LinkedList<GenericDataPart>();

		for (final GenericElement generic : generics) {
			Class<?> genericClass = ClassUtil.getClass(generic.getElementName());

			if (MatcherElement.class.isAssignableFrom(genericClass)) {
				MatcherElement matcherInitializer = (MatcherElement) ClassUtil.instantiate(genericClass);

				List<GenericDataPart> subParts = processGeneric(generic.getGenerics());
				matcherInitializer.init(new GenericData(subParts.toArray(new GenericDataPart[subParts.size()])));

				parts.add(new MatcherGenericDataPart(generic.toString(), matcherInitializer));
			} else {
				parts.add(new TypeGenericDataPart(generic.toString(), genericClass));
			}
		}

		return parts;
	}
}
