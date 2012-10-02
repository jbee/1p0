package de.jbee.ipo;

import static de.jbee.lang.seq.IndexFor.exists;
import de.jbee.lang.Equal;
import de.jbee.lang.Set;

public class Args {

	// how to model processes that takes a series of string key-value pairs and transforms them to args of known parameters
	// e.g. a variable sized matrix of input fields of some type should be just one parameter while there are many http request parameters 

	public Schema schema; // reflects the args attributes

	/**
	 * Arguments themselves have no particular order. The sequence of argumentation should never
	 * play a role. If sequence matters that {@link Arg}'value itself should be a sequence.
	 */
	public Set<Arg> values;

	public <T> Value valueOf( Param param ) {
		int idx = Set.indexFor.elemBy( param, Equal.by( Name.ORDER ) ).in( values );
		if ( !exists( idx ) ) {
			return null;
		}
		return values.at( idx ).value;
	}

}
