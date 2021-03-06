package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDown extends Command {

    public IntakeDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Intake down pressed");
    	Robot.intake.intakeDown();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; 
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stopAll();
    	System.out.println("Intake down interupted");
    }
}
