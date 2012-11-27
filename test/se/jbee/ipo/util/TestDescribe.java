package se.jbee.ipo.util;

import static se.jbee.ipo.Arg.arg;
import static se.jbee.ipo.Args.args;
import static se.jbee.ipo.Input.input;
import static se.jbee.ipo.Name.named;

import org.junit.Test;

import se.jbee.ipo.Input;
import se.jbee.ipo.Process;
import se.jbee.ipo.io.HtmlPrinter;
import se.jbee.ipo.process.Describe;


public class TestDescribe {

	@Test
	public void thatRoundTripWorks() {
		Process describe = new Describe();
		Input in = input( describe, args( arg( Describe.NAME, named( "describe" ), "describe" ) ) );
		new HtmlPrinter( System.out ).print( describe.process( in ) );
	}
}
