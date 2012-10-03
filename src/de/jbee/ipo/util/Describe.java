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
import de.jbee.ipo.Attr;
import de.jbee.ipo.Input;
import de.jbee.ipo.Name;
import de.jbee.ipo.Output;
import de.jbee.ipo.Param;
import de.jbee.ipo.Process;
import de.jbee.ipo.Prototype;
import de.jbee.ipo.Record;
import de.jbee.ipo.Schema;
import de.jbee.ipo.Series;
import de.jbee.ipo.Spec;
import de.jbee.lang.List;

public class Describe
		implements Process {

	private static final Schema PARAMETER = schema( named( "parameter" ), attr( "name",
			Prototype.NAME ), attr( "type", Prototype.NAME ) );

	private static final Schema ATTR = schema( named( "attribute" ),
			attr( "name", Prototype.NAME ), attr( "type", Prototype.NAME ), attr( "min-occur",
					Prototype.NUMBER ), attr( "max-occur", Prototype.NUMBER ) );

	public static final Param NAME = param( attr( "name", Prototype.NAME ) );

	private static final Spec SPEC = spec( "describe", "/describe/", NAME, PARAMETER, ATTR );

	@Override
	public Output process( Input input ) {
		return descripe( described( input ), input );
	}

	private Process described( Input input ) {
		return input.program.processNamed( input.args.valueOf( NAME ).as( Name.class ) );
	}

	private Output descripe( Process process, Input input ) {
		Spec spec = process.specification();
		List<Series> series = List.with.noElements();
		series = series.append( describeParameters( spec ) );
		series = series.concat( describeSeriesSchema( spec ) );
		return output( input, data( named( "description" ), series ) );
	}

	private List<Series> describeSeriesSchema( Spec spec ) {
		List<Series> series = List.with.noElements();
		for ( int i = 0; i < spec.series.length(); i++ ) {
			Schema schema = spec.series.at( i );
			List<Record> records = List.with.noElements();
			for ( int j = 0; j < schema.attributes.length(); j++ ) {
				Attr attr = schema.attributes.at( i );
				records = records.append( record( ATTR, attr.name, attr.proto.name, attr.minOccur,
						attr.maxOccur ) );
			}
			series = series.append( series( schema.name, ATTR, records ) );
		}
		return series;
	}

	private Series describeParameters( Spec spec ) {
		List<Record> records = List.with.noElements();
		for ( int i = 0; i < spec.params.length(); i++ ) {
			Param p = spec.params.at( i );
			records = records.append( record( PARAMETER, sequence( p.name(), p.attr.proto.name ) ) );
		}
		return series( named( "parameters" ), PARAMETER, records );
	}

	@Override
	public Spec specification() {
		return SPEC;
	}

}
