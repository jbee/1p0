package de.jbee.ipo.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IO {

	public static Resource resource( Class<?> root, String filename ) {
		return new Resource( root, filename );
	}

	public static class Resource {

		private final Class<?> root;
		private final String filename;

		Resource( Class<?> root, String filename ) {
			super();
			this.root = root;
			this.filename = filename;
		}

		public void print( PrintStream out ) {
			try {
				printTo( out );
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}

		private void printTo( PrintStream out )
				throws IOException {
			InputStream in = root.getResourceAsStream( filename );
			Scanner s = new Scanner( in );
			while ( s.hasNext() ) {
				out.append( s.nextLine() );
			}
			s.close();
			in.close();
		}
	}
}
