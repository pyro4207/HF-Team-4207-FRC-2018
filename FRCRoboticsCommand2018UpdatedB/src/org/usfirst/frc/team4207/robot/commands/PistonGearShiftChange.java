package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PistonGearShiftChange extends Command {
	private int gearShiftSetting;
    public PistonGearShiftChange(int gearShiftSetting) {
    	requires(Robot.drivetrain);
    	this.gearShiftSetting = gearShiftSetting;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   // 	Robot.drivetrain.setGearShift(this.gearShiftSetting);
    	System.out.println("Setting gear " + (this.gearShiftSetting == Robot.drivetrain.GEARSHIFT_FAST ? "fast" : "slow"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
