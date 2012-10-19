package de.jbee.ipo.util;

import static de.jbee.ipo.Arg.arg;
import static de.jbee.ipo.Attr.attr;
import static de.jbee.ipo.Data.data;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Output.output;
import static de.jbee.ipo.Param.param;
import static de.jbee.ipo.Prototype.proto;
import static de.jbee.ipo.Record.record;
import static de.jbee.ipo.Schema.schema;
import static de.jbee.ipo.Series.series;
import static de.jbee.ipo.Spec.spec;
import static de.jbee.ipo.Struct.struct;
import static de.jbee.lang.Array.sequence;
import static de.jbee.lang.Order.typeaware;
import static de.jbee.lang.seq.Sequences.key;
import de.jbee.ipo.Arg;
import de.jbee.ipo.Args;
import de.jbee.ipo.Attr;
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
import de.jbee.lang.Map;
import de.jbee.lang.Multimap;
import de.jbee.lang.Order;

public class Decode
		implements Process {

	public static final Name SERVICE_NAME = named( "decode" );

	public static final Param QUERY = param( attr( "query", proto( named( "query" ), String.class ) ) );
	public static final Param NAME = param( Attr.NAME ).required();

	private static final Schema ARGS = schema( named( Arg.class ), attr( "arg", Prototype.ARGUMENT ) );

	private static final Spec SPEC = spec( SERVICE_NAME, "/decode/", sequence( NAME, QUERY ),
			struct( ARGS ) );

	public static Args args( Output decoded ) {
		List<Record> records = decoded.data.series.at( 0 ).records;
		List<Arg> args = List.with.noElements();
		for ( int i = 0; i < records.length(); i++ ) {
			args = args.append( (Arg) records.at( i ).values.at( 0 ) );
		}
		return Args.args( args );
	}

	@Override
	public Output process( Input input ) {
		String query = input.args.valueOf( QUERY ).as( String.class );
		Spec spec = input.program.processNamed( input.args.valueOf( NAME ).as( Name.class ) ).specification();
		return output( input, data( "valid-args", series( named( Args.class ), ARGS, args( query,
				spec ) ) ) );
	}

	private List<Record> args( String query, Spec spec ) {
		String[] nvs = query.split( "&" );
		Multimap<String> decoded = Map.with.noEntries( typeaware( Order.alphabetical, String.class ) );
		for ( String nv : nvs ) {
			String[] kv = nv.split( "=" );
			decoded = decoded.insert( key( kv[0] ), kv[1] );
		}
		List<Record> res = List.with.noElements();
		for ( int i = 0; i < spec.params.length(); i++ ) {
			Param param = spec.params.at( i );
			String value = decoded.valuesAt( decoded.indexFor( key( param.name() ) ) ).at( 0 );
			Object decodedValue = named( value ); //TODO use attr.parser
			res = res.append( record( ARGS, arg( param, decodedValue ), value ) );
		}
		return res;
	}

	@Override
	public Spec specification() {
		return SPEC;
	}

}
