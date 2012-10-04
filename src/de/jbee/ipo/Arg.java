package de.jbee.ipo;

public final class Arg
		implements Named, Attributed {

	public static Arg arg( Param param, Object value ) {
		return arg( param, value, null );
	}

	public static Arg arg( Param param, Object value, String external ) {
		return new Arg( param, value, external );
	}

	public final Param param;
	public final Value value;

	/**
	 * as entered (externally) - not parsed into the internal form.
	 */
	public final String external;

	private Arg( Param param, Object value, String plain ) {
		super();
		this.param = param;
		this.value = Value.value( param.attr, value );
		this.external = plain;
		checkValue( param, value );
	}

	public static void checkValue( Param param, Object value ) {
		if ( value == null ) {
			if ( !param.required ) {
				throw new IllegalArgumentException( param + " is not optional but was null!" );
			}
			return;
		}
		if ( value.getClass() != param.attr.proto.type ) {
			throw new IllegalArgumentException( "Argument " + param + " value has to be of type "
					+ param.attr.proto.type.getSimpleName() + " but was: "
					+ value.getClass().getCanonicalName() );
		}

	}

	@Override
	public Name name() {
		return param.name();
	}

	@Override
	public Attr attribute() {
		return param.attr;
	}
}
