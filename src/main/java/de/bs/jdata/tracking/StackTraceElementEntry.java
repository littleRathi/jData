package de.bs.jdata.tracking;

public class StackTraceElementEntry implements TrackingEntry {
	private StackTraceElement element;

	public StackTraceElementEntry(final StackTraceElement element) {
		this.element = element;
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
