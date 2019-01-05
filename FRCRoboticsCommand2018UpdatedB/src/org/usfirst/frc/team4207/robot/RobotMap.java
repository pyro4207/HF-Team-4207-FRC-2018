/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4207.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	// Joystick Buttons
	
	
	
	//  CAN ID
	
	public static final int DRIVEMOTOR_LEFT_FRONT = 13;
	public static final int DRIVEMOTOR_LEFT_REAR = 8;
	public static final int DRIVEMOTOR_RIGHT_FRONT = 10;
	public static final int DRIVEMOTOR_RIGHT_REAR = 9;
	
	public static final int INTAKE_LEFT_MOTOR_ID = 20;
	public static final int INTAKE_RIGHT_MOTOR_ID = 7;

	
	public static final int ELEVATOR_LEFT_MOTOR_ID = 1;
	public static final int ELEVATOR_RIGHT_MOTOR_ID = 12;
	public static final int INTAKE_UP_DOWN = 11;   //bottom right
}
