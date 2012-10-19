package de.jbee.ipo.io;

import java.io.PrintStream;

import de.jbee.ipo.Data;
import de.jbee.ipo.Name;
import de.jbee.ipo.Output;
import de.jbee.ipo.Record;
import de.jbee.ipo.Schema;
import de.jbee.ipo.Series;
import de.jbee.ipo.Value;
import de.jbee.ipo.http.IO;

public class HtmlPrinter
		extends Printer {

	private static final IO.Resource CSS = IO.resource( HtmlPrinter.class, "default.css" );

	private final PrintStream out;

	public HtmlPrinter( PrintStream out ) {
		super();
		this.out = out;
	}

	@Override
	public void print( Output output ) {
		out.append( "<html><head>" );
		out.append( "<style>" );
		CSS.print( out );
		out.append( "</style></head><body><table class='content'><tr><td valign='top'>" );
		out.append( "<h1>" + output.input.program.name + "</h1>" );
		out.append( "<h2>" + output.data.name + "</h2><hr/>" );
		printDataIn( output );
		out.append( "</td></tr></table></html>" );
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
		out.append( "<h4>" + series.name + "</h4>" );
		out.append( "<table class='series'>" );
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
