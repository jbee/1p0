package de.jbee.ipo.util;

import static de.jbee.ipo.Attr.attr;
import static de.jbee.ipo.Data.data;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Output.output;
import static de.jbee.ipo.Param.param;
import static de.jbee.ipo.Record.record;
import static de.jbee.ipo.Schema.schema;
import static de.jbee.ipo.Series.series;
import static de.jbee.ipo.Spec.spec;
import static de.jbee.lang.Array.sequence;
import de.jbee.ipo.Input;
import de.jbee.ipo.Name;
import de.jbee.ipo.Output;
import de.jbee.ipo.Param;
import de.jbee.ipo.Process;
import de.jbee.ipo.Prototype;
import de.jbee.ipo.Record;
import de.jbee.ipo.Schema;
import de.jbee.ipo.Spec;
import de.jbee.lang.List;

public class Describe
		implements Process {

	private static final Schema PARAMETERS = schema( named( Describe.class ), attr( "name",
			Prototype.NAME ), attr( "type", Prototype.NAME ) );

	public static final Param NAME = param( attr( "name", Prototype.NAME ) );

	private static final Spec SPEC = spec( "describe", "/describe/", NAME, PARAMETERS );

	@Override
	public Output process( Input input ) {
		return descripe( described( input ), input );
	}

	private Process described( Input input ) {
		return input.program.processNamed( input.args.valueOf( NAME ).as( Name.class ) );
	}

	private Output descripe( Process process, Input input ) {
		Spec spec = process.specification();
		List<Record> records = List.with.noElements();
		for ( int i = 0; i < spec.params.length(); i++ ) {
			Param p = spec.params.at( i );
			records = records.append( record( PARAMETERS, sequence( p.name(), p.attr.proto.name ) ) );
		}
		return output( input, data( named( "description" ), series( PARAMETERS, records ) ) );
	}

	@Override
	public Spec specification() {
		return SPEC;
	}

}
