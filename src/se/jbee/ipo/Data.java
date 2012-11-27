package se.jbee.ipo;

import de.jbee.lang.List;

public final class Data
		implements Named {

	public static Data data( String name, Series... series ) {
		return data( Name.named( name ), series );
	}

	public static Data data( Name name, Series... series ) {
		return data( name, List.with.elements( series ) );
	}

	public static Data data( Name name, List<Series> series ) {
		return new Data( name, series );
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
