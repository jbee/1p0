package se.jbee.ipo;

public final class Cardinality {

	public static final Cardinality ONE = new Cardinality( 0, 1 );

	public final int minOccur;
	public final int maxOccur;

	private Cardinality( int minOccur, int maxOccur ) {
		super();
		this.minOccur = minOccur;
		this.maxOccur = maxOccur;
	}

	public Cardinality many() {
		return new Cardinality( minOccur, Integer.MAX_VALUE );
	}

	public Cardinality some() {
		return new Cardinality( Math.max( 1, minOccur ), maxOccur );
	}

	public boolean isMany() {
		return maxOccur > 1;
	}

	public boolean isSome() {
		return minOccur > 0;
	}
}
