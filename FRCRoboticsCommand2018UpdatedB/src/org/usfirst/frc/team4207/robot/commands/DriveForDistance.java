package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForDistance extends Command {

	private double m_time;
	private double dSpeed;
	private double targetDistance;
	private double absTargetDistance;
	private double attempts = 0;
	private double originalSpeed;
	private double directionMultiplier = 1;
	private double brakeSpeed=0;
	private double fastDistance;
	private double fastSpeed;
	private boolean finishCoast = false;
	private boolean resetEncoder = true;
	final double NOTSET = -4207;
	private double initialAngleHeading = NOTSET;
	
	
	private double startTime;
	private int correct=0;
    public DriveForDistance(double distance, double speed) {
        realConstructor(distance, speed,10000,speed,false,true,NOTSET,1500);
    } 
    public DriveForDistance(double distance, double speed,double heading) {
        realConstructor(distance, speed,10000,speed,false,true,heading,1500);
    } 
    public DriveForDistance(double distance, double speed,boolean finishCoast) {
        realConstructor(distance, speed,10000,speed,finishCoast,true,NOTSET, 1500);
    } 
    public DriveForDistance(double distance, double speed,boolean finishCoast,boolean resetEncoder) {
        realConstructor(distance, speed,10000,speed,finishCoast,resetEncoder,NOTSET,1500);
    } 
    public DriveForDistance(double distance, double speed,double fastDistance,double fastSpeed) {
        realConstructor(distance, speed,fastDistance,fastSpeed,false,true,NOTSET,1500);
    } 
    public DriveForDistance(double distance, double speed,double fastDistance,double fastSpeed,boolean finishCoast) {
        realConstructor(distance, speed,fastDistance,fastSpeed,finishCoast,true,NOTSET,1500);
    } 
    public DriveForDistance(double distance, double speed,double fastDistance,double fastSpeed,boolean finishCoast,boolean resetEncoder) {
        realConstructor(distance, speed,fastDistance,fastSpeed,finishCoast,resetEncoder,NOTSET,1500);
    } 
    public DriveForDistance(double distance, double speed,double fastDistance,double fastSpeed,boolean finishCoast,boolean resetEncoder,double angleHeading) {
        realConstructor(distance, speed,fastDistance,fastSpeed,finishCoast,resetEncoder,angleHeading, 1500);
    } 

    public void realConstructor(double distance, double speed,double fastDistance,double fastSpeed,boolean finishCoast,boolean resetEncoder,double angleHeading, double stoppingTime) {
    	requires(Robot.drivetrain);
    	m_time = stoppingTime;
    	this.finishCoast = finishCoast;
    	speed = Math.abs(speed);
    	dSpeed = speed;
    	originalSpeed = speed;
    	brakeSpeed = finishCoast ? 0 : ( -(originalSpeed > .5 ? .5 : originalSpeed));
    	targetDistance = distance;
    	absTargetDistance = Math.abs(distance);
    	this.fastDistance = fastDistance;
    	this.fastSpeed = Math.abs(fastSpeed);
    	directionMultiplier = Math.abs(distance)/distance;
    	initialAngleHeading = angleHeading;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(resetEncoder) {
SmartDashboard.putString("Restenc", "resetting");    		
    		Robot.drivetrain.resetEncoder();
			double tmp = Robot.drivetrain.initialAngle;
    		if(initialAngleHeading == NOTSET) {
    			Robot.drivetrain.initialAngle = Robot.sensors.gyro.getAngle();
    		} else {
    			Robot.drivetrain.initialAngle = Robot.autoStartInitialAngle + initialAngleHeading;
    		}
    		SmartDashboard.putString("Drive Angle", "prev:" + tmp + " now" + Robot.drivetrain.initialAngle);
    	} else if(initialAngleHeading != NOTSET) {
			Robot.drivetrain.initialAngle = Robot.autoStartInitialAngle + initialAngleHeading;    		
    	}
    	
    	//setTimeout(dDistance/(dSpeed*));
    	startTime = System.currentTimeMillis();
    	setTimeout(m_time);
    	correct=0;
    	overshot = false;
    }
    boolean overshot = false;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double difference = targetDistance - Robot.drivetrain.getEncoderDistance();
    	double absDifference = Math.abs(difference);
    	double signDifference = difference > 0 ? 1 : -1;
    	
    	if(difference*targetDistance<0) {
    		overshot = true;
    	}

    	if(finishCoast && overshot) {
    		correct+= 100;
    		dSpeed = 0;
    	} else if (absDifference <=5 && absTargetDistance > 15) {
    		correct++;
    		if(overshot) {
    			dSpeed = 0;
    		} else {
    			dSpeed = brakeSpeed;
    		}
    	}
    	else if(absDifference <= 75) {
    		dSpeed = .35;
    		correct=0;
    	}
    	else if(absDifference > fastDistance) {
    		dSpeed = fastSpeed;
    		correct=0;
    	}
    	else {
    		dSpeed = originalSpeed;
    		correct=0;
    	}
    	dSpeed *= signDifference;
    	SmartDashboard.putString("speed", "" + dSpeed + " signD" + signDifference);
    	Robot.drivetrain.drive(dSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
//    	return isTimedOut() || correct>10;//Math.abs(Robot.drivetrain.getEncoderDistance() - dDistance)<2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
//    	Robot.drivetrain.drive(-.1);
//Timer.delay(2);
//Robot.drivetrain.stop();
    	System.out.println("FINISHED "); 
    //	double dist = Robot.drivetrain.getEncoderDistance();
   // 	System.out.println("Final Average Distance = "+ dist);
    //	Timer.delay(.5);
    	double dist = Robot.drivetrain.getEncoderDistance();
    	SmartDashboard.putString("DriveDist", "final distance " + dist + " in " + (System.currentTimeMillis() - startTime) + "correct:" + correct);
    	System.out.println("Final Average Distance Real= "+ dist + " target: " + targetDistance);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
