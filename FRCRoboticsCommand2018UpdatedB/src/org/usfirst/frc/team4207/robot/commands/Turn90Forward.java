package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Turn90Forward extends Command {
	double maxSpeed=.5;
  	double speed=.5;
	double minSpeedCurrent =.3;// .34;
	double lastAngleReading = -4207;
	double startTime;
	double angleReading;
	double angle;
	double startAngle;
    int correctCount = 0;
	
    public Turn90Forward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.maxSpeed = .3;
    	this.angle = 90;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		angleReading = 0;//startAngle;
    	   	startAngle = Robot.sensors.gyro.getAngle();

    	   	SmartDashboard.putString("Gyro Angle","before "+startAngle);
    	  	maxSpeed = Math.abs(maxSpeed);
    		angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);

    		SmartDashboard.putString("Gyro Angle Start"," "+angleReading);
    		SmartDashboard.putString("Gyro Angle End"," ");
    		startTime = System.currentTimeMillis();
    		setTimeout(3.5);
    		correctCount = 0;
    	    overshot = false;
    	}

        // Called repeatedly when this Command is scheduled to run
        boolean overshot;
        protected void execute() {
        //	double directionAdjust = -1;
        	angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
        	double angleDifference = angle-angleReading;
        	double angleAbsDifference = Math.abs(angleDifference);
        	double signDifference = angleDifference > 0 ? 1 : -1;
        	
        	if(angleDifference*angle < 0) {
        		overshot = true;
        	}
        	  	
    			if(angleAbsDifference > 20.0) {
    				speed = maxSpeed;
    				correctCount=0;
    			} else if(angleAbsDifference <3) {
    				correctCount++;
    				if(!overshot) {
    					speed =0;// -maxSpeed;
    				} else {
    					speed = 0;
    				}
    			} else {
    				correctCount=0;
    				speed = minSpeedCurrent;// + angleAbsDifference/350;
    				//if(speed > maxSpeed) speed = maxSpeed;
    				if(lastAngleReading != -4207 && Math.abs(angleReading - lastAngleReading) < .005) {
    					minSpeedCurrent += .001;
    				}
    			}
    			//speed *= signDifference;
    //System.out.println("Speed: " + speed + "  ang: " + angleDifference);
//    		SmartDashboard.putString("Speed B"," "+speed);
    		Robot.drivetrain.drive(speed,.8*signDifference);
//    		Robot.drivetrain.m_drive.tankDrive(speed,-speed);

    		lastAngleReading = angleReading;
    		SmartDashboard.putString("min Speed","A " + minSpeedCurrent);
        }

        
        // Make this return true when this Command no longer needs to run execute()
        protected boolean isFinished() {   	
    		SmartDashboard.putString("currentAngle"," "+ angleReading + " Count:" + correctCount);
        	return isTimedOut() || (correctCount > 10 ? true :false);
        }

        // Called once after isFinished returns true
        protected void end() {
        	Robot.drivetrain.m_drive.curvatureDrive(0, 0, false);
        	angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
    		SmartDashboard.putString("Turn Finish","finished correct" + angleReading + " in " + (System.currentTimeMillis() - startTime) + "ms");
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected void interrupted() {
          	Robot.drivetrain.m_drive.curvatureDrive(0, 0, false);
        	angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
    		SmartDashboard.putString("Turn Finish","finished time" + angleReading);
        }
    }
