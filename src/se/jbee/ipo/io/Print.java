package se.jbee.ipo.io;

import static se.jbee.ipo.Attr.attr;
import static se.jbee.ipo.Data.data;
import static se.jbee.ipo.Name.named;
import static se.jbee.ipo.Output.output;
import static se.jbee.ipo.Param.param;
import static se.jbee.ipo.Prototype.proto;
import static se.jbee.ipo.Spec.spec;

import java.io.PrintStream;

import se.jbee.ipo.Input;
import se.jbee.ipo.Name;
import se.jbee.ipo.Output;
import se.jbee.ipo.Param;
import se.jbee.ipo.Process;
import se.jbee.ipo.Prototype;
import se.jbee.ipo.Spec;


public class Print
		implements Process {

	private static final Prototype PRINT_STREAM = proto( named( "print-stream" ), PrintStream.class );

	public static final Name PROCESS_NAME = named( "print-html" );

	public static final Param PRINTED = param( attr( "printed", Prototype.OUTPUT ) ).required();
	public static final Param STREAM = param( attr( "stream", PRINT_STREAM ) ).optional();

	private static final Spec SPEC = spec( PROCESS_NAME, "/print/html/", PRINTED, STREAM );

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
