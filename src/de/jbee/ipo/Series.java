package de.jbee.ipo;

import de.jbee.lang.List;

public final class Series
		implements Named {

	public static Series series( Schema schema, List<List<Object>> values ) {
		return new Series( schema, values );
	}

	public final Schema schema;
	public final List<List<Object>> values;

	private Series( Schema schema, List<List<Object>> values ) {
		super();
		this.schema = schema;
		this.values = values;
	}

	@Override
	public Name name() {
		return schema.name;
	}
}
