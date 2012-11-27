package se.jbee.ipo;

import static se.jbee.ipo.Program.program;
import de.jbee.lang.List;

public final class Input
		implements Named {

	public static Input input( Process process, Args args ) {
		Program prog = program( process.specification().name(), Env.EMPTY,
				List.with.<Setup> noElements(), process );
		return new Input( prog, process.specification(), args, prog.env );
	}

	public static Input input( Program program, Spec spec, Args args ) {
		return new Input( program, spec, args, program.env );
	}

	public final Program program;
	public final Spec spec;
	public final Args args;
	public final Env env;

	private Input( Program program, Spec spec, Args args, Env env ) {
		super();
		this.program = program;
		this.spec = spec;
		this.args = args;
		this.env = env;
		//TODO check that all required parameters got args so that no input can be constructed that is invalid
	}

	public Input in( Env env ) {
		return new Input( program, spec, args, env );
	}

	@Override
	public Name name() {
		return spec.name;
	}
}
