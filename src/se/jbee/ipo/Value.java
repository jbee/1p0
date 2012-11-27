package se.jbee.ipo;

public final class Value {

	public static Value value( Attr attr, Object value ) {
		return new Value( attr, value );
	}

	public final Attr attr;
	public final Object value;

	private Value( Attr attr, Object value ) {
		super();
		this.attr = attr;
		this.value = value;
	}

	public <T> T as( Class<T> type ) {
		return type.cast( value );
	}

	@Override
	public String toString() {
		return String.valueOf( value );
	}
}
