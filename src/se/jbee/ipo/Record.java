package se.jbee.ipo;

import de.jbee.lang.Array;
import de.jbee.lang.List;

public final class Record {

	public static Record record( Schema schema, Object... values ) {
		return record( schema, Array.sequence( values ) );
	}

	public static Record record( Schema schema, List<?> values ) {
		return new Record( schema, values );
	}

	public final Schema schema;
	public final List<?> values;

	private Record( Schema schema, List<?> values ) {
		super();
		this.schema = schema;
		this.values = values;
	}

}
