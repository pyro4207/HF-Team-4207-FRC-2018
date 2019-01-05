package org.usfirst.frc.team4207.robot.subsystems;

import HF2017Utility.NavXGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sensors extends Subsystem {

	public NavXGyro gyro;
    
    public Sensors() {
    	gyro = new NavXGyro();
    	gyro.reset();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

