package se.jbee.ipo;

/**
 * A dynamic form of enumeration. While the {@link Ns} corresponds to the enum class the
 * {@link Name} would be the enum constant.
 */
public final class Ident
		implements Equals<Ident> {

	public final Ns ns;
	public final Name name;

	private Ident( Ns ns, Name name ) {
		super();
		this.ns = ns;
		this.name = name;
	}

	@Override
	public boolean equals( Object obj ) {
		return obj instanceof Ident && equalTo( (Ident) obj );
	}

	@Override
	public boolean equalTo( Ident other ) {
		return ns.equalTo( other.ns ) && name.equalTo( other.name );
	}

	@Override
	public int hashCode() {
		return ns.hashCode() ^ name.hashCode();
	}

	@Override
	public String toString() {
		return ns.toString() + name;
	}
}
