package de.jbee.ipo;

/**
 * What does an {@link Attr} mean within the domain.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public enum Objective {

	/**
	 * as a PK for a data-set
	 */
	KEY,
	/**
	 * as a FK to another data-set
	 */
	REF,

	/**
	 * a internal identifier
	 */
	NAME,

	/**
	 * Just data with no special semantics.
	 */
	DATA,
}
