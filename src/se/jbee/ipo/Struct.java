package se.jbee.ipo;

public final class Struct {

	public static Struct struct( Schema schema ) {
		return struct( schema, Cardinality.ONE );
	}

	public static Struct struct( Schema schema, Cardinality card ) {
		return new Struct( schema, card );
	}

	public final Schema schema;
	public final Cardinality card;

	private Struct( Schema schema, Cardinality card ) {
		super();
		this.schema = schema;
		this.card = card;
	}

}
