package se.jbee.ipo.process;

import static se.jbee.ipo.Attr.attr;
import static se.jbee.ipo.Data.data;
import static se.jbee.ipo.Name.named;
import static se.jbee.ipo.Output.output;
import static se.jbee.ipo.Record.record;
import static se.jbee.ipo.Schema.schema;
import static se.jbee.ipo.Series.series;
import static se.jbee.ipo.Spec.spec;
import static se.jbee.ipo.Struct.struct;
import se.jbee.ipo.Input;
import se.jbee.ipo.Output;
import se.jbee.ipo.Param;
import se.jbee.ipo.Process;
import se.jbee.ipo.Prototype;
import se.jbee.ipo.Record;
import se.jbee.ipo.Schema;
import se.jbee.ipo.Series;
import se.jbee.ipo.Spec;
import de.jbee.lang.List;

/**
 * A grouped overview list containing all known {@link Process}es (reachable from {@link Input}).
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public class Overview
		implements Process {

	private static final Schema PROCESS = schema( named( "process" ),
			attr( "name", Prototype.NAME ), attr( "location", Prototype.LOCATION ) );

	private static final Spec SPEC = spec( named( "overview" ), "/",
			List.with.<Param> noElements(), struct( PROCESS ) );

	@Override
	public Output process( Input input ) {
		List<Record> records = List.with.noElements();
		List<Process> ps = input.program.processes;
		for ( int i = 0; i < ps.length(); i++ ) {
			Process p = ps.at( i );
			Spec spec = p.specification();
			records = records.append( record( PROCESS, spec.name, spec.location ) );
		}
		Series processes = series( named( "processes" ), PROCESS, records );
		return output( input, data( "overview", processes ) );
	}

	@Override
	public Spec specification() {
		return SPEC;
	}

}
