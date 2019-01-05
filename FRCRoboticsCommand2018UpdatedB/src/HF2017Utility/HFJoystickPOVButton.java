package HF2017Utility;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class HFJoystickPOVButton extends Button {
	public static int POV_UP = 0;
	public static int POV_UP_RIGHT = 45;
	public static int POV_RIGHT = 90;
	public static int POV_DOWN_RIGHT = 135;
	public static int POV_DOWN = 180;
	public static int POV_DOWN_LEFT = 225;
	public static int POV_LEFT = 270;
	public static int POV_UP_LEFT = 315;
	
	private int direction;
	Joystick joystick;
	public HFJoystickPOVButton(Joystick joystick,int direction) {
		this.joystick = joystick;
		this.direction = direction;
	}
	
	@Override 
	public boolean get() {
		return joystick.getPOV() == direction;
	}
}
