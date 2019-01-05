package HF2017Utility;

import edu.wpi.first.wpilibj.Servo;

public class HFServo360 {
	private Servo servo;
	
	public HFServo360(int pwmID) {
		servo = new Servo(pwmID);
	}
	
	public void startMoveClockwise() {
		servo.set(.4);
	}
	public void startMoveCounterClockwise() {
		servo.set(.6);
	}
	public void stop() {
		servo.set(.5);
	}
}
