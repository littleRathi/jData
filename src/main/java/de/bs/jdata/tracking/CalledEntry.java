package de.bs.jdata.tracking;

/**
 * Represents a called element, that is the called specific method.
 * <p>
 * Represents an entry that expect also information for the parameters. It
 * will store not only which class and method is called, it also allow
 * a identifier, and the parameters name and value.
 * 
 * <p>
 * The parameters name and values are passed in the varargs of parameters, and
 * must be every time a pair of name and value. If the object is complex
 * and would create a big String for the value, consider only parts of 
 * it, like an ID.
 * 
 * <p>
 * Format: &lt;class&gt;[&lt;identifier&gt;].&lt;methodeName&gt;([&lt;a&gt; : &lt;b&gt; [, &lt;a&gt; : &lt;b&gt;]*]?)
 * <p>
 * <ul>
 * 	<li>a is the variable name</li>
 *  <li>b is the variable type</li>
 * </ul>
 * 
 * @author little Rathi
 *
 */
public class CalledEntry implements TrackingEntry {
	private Class<?> source;
	private String identifier;
	private String calledMethod;
	private String[] parameters;

	public CalledEntry(Object source, String identifier, String calledMethod, String... parameters) {
		this.source = source.getClass();
		this.identifier = identifier;
		this.calledMethod = calledMethod;
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return source.getName() + "[" + identifier + "]." + calledMethod + "(" + parametersToString() + ")";
	}

	private String parametersToString() {
		if (parameters != null && parameters.length > 0) {
			StringBuffer sb = new StringBuffer(parameters[0]).append(": ").append(parameters[1]);
			
			for (int i = 2; i < parameters.length; i += 2) {
				sb.append(", ").append(parameters[i]).append(": ").append(parameters[i+1]);
			}
			
			return sb.toString();
		}
		return "";
	}
}
