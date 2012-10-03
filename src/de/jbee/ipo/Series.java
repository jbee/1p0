package de.jbee.ipo;

import de.jbee.lang.List;

public final class Series
		implements Named {

	public static Series series( Schema schema, List<Record> records ) {
		return new Series( schema, records );
	}

	public final Schema schema;
	public final List<Record> records;

	private Series( Schema schema, List<Record> records ) {
		super();
		this.schema = schema;
		this.records = records;
	}

	@Override
	public Name name() {
		return schema.name;
	}

}