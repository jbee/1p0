package se.jbee.ipo;

import static de.jbee.lang.seq.IndexFor.exists;
import static se.jbee.ipo.Attr.attributes;
import static se.jbee.ipo.Name.named;
import static se.jbee.ipo.Schema.schema;
import de.jbee.lang.Array;
import de.jbee.lang.Equal;
import de.jbee.lang.List;
import de.jbee.lang.Set;

public final class Args {

	public static Args args( List<Arg> args ) {
		return new Args( Set.with.elements( Name.ORDER, args ) );
	}

	public static Args args( Arg... args ) {
		return args( Array.sequence( args ) );
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
			return Value.value( param.attr, null );
		}
		return values.at( idx ).value;
	}

	@Override
	public String toString() {
		return values.toString();
	}
}
