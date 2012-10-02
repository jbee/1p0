package de.jbee.ipo;

import de.jbee.lang.List;

public final class Attr
		implements Named, Attributed {

	public static List<Attr> attributes( Attributed... attributes ) {
		Attr[] attrs = new Attr[attributes.length];
		for ( int i = 0; i < attrs.length; i++ ) {
			attrs[i] = attributes[i].attribute();
		}
		return List.with.elements( attrs );
	}

	public static Attr attr( String name, Prototype form ) {
		return attr( Name.named( name ), form );
	}

	public static Attr attr( Name name, Prototype form ) {
		return new Attr( name, form, 0, 1 );
	}

	public final Name name;
	public final Prototype proto;
	public final int minOccur;
	public final int maxOccur;

	private Attr( Name name, Prototype proto, int minOccur, int maxOccur ) {
		super();
		this.name = name;
		this.proto = proto;
		this.minOccur = minOccur;
		this.maxOccur = maxOccur;
	}

	public Attr list() {
		return new Attr( name, proto, minOccur, Integer.MAX_VALUE );
	}

	public Attr mandatory() {
		return isMandatory()
			? this
			: new Attr( name, proto, 1, maxOccur );
	}

	public boolean isList() {
		return maxOccur > 1;
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
		return minOccur > 0;
	}

	public Attr named( Name name ) {
		return name.equalTo( this.name )
			? this
			: new Attr( name, proto, minOccur, maxOccur );
	}
}
