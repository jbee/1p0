package se.jbee.ipo;

import java.util.regex.Pattern;

import de.jbee.lang.Ord;
import de.jbee.lang.Order;
import de.jbee.lang.Ordering;

public final class Name
		implements CharSequence, Equals<Name> {

	public static final Ord<Object> ORDER = Order.typeaware( new NamedOrder(), Named.class );

	private static final Pattern VALID = Pattern.compile( "^[a-z](?:[-_a-z]*[a-z])?$" );

	public static Name named( Class<?> name ) {
		return inferFrom( name.getSimpleName() );
	}

	public static <E extends Enum<E>> Name named( E name ) {
		return inferFrom( name.name().toLowerCase() );
	}

	public static Name named( String name ) {
		if ( !VALID.matcher( name ).matches() ) {
			throw new IllegalArgumentException( "Not a valid name:" + name );
		}
		return new Name( name );
	}

	private static Name inferFrom( String name ) {
		String s = name.replaceAll( "[A-Z]", "-$0" );
		if ( s.startsWith( "-" ) ) {
			s = s.substring( 1 );
		}
		return named( s.toLowerCase() );
	}

	private final String name;

	private Name( String name ) {
		super();
		this.name = name.intern();
	}

	@Override
	public char charAt( int index ) {
		return name.charAt( index );
	}

	@Override
	public boolean equals( Object obj ) {
		return obj instanceof Name && equalTo( (Name) obj );
	}

	@Override
	public boolean equalTo( Name other ) {
		return name == other.name; // since we use intern it should be correct
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int length() {
		return name.length();
	}

	@Override
	public Name subSequence( int start, int end ) {
		return named( name.subSequence( start, end ).toString() );
	}

	@Override
	public String toString() {
		return name;
	}

	private static final class NamedOrder
			implements Ord<Named> {

		NamedOrder() {
			// make accessible
		}

		@Override
		public Ordering ord( Named left, Named right ) {
			return Order.alphabetical.ord( left.name(), right.name() );
		}

	}
}
