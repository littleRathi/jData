package de.bs.jdata.tracking;

/**
 * Format: class[identifier].methode([a : b [, a : b]*]?)
 * <p>
 * <ul>
 * 	<li>a is the variable name</li>
 *  <li>b is the variable type</li>
 * </ul>
 * 
 * @author little Rathi
 *
 */
public class CallerEntry implements TrackingEntry {
	private Class<?> source;
	private String identifier;
	private String calledMethod;
	private String[] parameters;

	public CallerEntry(Object source, String identifier, String calledMethod, String... parameters) {
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
