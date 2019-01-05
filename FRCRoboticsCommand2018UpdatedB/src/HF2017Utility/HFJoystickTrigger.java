package HF2017Utility;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class HFJoystickTrigger extends Button {

	
	private int axis;
	Joystick joystick;
	public HFJoystickTrigger(Joystick joystick,int axis) {
		this.joystick = joystick;
		this.axis = axis;
	}
	
	@Override 
	public boolean get() {
	//	System.out.println("Axis equals: " + joystick.getRawAxis(axis));
		return joystick.getRawAxis(axis) >= .5;
	}
}
