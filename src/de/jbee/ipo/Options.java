package de.jbee.ipo;

/**
 * The {@link Process} that responds with a set of possible options.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 * 
 */
public abstract class Options
		implements Process {

	private final Spec spec;

	protected Options( Spec spec ) { // TODO not ask for full spec to ensure a fixed format
		super();
		this.spec = spec;
	}

	@Override
	public final Spec specification() {
		return spec;
	}
}
