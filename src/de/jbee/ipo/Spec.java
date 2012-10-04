package de.jbee.ipo;

import static de.jbee.ipo.Location.located;
import static de.jbee.ipo.Name.named;
import de.jbee.lang.Array;
import de.jbee.lang.List;

public final class Spec
		implements Named {

	public static Spec spec( String name, String location, Param... params ) {
		return spec( name, location, Array.sequence( params ) );
	}

	public static Spec spec( String name, String location, Param param, Schema... series ) {
		return spec( name, location, Array.sequence( param ), Array.sequence( series ) );
	}

	public static Spec spec( String name, String location, List<Param> params, Schema... series ) {
		return spec( name, location, params, Array.sequence( series ) );
	}

	public static Spec spec( String name, String location, List<Param> params, List<Schema> series ) {
		return spec( named( name ), located( location ), params, series );
	}

	public static Spec spec( Name name, Location location, List<Param> params, List<Schema> series ) {
		return new Spec( name, location, params, series );
	}

	public final Name name;
	public final Location location;
	public final List<Param> params; // what goes in
	public final List<Schema> series; // what comes out --- FIXME sometimes a schema occurs more than once but we do not know how often

	private Spec( Name name, Location location, List<Param> params, List<Schema> series ) {
		super();
		this.name = name;
		this.location = location;
		this.params = params;
		this.series = series;
	}

	@Override
	public Name name() {
		return name;
	}
}
