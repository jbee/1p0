package de.jbee.ipo;

import static de.jbee.ipo.Name.named;

public final class Prototype
		implements Named {

	public static final Prototype LOCATION = proto( named( "location" ), Location.class );
	public static final Prototype NAME = proto( named( "name" ), Name.class );
	public static final Prototype NUMBER = proto( named( "number" ), Integer.class );

	public static Prototype proto( Name name, Class<?> type ) {
		return new Prototype( name, type, Objective.DATA, null, null, null );
	}

	public final Name name;
	/**
	 * The internal type of values resulting from the {@link #parser}.
	 */
	public final Class<?> type;
	public final Objective objective;
	public final Options options;
	public final Parser parser;
	public final Format format;

	private Prototype( Name name, Class<?> type, Objective objective, Options options,
			Parser parser, Format format ) {
		super();
		this.name = name;
		this.type = type;
		this.objective = objective;
		this.options = options;
		this.parser = parser;
		this.format = format;
	}

	@Override
	public Name name() {
		return name;
	}
}
