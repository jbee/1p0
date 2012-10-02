package de.jbee.ipo;

import de.jbee.lang.List;

public final class Data
		implements Named {

	public static Data data( Name name, Series... series ) {
		return new Data( name, List.with.elements( series ) );
	}

	public final Name name;
	public final List<Series> series;

	private Data( Name name, List<Series> series ) {
		super();
		this.name = name;
		this.series = series;
	}

	@Override
	public Name name() {
		return name;
	}

}
