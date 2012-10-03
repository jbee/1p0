package de.jbee.ipo;

import static de.jbee.ipo.Program.program;

public final class Input
		implements Named {

	public static Input input( Process process, Args args ) {
		return new Input( program( process.specification().name(), process ),
				process.specification(), args );
	}

	public static Input input( Program program, Spec spec, Args args ) {
		return new Input( program, spec, args );
	}

	public final Program program;
	public final Spec spec;
	public final Args args;

	private Input( Program program, Spec spec, Args args ) {
		super();
		this.program = program;
		this.spec = spec;
		this.args = args;
		//TODO check that all required parameters got args so that no input can be constructed that is invalid
	}

	@Override
	public Name name() {
		return spec.name;
	}
}
