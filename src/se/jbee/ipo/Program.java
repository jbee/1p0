package se.jbee.ipo;

import de.jbee.lang.Array;
import de.jbee.lang.List;

public final class Program
		implements Named {

	public static Program program( Name name, Env env, List<Setup> setups, Process... processes ) {
		return program( name, env, setups, Array.sequence( processes ) );
	}

	public static Program program( Name name, Env env, List<Setup> setups, List<Process> processes ) {
		return new Program( name, env, setups, processes );
	}

	public final Name name;
	public final Env env;
	public final List<Setup> setups;
	public final List<Process> processes;

	private Program( Name name, Env env, List<Setup> setups, List<Process> processes ) {
		super();
		this.name = name;
		this.env = env;
		this.setups = setups;
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
