package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorUp extends Command {

    public ElevatorUp() {
       requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.moveUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     // return Robot.elevator.elevatorFullUp() || !(Robot.oi.getElevatorUpButtonPressed() || Robot.oi.getElevatorUpTriggerPressed()   );
    	return false || Robot.elevator.elevatorFullUp();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.stop();
    }
}
