package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardForTime extends Command {

	private double m_driveForwardSpeed;
	private double m_time;
	private double startAngle;
	
	public DriveForwardForTime(double time, double maxSpeed) {
		requires(Robot.drivetrain);
		m_time = time;
		m_driveForwardSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
	@Override
    protected void initialize() {
		Robot.drivetrain.setGyroAngle();
	   	startAngle = Robot.sensors.gyro.getAngle();
    	setTimeout(m_time/1000);
    }

    // Called repeatedly when this Command is scheduled to run
	@Override
    protected void execute() {
			Robot.drivetrain.drive(m_driveForwardSpeed);

    }

	@Override    
    protected boolean isFinished() {
     
       return isTimedOut();
    }

    // Called once after isFinished returns true
	@Override
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.drive(-.1);
    	Timer.delay(.1);
//	   	double offAngle = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
//	    if(Math.abs(offAngle) > 2) {
//	    	(new Turn(offAngle,.4)).execute();
//	    }
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
