package de.jbee.ipo.io;

import java.io.PrintStream;

import de.jbee.ipo.Data;
import de.jbee.ipo.Name;
import de.jbee.ipo.Output;
import de.jbee.ipo.Record;
import de.jbee.ipo.Schema;
import de.jbee.ipo.Series;
import de.jbee.ipo.Value;

public class HtmlPrinter
		extends Printer {

	private final PrintStream out;

	public HtmlPrinter( PrintStream out ) {
		super();
		this.out = out;
	}

	@Override
	public void print( Output output ) {
		out.append( "<html><head></head><body>" );
		printDataIn( output );
		out.append( "</html>" );
	}

	@Override
	public void print( Data data ) {
		out.append( "<h3>" );
		print( data.name );
		out.append( "</h3>" );
		printSeriesIn( data );
	}

	@Override
	public void print( Series series ) {
		out.append( "<table>" );
		print( series.schema );
		printRecordsIn( series );
		out.append( "</table>" );
	}

	@Override
	public void print( Schema schema ) {
		out.append( "<tr><th colspan='" + schema.attributes.length() + "'>" + schema.name
				+ "</th></tr>" );
		out.append( "<tr>" );
		for ( int i = 0; i < schema.attributes.length(); i++ ) {
			out.append( "<th>" );
			print( schema.attributes.at( i ).name );
			out.append( "</th>" );
		}
		out.append( "</tr>" );
	}

	@Override
	public void print( Record record ) {
		out.append( "<tr>" );
		printValuesIn( record );
		out.append( "</tr>" );
	}

	@Override
	public void print( Value value ) {
		out.append( "<td>" );
		out.append( String.valueOf( value.value ) );
		out.append( "</td>" );
	}

	private void print( Name name ) {
		out.append( name );
	}

}
