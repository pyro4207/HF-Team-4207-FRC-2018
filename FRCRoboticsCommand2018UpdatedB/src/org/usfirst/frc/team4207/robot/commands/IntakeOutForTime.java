package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class IntakeOutForTime extends Command {
	
	private double m_time;

	
    public IntakeOutForTime(double time) {
    	requires(Robot.intake);
    	m_time = time;
    	
    	
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(m_time/1000);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.startOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     	return isTimedOut();
     }
        
    

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stopAll();
    }
}
