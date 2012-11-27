package se.jbee.ipo;

import static se.jbee.ipo.Location.located;
import de.jbee.lang.Array;
import de.jbee.lang.List;

public final class Spec
		implements Named {

	public static Spec spec( Name name, String location, Param... params ) {
		return spec( name, location, Array.sequence( params ) );
	}

	public static Spec spec( Name name, String location, Param param, Struct... series ) {
		return spec( name, located( location ), Array.sequence( param ), Array.sequence( series ) );
	}

	public static Spec spec( Name name, String location, List<Param> params, Struct... series ) {
		return spec( name, located( location ), params, Array.sequence( series ) );
	}

	public static Spec spec( Name name, Location location, List<Param> params, List<Struct> series ) {
		return new Spec( name, location, params, series );
	}

	public final Name name;
	public final Location location;
	public final List<Param> params; // what goes in
	public final List<Struct> series; // what comes out

	//TODO add something like a ProcessType

	private Spec( Name name, Location location, List<Param> params, List<Struct> series ) {
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
