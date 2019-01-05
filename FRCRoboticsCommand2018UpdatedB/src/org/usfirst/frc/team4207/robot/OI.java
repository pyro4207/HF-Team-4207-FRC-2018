/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4207.robot;


import org.usfirst.frc.team4207.robot.commands.DriveTrainSetSpeedMode;
import org.usfirst.frc.team4207.robot.commands.ElevatorAutoToHigh;
import org.usfirst.frc.team4207.robot.commands.ElevatorDown;
import org.usfirst.frc.team4207.robot.commands.ElevatorLockEngage;
import org.usfirst.frc.team4207.robot.commands.ElevatorUp;
import org.usfirst.frc.team4207.robot.commands.IntakeDown;
import org.usfirst.frc.team4207.robot.commands.IntakeIn;
import org.usfirst.frc.team4207.robot.commands.IntakeOut;
import org.usfirst.frc.team4207.robot.commands.IntakeUp;
import org.usfirst.frc.team4207.robot.commands.PistonGearShiftChange;
import org.usfirst.frc.team4207.robot.subsystems.DriveTrain;

import HF2017Utility.HFJoystickTrigger;
import HF2017Utility.HFQuickCommand;
import HF2017Utility.HFJoystickPOVButton;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//main controller
	
	private final int INTAKEINBUTTON = 6;
	private final int INTAKEOUTBUTTON = 5;
	private final int ELEVATORUPBUTTON = 4;
	private final int ELEVATORDOWNBUTTON = 1;

	private final int SPEEDMODEFASTBUTTON = 2;
	private final int SPEEDMODESLOWBUTTON = 3;
	
	//main controller axis
	private final int ELEVATORUPTRIGGER = 3;
	private final int ELEVATORDOWNTRIGGER = 2;	
	
//	private final int GEARMODEFASTBUTTON = 7;
//	private final int GEARMODESLOWBUTTON = 8;
	
	//secondary controller
//	private final int ELEVATOR_AUTO_UP_BUTTON = 5;	
	private final int ELEVATORUP = 4;
	private final int ELEVATORDOWN = 1;
	
//	private final int ELEVATOR_LOCK_ENGAGE = 7;
//	private final int ELEVATOR_LOCK_DISENGAGE = 8;
	private final int CAMERA_1_BUTTON=5;
	private final int CAMERA_2_BUTTON=6;
	
	
	public Joystick m_joystick = new Joystick(0);
	public Joystick buttonBox = new Joystick(1);

	
	public OI () {
		
	//	edu.wpi.first.wpilibj.buttons.Trigger;
		
		new HFJoystickTrigger(m_joystick, ELEVATORUPTRIGGER).whileHeld(new ElevatorUp());
		new HFJoystickTrigger(m_joystick, ELEVATORDOWNTRIGGER).whileHeld(new ElevatorDown());
		new HFJoystickTrigger(buttonBox, ELEVATORUPTRIGGER).whileHeld(new ElevatorUp());
		new HFJoystickTrigger(buttonBox, ELEVATORDOWNTRIGGER).whileHeld(new ElevatorDown());

		new JoystickButton(m_joystick, INTAKEINBUTTON).whileHeld(new IntakeIn());
		new JoystickButton(m_joystick, INTAKEOUTBUTTON).whileHeld(new IntakeOut());
//		if(Robot.elevator != null) {
			new JoystickButton(m_joystick, ELEVATORUPBUTTON).whileHeld(new ElevatorUp());
			new JoystickButton(m_joystick, ELEVATORDOWNBUTTON).whileHeld(new ElevatorDown());
	//	}
		
		new JoystickButton(buttonBox, ELEVATORUP).whileHeld(new ElevatorUp());
		new JoystickButton(buttonBox, ELEVATORDOWN).whileHeld(new ElevatorDown());

		
		new JoystickButton(m_joystick,SPEEDMODESLOWBUTTON).whenPressed(new DriveTrainSetSpeedMode(DriveTrain.SPEED_MODE_SLOW));
		new JoystickButton(m_joystick,SPEEDMODEFASTBUTTON).whenPressed(new DriveTrainSetSpeedMode(DriveTrain.SPEED_MODE_FAST));

	//	new JoystickButton(m_joystick,GEARMODEFASTBUTTON).whenPressed(new PistonGearShiftChange(DriveTrain.GEARSHIFT_FAST));
	//	new JoystickButton(m_joystick,GEARMODESLOWBUTTON).whenPressed(new PistonGearShiftChange(DriveTrain.GEARSHIFT_FAST));
	
		//requires(robot);
	//	new POVButton(m_joystick,POVButton.POV_DOWN).whenPressed(new HFQuickCommand(()-> { Robot.airCompressor.stop(); }));
	//	new POVButton(m_joystick,POVButton.POV_UP).whenPressed(new HFQuickCommand(()-> { Robot.airCompressor.start(); }));

		new HFJoystickPOVButton(m_joystick,HFJoystickPOVButton.POV_UP).whileHeld(new IntakeUp());
		new HFJoystickPOVButton(m_joystick,HFJoystickPOVButton.POV_DOWN).whileHeld(new IntakeDown());
//		new JoystickButton(m_joystick,POVButton.POV_DOWN).whileHeld(new IntakeDown());

		
		//controller 2
	//	if(buttonBox.)
//		Command cmdIntakeUp = new IntakeUp();
//		JoystickButton btnIntakeUp = new JoystickButton(buttonBox,INTAKE_UP_BUTTON);
//		btnIntakeUp.whenPressed(new HFQuickCommand(()->  {Robot.intake.intakeUp();}));
//		btnIntakeUp.whenReleased(new HFQuickCommand(()->  {Robot.intake.stop();}));
		
		new HFJoystickPOVButton(buttonBox,HFJoystickPOVButton.POV_UP).whileHeld(new IntakeUp());
		new HFJoystickPOVButton(buttonBox,HFJoystickPOVButton.POV_DOWN).whileHeld(new IntakeDown());
		//		new POVButton(m_joystick,180).whenPressed(new CompressorEnable(false));


		new JoystickButton(buttonBox,CAMERA_1_BUTTON).whenPressed(new HFQuickCommand(()-> { Robot.cameraPair.setCamera(1); }));
		new JoystickButton(buttonBox,CAMERA_2_BUTTON).whenPressed(new HFQuickCommand(()-> { Robot.cameraPair.setCamera(2); }));

		
		
//		new JoystickButton(buttonBox,ELEVATOR_LOCK_ENGAGE).whenPressed(new ElevatorLockEngage(true) );
//		new JoystickButton(buttonBox,ELEVATOR_LOCK_DISENGAGE).whenPressed(new ElevatorLockEngage(false) );
//		new JoystickButton(buttonBox,ELEVATOR_LOCK_ENGAGE).whenPressed(new HFQuickCommand(()-> { Robot.elevator.turnLock(1); }));
//		new JoystickButton(buttonBox,ELEVATOR_LOCK_DISENGAGE).whenPressed(new HFQuickCommand(()-> { Robot.elevator.turnLock(-1); }));
		//	new POVButton(m_joystick,POVButton.POV_UP).whenPressed(new HFQuickCommand(()-> { Robot.airCompressor.start(); }));

	}
	
/*	
	
	public boolean getElevatoDownTriggerPressed() {
		return m_joystick.getRawAxis(ELEVATORDOWNTRIGGER) > .5;
	}	
	public boolean getElevatorDownButtonPressed() {
		return m_joystick.getRawButton(ELEVATORDOWNBUTTON);
	}
	*/
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick getJoystick() {
		return m_joystick;
	}
	
}
