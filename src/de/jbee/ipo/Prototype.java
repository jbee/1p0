package de.jbee.ipo;

public final class Prototype
		implements Named {

	public static final Prototype LOCATION = null;
	public static final Prototype NAME = null;
	public static final Prototype TYPE = null;

	public Name name;
	/**
	 * The internal type of values resulting from the {@link #parser}.
	 */
	public Class<?> type;
	public Kind kind;
	public Options options;
	public Parser parser;
	public Pattern scheme;

	@Override
	public Name name() {
		return name;
	}
}
