package se.jbee.ipo.http;

import static se.jbee.ipo.Arg.arg;
import static se.jbee.ipo.Args.args;
import static se.jbee.ipo.Input.input;
import static se.jbee.ipo.Name.named;
import static se.jbee.ipo.Program.program;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import se.jbee.ipo.Env;
import se.jbee.ipo.Input;
import se.jbee.ipo.Output;
import se.jbee.ipo.Process;
import se.jbee.ipo.Program;
import se.jbee.ipo.Setup;
import se.jbee.ipo.Spec;
import se.jbee.ipo.io.Print;
import se.jbee.ipo.process.Decode;
import se.jbee.ipo.process.Describe;
import se.jbee.ipo.process.Overview;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import de.jbee.lang.List;

public class Server {

	static final Program program = program( named( "test" ), Env.EMPTY,
			List.with.<Setup> noElements(), new Print(), new Overview(), new Describe(),
			new Decode() );

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
