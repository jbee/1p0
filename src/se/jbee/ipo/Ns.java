package se.jbee.ipo;

/**
 * A namespace.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 * 
 */
public final class Ns
		implements Equals<Ns> {

	public static final String SEPARATOR = ".";

	/**
	 * The default {@link Ns} where things end up when no special name-space is assigned to them.
	 */
	public static final Ns GLOBAL = new Ns( "", "" );

	private final String primary;
	private final String secondary;

	private Ns( String primary, String secondary ) {
		super();
		this.primary = primary.intern();
		this.secondary = secondary.intern();
	}

	@Override
	public boolean equals( Object obj ) {
		return obj instanceof Ns && equalTo( (Ns) obj );
	}

	@Override
	public boolean equalTo( Ns other ) {
		return other.primary == primary && other.secondary == secondary;
	}

	@Override
	public int hashCode() {
		return primary.hashCode() ^ secondary.hashCode();
	}

	@Override
	public String toString() {
		return secondary.isEmpty()
			? primary
			: primary + SEPARATOR + secondary;
	}

}
