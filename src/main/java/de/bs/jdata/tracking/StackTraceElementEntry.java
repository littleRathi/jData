package de.bs.jdata.tracking;

/**
 * Represents a caller element, which calls a specific method.
 * <p>
 * Represents an entry for a StackTraceElement. A StackTraceElement can be
 * extracted from simple through {@link Thread#getStackTrace()}, if the
 * {@link Thread} is not present, it can be fetched by
 * {@link Thread#currentThread()}. Another way is to use the
 * {@link Tracking#findCallerOf(Class...)} Method, when it fits. When use
 * want to use with the findCallOf method, simply use {@link #StackTraceElementEntry(Class...)}.
 * 
 * <p>
 * Format: [caller: &lt;className&gt;.&lt;methodName&gt;(&lt;lineNumber&gt;)]
 * 
 * TODO: rename it to CallerEntry? as Opposite to {@link CalledEntry}
 * 
 * @author little Rathi
 *
 */
public class StackTraceElementEntry implements TrackingEntry {
	private StackTraceElement element;

	public StackTraceElementEntry(final StackTraceElement element) {
		this.element = element;
	}
	
	/**
	 * Shorthand for calling {@link Tracking#findCallerOf(Class...)} and pass the result
	 * to {@link #StackTraceElementEntry(StackTraceElement)}.
	 * @param classes see {@link Tracking#findCallerOf(Class...)}
	 */
	public StackTraceElementEntry(final Class<?>... classes) {
		this.element = Tracking.findCallerOf(classes);
	}

	@Override
	public String toString() {
		return "[caller: " + format(element) + "]";
	}

	/**
	 * Format a {@link StackTraceElement} in a partly StrackTrace look.
	 * 
	 * @param ste that will be formated
	 * @return a {@link String} representation for the passed
	 *         {@link StackTraceElement}
	 * @throws NullPointerException if passed parameter ste is null
	 */
	public static String format(final StackTraceElement ste) {
		if (ste == null) {
			new NullPointerException("Parameter ste is null");
		}
		return ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getLineNumber() + ")";
	}
}
