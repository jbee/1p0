package de.jbee.ipo.util;

import static de.jbee.ipo.Arg.arg;
import static de.jbee.ipo.Args.args;
import static de.jbee.ipo.Input.input;
import static de.jbee.ipo.Name.named;

import org.junit.Test;

import de.jbee.ipo.Input;
import de.jbee.ipo.Process;
import de.jbee.ipo.io.HtmlPrinter;

public class TestDescribe {

	@Test
	public void thatRoundTripWorks() {
		Process describe = new Describe();
		Input in = input( describe, args( arg( Describe.NAME, named( "describe" ), "describe" ) ) );
		new HtmlPrinter( System.out ).print( describe.process( in ) );
	}
}
