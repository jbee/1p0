package de.jbee.ipo;

import de.jbee.lang.List;

public final class Series
		implements Named {

	public static Series series( Name name, Schema schema, List<Record> records ) {
		return new Series( name, schema, records );
	}

	public final Name name;
	public final Schema schema;
	public final List<Record> records;

	private Series( Name name, Schema schema, List<Record> records ) {
		super();
		this.name = name;
		this.schema = schema;
		this.records = records;
	}

	@Override
	public Name name() {
		return name;
	}

}