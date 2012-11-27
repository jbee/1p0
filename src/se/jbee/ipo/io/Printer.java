package se.jbee.ipo.io;

import se.jbee.ipo.Data;
import se.jbee.ipo.Output;
import se.jbee.ipo.Record;
import se.jbee.ipo.Schema;
import se.jbee.ipo.Series;
import se.jbee.ipo.Value;

public abstract class Printer {

	public void printDataIn( Output output ) {
		print( output.data );
	}

	public void printSeriesIn( Data data ) {
		for ( int i = 0; i < data.series.length(); i++ ) {
			print( data.series.at( i ) );
		}
	}

	public void printRecordsIn( Series series ) {
		for ( int i = 0; i < series.records.length(); i++ ) {
			print( series.records.at( i ) );
		}
	}

	public void printValuesIn( Record record ) {
		for ( int i = 0; i < record.schema.attributes.length(); i++ ) {
			print( Value.value( record.schema.attributes.at( i ), record.values.at( i ) ) );
		}
	}

	public abstract void print( Output output );

	public abstract void print( Data data );

	public abstract void print( Series series );

	public abstract void print( Schema schema );

	public abstract void print( Record record );

	public abstract void print( Value value );
}
