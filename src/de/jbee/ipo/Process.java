package de.jbee.ipo;

public interface Process {

	Spec specification();

	Output process( Input input );
}
