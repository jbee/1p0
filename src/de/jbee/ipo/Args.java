package de.jbee.ipo;

import static de.jbee.ipo.Attr.attributes;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Schema.schema;
import static de.jbee.lang.seq.IndexFor.exists;
import de.jbee.lang.Array;
import de.jbee.lang.Equal;
import de.jbee.lang.Set;

public final class Args {

	public static Args args( Arg... args ) {
		return new Args( Set.with.elements( Name.ORDER, Array.sequence( args ) ) );
	}

	// how to model processes that takes a series of string key-value pairs and transforms them to args of known parameters
	// e.g. a variable sized matrix of input fields of some type should be just one parameter while there are many http request parameters 

	public final Schema schema; // reflects the args attributes

	/**
	 * Arguments themselves have no particular order. The sequence of argumentation should never
	 * play a role. If sequence matters that {@link Arg}'value itself should be a sequence.
	 */
	public final Set<Arg> values;

	private Args( Set<Arg> values ) {
		super();
		this.schema = schema( named( "args" ), attributes( values ) );
		this.values = values;
	}

	public <T> Value valueOf( Param param ) {
		int idx = Set.indexFor.elemBy( param, Equal.by( Name.ORDER ) ).in( values );
		if ( !exists( idx ) ) {
			return null;
		}
		return values.at( idx ).value;
	}

}
