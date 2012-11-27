package se.jbee.ipo;

import static se.jbee.ipo.Name.named;

public final class Prototype
		implements Named {

	public static final Prototype LOCATION = proto( named( "location" ), Location.class );
	public static final Prototype NAME = proto( named( "name" ), Name.class );
	public static final Prototype NUMBER = proto( named( "number" ), Integer.class );
	public static final Prototype OUTPUT = proto( named( Output.class ), Output.class );
	public static final Prototype ARGUMENT = proto( named( Arg.class ), Arg.class );

	public static Prototype proto( Name name, Class<?> type ) {
		return new Prototype( name, type, null, null, null );
	}

	public final Name name;
	/**
	 * The internal type of values resulting from the {@link #parser}.
	 */
	public final Class<?> type;
	public final Options options;
	public final Parser parser;
	public final Format format;

	private Prototype( Name name, Class<?> type, Options options, Parser parser, Format format ) {
		super();
		this.name = name;
		this.type = type;
		this.options = options;
		this.parser = parser;
		this.format = format;
	}

	@Override
	public Name name() {
		return name;
	}
}
