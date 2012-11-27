package se.jbee.ipo;

public interface Process {

	Spec specification();

	Output process( Input input );
}
