package se.jbee.ipo.util;

import se.jbee.ipo.Equals;

public class Array {

	public static <T extends Equals<T>> int indexOfEqual( T e, T[] list ) {
		for ( int i = 0; i < list.length; i++ ) {
			if ( list[i].equalTo( e ) ) {
				return i;
			}
		}
		return -1;
	}
}
