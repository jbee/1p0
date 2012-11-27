package se.jbee.ipo;

import de.jbee.lang.List;

public final class Schema
		implements Named {

	public static Schema schema( Name name, Attributed... attributes ) {
		return schema( name, Attr.attributes( attributes ) );
	}

	public static Schema schema( Name name, Attr... attributes ) {
		return schema( name, List.with.elements( attributes ) );
	}

	public static Schema schema( Name name, List<Attr> attributes ) {
		return new Schema( name, attributes );
	}

	public final Name name;
	public final List<Attr> attributes;

	private Schema( Name name, List<Attr> attributes ) {
		super();
		this.name = name;
		this.attributes = attributes;
	}

	@Override
	public Name name() {
		return name;
	}

	/**
	 * @return If this contains all {@link Attr}s of the other {@link Schema} independent from their
	 *         particular order.
	 */
	public boolean conformTo( Schema other ) {
		//FIXME
		return true;
	}

	@Override
	public String toString() {
		return name + " " + attributes.toString();
	}
}
