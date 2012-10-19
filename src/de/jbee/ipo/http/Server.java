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
import de.jbee.ipo.Spec;
import de.jbee.ipo.io.Print;
import de.jbee.ipo.util.Decode;
import de.jbee.ipo.util.Describe;
import de.jbee.ipo.util.Overview;

public class Server {

	static final Program program = program( named( "test" ), new Print(), new Overview(),
			new Describe(), new Decode() );

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
					e.printStackTrace();
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					e.printStackTrace( new PrintStream( out ) );
					respond( ex, HttpURLConnection.HTTP_INTERNAL_ERROR, out );
				}
			}

			private void respond( HttpExchange ex, int code, ByteArrayOutputStream body )
					throws IOException {
				ex.sendResponseHeaders( code, body.size() );
				ex.getResponseBody().write( body.toByteArray() );
				body.close();
				ex.close();
			}

			private void respond( HttpExchange ex )
					throws IOException {
				ByteArrayOutputStream out = new ByteArrayOutputStream();

				String path = ex.getRequestURI().getPath();
				String query = ex.getRequestURI().getQuery();
				if ( "/favicon.ico".equals( path ) ) {
					respond( ex, HttpURLConnection.HTTP_NOT_FOUND, out );
					return;
				}
				Process used = program.processNamed( named( path.replace( "/", "" ) ) );

				Spec spec = used.specification();
				Process decode = program.processNamed( Decode.SERVICE_NAME );

				Output output = used.process( input( program, spec,
						Decode.args( decode.process( input( program, decode.specification(), args(
								arg( Decode.NAME, spec.name ), arg( Decode.QUERY, query ) ) ) ) ) ) );

				Process print = program.processNamed( Print.PROCESS_NAME );
				print.process( Input.input( program, print.specification(), args( arg(
						Print.PRINTED, output ), arg( Print.STREAM, new PrintStream( out ) ) ) ) );
				respond( ex, HttpURLConnection.HTTP_OK, out );
			}
		} );
		server.start();
		System.out.println( "started at " + address );
	}
}
