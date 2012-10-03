package de.jbee.ipo;

import de.jbee.lang.Map;

public final class Program
		implements Named {

	public static Program program( Name name, Map<Process> processes ) {
		return new Program( name, processes );
	}

	public final Name name;
	public final Map<Process> processes;

	private Program( Name name, Map<Process> processes ) {
		super();
		this.name = name;
		this.processes = processes;
	}

	public Process processNamed( Name name ) {
		for ( int i = 0; i < processes.length(); i++ ) {
			Process p = processes.at( i ).value();
			if ( p.specification().name.equalTo( name ) ) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Name name() {
		return name;
	}
}
