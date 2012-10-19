package de.jbee.ipo;

import de.jbee.lang.Array;
import de.jbee.lang.List;

public final class Program
		implements Named {

	public static Program program( Name name, Process... processes ) {
		return program( name, Array.sequence( processes ) );
	}

	public static Program program( Name name, List<Process> processes ) {
		return new Program( name, processes );
	}

	public final Name name;
	public final List<Process> processes;

	private Program( Name name, List<Process> processes ) {
		super();
		this.name = name;
		this.processes = processes;
	}

	public Process processNamed( Name name ) {
		for ( int i = 0; i < processes.length(); i++ ) {
			Process p = processes.at( i );
			if ( p.specification().name.equalTo( name ) ) {
				return p;
			}
		}
		throw new IllegalArgumentException( "No such process: " + name );
	}

	@Override
	public Name name() {
		return name;
	}
}
