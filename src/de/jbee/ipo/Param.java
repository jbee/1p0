package de.jbee.ipo;

public final class Param
		implements Named, Attributed {

	public static Param param( Attributed attributed ) {
		return param( attributed.attribute() );
	}

	public static Param param( Attr attr ) {
		return new Param( attr, false );
	}

	public final Attr attr;
	public final boolean required;

	private Param( Attr attr, boolean required ) {
		super();
		this.attr = attr;
		this.required = required;
	}

	public Param required() {
		return required
			? this
			: new Param( attr, required );
	}

	public Param optional() {
		return !required
			? this
			: new Param( attr, required );
	}

	public Param named( Name name ) {
		return new Param( attr.named( name ), required );
	}

	@Override
	public Name name() {
		return attr.name;
	}

	@Override
	public Attr attribute() {
		return attr;
	}
}
