package de.bs.jdata.tracking;

import java.util.LinkedList;

/**
 * Tracking mechanism for tracking calls and creating more helpful exceptions
 * messages. For that at some important points in the code there can
 * {@link TrackingEntry} created and added to the a {@link Tracking} object.
 * This allows that not only the simple stacktrace in an exceptions, it let it
 * enrich it by different kinds of {@link TrackingEntry}'s (like
 * {@link CalledEntry} or {@link StackTraceElementEntry}).
 * 
 * TODO: creating so called tracking exceptions? IllegalArgumentExceptions
 * extends original
 * 
 * 
 * @author little Rathi
 *
 */
public class Tracking {

	private Tracking parent;

	private final LinkedList<TrackingEntry> entries = new LinkedList<TrackingEntry>();

	public Tracking() {
	}

	/**
	 * Constructor that allows to pass a parent {@link Tracking} object. All
	 * {@link TrackingEntry} that get added to the {@link Tracking} object get also
	 * added to the parent {@link Tracking} object.
	 * 
	 * @param parentTracker that get also passed all {@link TrackingEntry}'s that
	 *                      will added.
	 */
	public Tracking(final Tracking parentTracker) {
		this.parent = parentTracker;
	}

	/**
	 * Adds a {@link TrackingEntry} object and pass it if it exists to the parent
	 * {@link Tracking} object.
	 * 
	 * @param entry that will be added to the {@link Tracking}
	 */
	public void add(final TrackingEntry entry) {
		if (parent != null) {
			parent.add(entry);
		}
		entries.addFirst(entry);
	}

	public Tracking getParent() {
		return parent;
	}

	/**
	 * Return all {@link TrackingEntry}'s in a formated, similar to stacktrace,
	 * String.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (TrackingEntry trackingEntry : entries) {
			sb.append("\n").append(trackingEntry);
		}

		return sb.toString();
	}

	/**
	 * Find the {@link StackTraceElement} element, that represents the 'caller' of
	 * one of the passed parameter {@link Class} objects. This can be used to enrich
	 * data, in particular for exceptions.
	 * 
	 * @param classes that represents the classes, that the 'caller' calls directly
	 * @return the {@link StackTraceElement} that represents the 'caller' element,
	 *         else <code>null</code>
	 */
	public static StackTraceElement findCallerOf(final Class<?>... classes) {
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
