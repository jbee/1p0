package de.jbee.ipo;


public final class Output {

	public static Output output( Input input, Data data ) {
		return new Output( input, data );
	}

	/**
	 * The input that produced the output.
	 */
	public final Input input;
	/**
	 * The output data. What has been computed.
	 */
	public final Data data;

	// failure as data or more explicit ? what about the sequence problem (that failures occur at different steps of the overall response) ?
	private Output( Input input, Data data ) {
		super();
		this.input = input;
		this.data = data;
	}

}
