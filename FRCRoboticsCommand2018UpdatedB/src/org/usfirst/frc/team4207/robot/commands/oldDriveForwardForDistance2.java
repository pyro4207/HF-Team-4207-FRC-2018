package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class oldDriveForwardForDistance2 extends Command {

	private double dSpeed;
	private double dDistance;
	private double attempts = 0;
	private double originalSpeed;
	private double directionMultiplier = 1;
	
	private double startTime;
	
    public oldDriveForwardForDistance2(double distance, double speed) {
    	requires(Robot.drivetrain);
    	speed = Math.abs(speed);
    	dSpeed = speed;
    	originalSpeed = speed;
    	dDistance = distance;
    	directionMultiplier = Math.abs(distance)/distance;
    } 

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.initialAngle = Robot.sensors.gyro.getAngle();
    	//setTimeout(dDistance/(dSpeed*));
    	startTime = System.currentTimeMillis();
    	setTimeout(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.drivetrain.getEncoderDistance() - dDistance) <= 40) {
    		dSpeed = .35 * directionMultiplier;
    	}
    	else {
    		dSpeed = originalSpeed * directionMultiplier;
    	}
    	SmartDashboard.putString("speed", "" + dSpeed);
    	Robot.drivetrain.drive(dSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut() || Math.abs(Robot.drivetrain.getEncoderDistance() - dDistance)<2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
//    	Robot.drivetrain.drive(-.1);
//Timer.delay(2);
//Robot.drivetrain.stop();
    	System.out.println("FINISHED "); 
    	double dist = Robot.drivetrain.getEncoderDistance();
    	System.out.println("Final Average Distance = "+ dist);
    	Timer.delay(.5);
    	dist = Robot.drivetrain.getEncoderDistance();
    	SmartDashboard.putString("DriveDist", "final distance " + dist + " in " + (System.currentTimeMillis() - startTime));
    	System.out.println("Final Average Distance Real= "+ dist);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
