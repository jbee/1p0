package de.jbee.ipo.http;

import static de.jbee.ipo.Arg.arg;
import static de.jbee.ipo.Args.args;
import static de.jbee.ipo.Input.input;
import static de.jbee.ipo.Name.named;
import static de.jbee.ipo.Program.program;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import de.jbee.ipo.Input;
import de.jbee.ipo.Output;
import de.jbee.ipo.Process;
import de.jbee.ipo.Program;
import de.jbee.ipo.io.Print;
import de.jbee.ipo.util.Describe;
import de.jbee.ipo.util.Overview;

public class Server {

	static final Program program = program( named( "test" ), new Print(), new Overview(),
			new Describe() );

	public static void main( String[] args )
			throws Exception {
		InetSocketAddress address = new InetSocketAddress( 8000 );
		HttpServer server = HttpServer.create( address, 0 );
		server.createContext( "/", new HttpHandler() {

			@Override
			public void handle( HttpExchange ex )
					throws IOException {
				try {
					respond( ex );
				} catch ( RuntimeException e ) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					e.printStackTrace( new PrintStream( out ) );
					ex.sendResponseHeaders( HttpURLConnection.HTTP_INTERNAL_ERROR, out.size() );
					ex.getResponseBody().write( out.toByteArray() );
					ex.close();
				}
			}

			private void respond( HttpExchange ex )
					throws IOException {
				ByteArrayOutputStream out = new ByteArrayOutputStream();

				Process used = program.processNamed( named( ex.getRequestURI().getPath().toString().replace(
						"/", "" ) ) );

				Output output = used.process( input( program, used.specification(), args() ) );

				Process print = program.processNamed( Print.PROCESS_NAME );
				print.process( Input.input( program, print.specification(), args( arg(
						Print.PRINTED, output ), arg( Print.STREAM, new PrintStream( out ) ) ) ) );
				ex.sendResponseHeaders( HttpURLConnection.HTTP_OK, out.size() );
				ex.getResponseBody().write( out.toByteArray() );
				ex.close();
			}
		} );
		server.start();
		System.out.println( "started at " + address );
	}
}
