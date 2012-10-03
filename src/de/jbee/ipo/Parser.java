package de.jbee.ipo;

/**
 * Knows how to convert a {@link Character} based representation into a typed one.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public abstract class Parser
		implements Process {

	public static Param VALUE = null;

	private final Spec spec;

	protected Parser( Prototype form ) {
		super();
		this.spec = null; //TODO create spec from output form
	}

	@Override
	public Spec specification() {
		return spec;
	}

	private static class IntegerParser
			extends Parser {

		protected IntegerParser( Objective kind, Format pattern ) {
			super( null );
		}

		@Override
		public Output process( Input input ) {
			return null;
		}

	}
}
