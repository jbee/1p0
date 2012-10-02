package de.jbee.ipo;

import de.jbee.lang.Map;
import de.jbee.lang.Ord;
import de.jbee.lang.Order;
import de.jbee.lang.Ordering;
import de.jbee.lang.seq.Sequences;

public final class Input
		implements Named {

	public static final Ord<Object> PROCESS_ORDER = Order.typeaware( new ProcessOrder(),
			Process.class );

	public static Input input( Process process, Args args ) {
		return new Input( Program.program( process.specification().name(),
				Map.with.<Process> noEntries( PROCESS_ORDER ).insert(
						Sequences.key( process.specification().name ), process ) ),
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

	public static class ProcessOrder
			implements Ord<Process> {

		@Override
		public Ordering ord( Process left, Process right ) {
			return Name.ORDER.ord( left.specification(), right.specification() );
		}

	}
}
