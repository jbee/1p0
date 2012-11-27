package se.jbee.ipo;

import de.jbee.lang.Array;
import de.jbee.lang.List;

public final class Attr
		implements Named, Attributed {

	public static final Attr NAME = attr( "name", Prototype.NAME );

	public static List<Attr> attributes( Attributed... attributes ) {
		return attributes( Array.sequence( attributes ) );
	}

	public static List<Attr> attributes( List<? extends Attributed> attributes ) {
		Attr[] attrs = new Attr[attributes.length()];
		for ( int i = 0; i < attrs.length; i++ ) {
			attrs[i] = attributes.at( i ).attribute();
		}
		return List.with.elements( attrs );
	}

	public static Attr attr( String name, Prototype form ) {
		return attr( Name.named( name ), form );
	}

	public static Attr attr( Name name, Prototype form ) {
		return new Attr( name, form, Objective.DATA, Cardinality.ONE );
	}

	public final Ns ns = Ns.GLOBAL;
	public final Name name;
	public final Prototype proto;
	public final Objective objective;
	public final Cardinality card;

	private Attr( Name name, Prototype proto, Objective objective, Cardinality card ) {
		super();
		this.name = name;
		this.proto = proto;
		this.objective = objective;
		this.card = card;
	}

	public Attr list() {
		return new Attr( name, proto, objective, card.many() );
	}

	public Attr mandatory() {
		return isMandatory()
			? this
			: new Attr( name, proto, objective, card.some() );
	}

	/**
	 * same as {@link #mandatory()} but as a result this is more readable. It means it is guaranteed
	 * to occur.
	 */
	public Attr nonnull() {
		return mandatory();
	}

	public boolean isList() {
		return card.isMany();
	}

	@Override
	public Name name() {
		return name;
	}

	@Override
	public Attr attribute() {
		return this;
	}

	public boolean isMandatory() {
		return card.isSome();
	}

	public Attr named( Name name ) {
		return name.equalTo( this.name )
			? this
			: new Attr( name, proto, objective, card );
	}

	@Override
	public String toString() {
		return name + ":" + proto.name;
	}
}
