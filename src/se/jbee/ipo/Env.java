package se.jbee.ipo;

import java.util.Arrays;

import se.jbee.ipo.util.Array;

/**
 * The general environment a {@link Process} is running in.
 * 
 * This is a set of settings, options or preferences.
 * 
 * @author Jan Bernitt (jan.bernitt@gmx.de)
 */
public final class Env {

	/**
	 * Stating point of each {@link Env}.
	 */
	public static final Env EMPTY = new Env( new Preference<?>[0], new Object[0] );

	private final Preference<?>[] preferences;
	private final Object[] values;

	private Env( Preference<?>[] preferences, Object[] values ) {
		super();
		this.preferences = preferences;
		this.values = values;
	}

	public <T> T value( Preference<T> pref ) {
		int idx = indexOf( pref );
		return pref.cast( preferences[idx] );
	}

	public <T> Env set( Preference<T> pref, T value ) {
		int idx = indexOf( pref );
		if ( idx < 0 ) {
			int len = preferences.length + 1;
			Preference<?>[] prefs = Arrays.copyOf( preferences, len );
			Object[] vals = Arrays.copyOf( values, len );
			prefs[len - 1] = pref;
			vals[len - 1] = value;
			return new Env( prefs, vals );
		}
		Object[] copy = values.clone();
		copy[idx] = value;
		return new Env( preferences, copy );
	}

	private int indexOf( Preference<?> pref ) {
		return Array.indexOfEqual( pref, preferences );
	}

	public static class Preference<T>
			implements Equals<Preference<?>> {

		public final Ident ident;
		public final Class<T> valueType;

		private Preference( Ident ident, Class<T> valueType ) {
			super();
			this.ident = ident;
			this.valueType = valueType;
		}

		T cast( Object value ) {
			return valueType.cast( value );
		}

		@Override
		public boolean equalTo( Preference<?> other ) {
			return valueType == other.valueType && ident.equalTo( other.ident );
		}
	}

}
