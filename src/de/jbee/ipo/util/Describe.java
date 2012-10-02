package de.jbee.ipo.util;

import static de.jbee.ipo.Attr.attr;
import static de.jbee.ipo.Data.data;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Output.output;
import static de.jbee.ipo.Param.param;
import static de.jbee.ipo.Schema.schema;
import static de.jbee.ipo.Series.series;
import de.jbee.ipo.Input;
import de.jbee.ipo.Location;
import de.jbee.ipo.Output;
import de.jbee.ipo.Param;
import de.jbee.ipo.Process;
import de.jbee.ipo.Prototype;
import de.jbee.ipo.Schema;
import de.jbee.ipo.Spec;
import de.jbee.lang.List;

public class Describe
		implements Process {

	private static final Schema PARAMETERS = schema( named( Describe.class ), attr( "name",
			Prototype.NAME ), attr( "type", Prototype.NAME ) );

	private static final Param LOCATION = param( attr( "location", Prototype.LOCATION ) );

	private static final Spec SPEC = Spec.spec( "describe", "/describe/", LOCATION, PARAMETERS );

	@Override
	public Output process( Input input ) {
		return descripe( described( input ), input );
	}

	private Process described( Input input ) {
		return input.program.processLocatedAt( input.args.valueOf( LOCATION ).as( Location.class ) );
	}

	private Output descripe( Process process, Input input ) {
		List<List<Object>> values = List.with.noElements();
		Spec spec = process.specification();
		for ( int i = 0; i < spec.params.length(); i++ ) {
			Param p = spec.params.at( i );
			values = values.append( List.with.<Object> elements( p.name(), p.attr.proto.name ) );
		}
		return output( input, data( named( "description" ), series( PARAMETERS, values ) ) );
	}

	@Override
	public Spec specification() {
		return SPEC;
	}

}
