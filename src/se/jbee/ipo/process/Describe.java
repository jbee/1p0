package se.jbee.ipo.process;

import static de.jbee.lang.Array.sequence;
import static se.jbee.ipo.Attr.attr;
import static se.jbee.ipo.Data.data;
import static se.jbee.ipo.Name.named;
import static se.jbee.ipo.Output.output;
import static se.jbee.ipo.Param.param;
import static se.jbee.ipo.Record.record;
import static se.jbee.ipo.Schema.schema;
import static se.jbee.ipo.Series.series;
import static se.jbee.ipo.Spec.spec;
import static se.jbee.ipo.Struct.struct;
import se.jbee.ipo.Attr;
import se.jbee.ipo.Input;
import se.jbee.ipo.Name;
import se.jbee.ipo.Objective;
import se.jbee.ipo.Output;
import se.jbee.ipo.Param;
import se.jbee.ipo.Process;
import se.jbee.ipo.Prototype;
import se.jbee.ipo.Record;
import se.jbee.ipo.Schema;
import se.jbee.ipo.Series;
import se.jbee.ipo.Spec;
import de.jbee.lang.List;

public class Describe
		implements Process {

	private static final Schema PARAMETER = schema( named( "parameter" ), // 
			attr( "name", Prototype.NAME ), // 
			attr( "type", Prototype.NAME ) );

	private static final Schema ATTR = schema( named( "attribute" ), //
			attr( "name", Prototype.NAME ), // 
			attr( "type", Prototype.NAME ), //
			attr( "objective", Prototype.proto( named( Objective.class ), Objective.class ) ), //
			attr( "min-occur", Prototype.NUMBER ), // 
			attr( "max-occur", Prototype.NUMBER ) );

	public static final Param NAME = param( attr( "name", Prototype.NAME ) );

	private static final Spec SPEC = spec( named( "describe" ), "/describe/", NAME,
			struct( PARAMETER ), struct( ATTR ) );

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
		series = series.concat( describeStruct( spec ) );
		return output( input, data( named( "description" ), series ) );
	}

	private List<Series> describeStruct( Spec spec ) {
		List<Series> series = List.with.noElements();
		for ( int i = 0; i < spec.series.length(); i++ ) {
			Schema schema = spec.series.at( i ).schema;
			List<Record> records = List.with.noElements();
			for ( int j = 0; j < schema.attributes.length(); j++ ) {
				Attr attr = schema.attributes.at( j );
				records = records.append( record( ATTR, attr.name, attr.proto.name, attr.objective,
						attr.card.minOccur, attr.card.maxOccur ) );
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
