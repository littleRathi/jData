package de.bs.jdata.tracking;

import java.util.LinkedList;

public class Tracking {

	private Tracking parent;

	private final LinkedList<TrackingEntry> entries = new LinkedList<TrackingEntry>();

	public Tracking() {
	}

	public Tracking(final Tracking parentTracker) {
		this.parent = parentTracker;
	}

	public void add(final TrackingEntry entry) {
		if (parent != null) {
			parent.add(entry);
		}
		entries.addFirst(entry);
	}

	public Tracking getParent() {
		return parent;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (TrackingEntry trackingEntry : entries) {
			sb.append("\n").append(trackingEntry);
		}

		return sb.toString();
	}

	/**
	 * Find the {@link StackTraceElement} element, that represents the 'caller' of one
	 * of the passed parameter {@link Class} objects. This can be used to enrich
	 * data, in particular for exceptions.
	 * 
	 * @param classes that represents the classes, that the 'caller' calls directly
	 * @return the {@link StackTraceElement} that represents the 'caller' element, else
	 * <code>null</code>
	 */
	public static StackTraceElement findCallerOf(Class<?>... classes) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement result = null;

		boolean match = false;
		for (StackTraceElement ste : stackTrace) {
			if (!match) {
				for (Class<?> clazz : classes) {
					if (ste.getClassName().equals(clazz.getName())) {
						match = true;
					}
				}
			}
			if (match) {
				boolean stillMatch = false;
				for (Class<?> clazz : classes) {
					if (ste.getClassName().equals(clazz.getName())) {
						stillMatch = true;
					}
				}

				if (!stillMatch) {
					result = ste;
					break;
				}
			}
		}

		return result;
	}
}
