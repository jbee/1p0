package de.jbee.ipo.util;

import static de.jbee.ipo.Attr.attr;
import static de.jbee.ipo.Data.data;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Output.output;
import static de.jbee.ipo.Record.record;
import static de.jbee.ipo.Schema.schema;
import static de.jbee.ipo.Series.series;
import static de.jbee.ipo.Spec.spec;
import de.jbee.ipo.Input;
import de.jbee.ipo.Output;
import de.jbee.ipo.Param;
import de.jbee.ipo.Process;
import de.jbee.ipo.Prototype;
import de.jbee.ipo.Record;
import de.jbee.ipo.Schema;
import de.jbee.ipo.Series;
import de.jbee.ipo.Spec;
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

	private static final Spec SPEC = spec( "overview", "/", List.with.<Param> noElements(), PROCESS );

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
