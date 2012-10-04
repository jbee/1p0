package de.jbee.ipo.io;

import static de.jbee.ipo.Attr.attr;
import static de.jbee.ipo.Data.data;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Output.output;
import static de.jbee.ipo.Param.param;
import static de.jbee.ipo.Prototype.proto;
import static de.jbee.ipo.Spec.spec;

import java.io.PrintStream;

import de.jbee.ipo.Input;
import de.jbee.ipo.Name;
import de.jbee.ipo.Output;
import de.jbee.ipo.Param;
import de.jbee.ipo.Process;
import de.jbee.ipo.Prototype;
import de.jbee.ipo.Spec;

public class Print
		implements Process {

	private static final Prototype PRINT_STREAM = proto( named( "print-stream" ), PrintStream.class );

	public static final Name PROCESS_NAME = named( "print-html" );

	public static final Param PRINTED = param( attr( "printed", Prototype.OUTPUT ) ).required();
	public static final Param STREAM = param( attr( "stream", PRINT_STREAM ) ).optional();

	private static final Spec SPEC = spec( PROCESS_NAME.toString(), "/print/html/", PRINTED, STREAM );

	@Override
	public Spec specification() {
		return SPEC;
	}

	@Override
	public Output process( Input input ) {
		PrintStream out = input.args.valueOf( STREAM ).as( PrintStream.class );
		Output printed = input.args.valueOf( PRINTED ).as( Output.class );
		new HtmlPrinter( out ).print( printed );
		return output( input, data( "nil" ) );
	}
}
