package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnOld extends Command {

	double maxSpeed;
	int maxAttempts;
  	double speed = maxSpeed;
  	int loopCount = 0;
  	int attempts = 0;
	double minSpeedCurrent = .4;
	double lastAngleReading = -4207;
	double startTime;
//	double maxTime = 10000;
	double angleReading;
	double angle;
	double startAngle;
	
	
    public TurnOld(double angle, double maxSpeed, int maxAttempts) {
    	requires(Robot.sensors);
    	requires(Robot.drivetrain);
    	
    	this.maxSpeed = maxSpeed;
    	this.maxAttempts = maxAttempts;
    	this.angle = angle;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
		angleReading = 0;//startAngle;
	//	SmartDashboard.putString("Gyro Turn To","" + angle);
	   	startAngle = Robot.sensors.gyro.getAngle();
		SmartDashboard.putString("Gyro Angle","before "+startAngle);
	  	maxSpeed = Math.abs(maxSpeed);
		angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
		SmartDashboard.putString("Gyro Angle Start"," "+angleReading);
		SmartDashboard.putString("Gyro Angle End"," ");
		startTime = System.currentTimeMillis();
		setTimeout(4);
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //	double directionAdjust = -1;
    	angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
    	double angleDifference;
		if(angle > 0) {
			angleDifference = angle-angleReading;
	    	if(angleReading < angle-.5) {
	    		if(angleDifference > 10) {
	    			speed = maxSpeed;
	    		} else {
					speed = minSpeedCurrent + (angle-angleReading)/250;
					if(speed > maxSpeed) speed = maxSpeed;
	    		}
				SmartDashboard.putString("Speed B"," "+speed);
				Robot.drivetrain.m_drive.tankDrive(speed,-speed);
			//	Timer.delay(0.001);  wait for a motor update time
				angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
				if(loopCount >= 10)
				{
					if(lastAngleReading != -4207 && Math.abs(angleReading - lastAngleReading) < .005) {
						minSpeedCurrent += .01;
					}
					lastAngleReading = angleReading;
					loopCount = 0;
				}
				SmartDashboard.putString("min Speed","A " + minSpeedCurrent);
				loopCount++;
				SmartDashboard.putString("Gyro Angle","A "+angleReading);
				SmartDashboard.putString("Speed","A "+minSpeedCurrent);
	    	} else if(angleReading > angle+.5) {
		// might need a timer delay
	//			minSpeedCurrent = .3;
				speed = minSpeedCurrent;// + (angleReading-angle)/125;
				if(speed > maxSpeed) speed = maxSpeed;
				speed = 0;
				Robot.drivetrain.m_drive.tankDrive(-speed,speed);
			//	Timer.delay(0.001); // wait for a motor update time
				angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
				SmartDashboard.putString("Gyro Angle","B "+angleReading);
				if(lastAngleReading != -4207 && Math.abs(angleReading - lastAngleReading) < .005) {
					minSpeedCurrent += .0002;
				}
				SmartDashboard.putString("min Speed","B "+ minSpeedCurrent);
				lastAngleReading = angleReading;
				Timer.delay(.005);
			}
		} else if(angle < 0 ){
			//	SmartDashboard.putString("Gyro Angle",""+startAngle);//Reading);
		    	if(angleReading > angle+.5) {
					speed = minSpeedCurrent - (angle-angleReading)/250;
					if(speed > maxSpeed) speed = maxSpeed;
					SmartDashboard.putString("Speed B"," "+speed);
					Robot.drivetrain.m_drive.tankDrive(-speed,speed);
				//	Timer.delay(0.001);  wait for a motor update time
					angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
					if(loopCount >= 10)
					{
						if(lastAngleReading != -4207 && Math.abs(angleReading - lastAngleReading) < .005) {
							minSpeedCurrent += .01;
						}
						lastAngleReading = angleReading;
						loopCount = 0;
					}
					SmartDashboard.putString("min Speed","A " + minSpeedCurrent);
					loopCount++;
					SmartDashboard.putString("Gyro Angle","A "+angleReading);
					SmartDashboard.putString("Speed","A "+minSpeedCurrent);
			} else if(angleReading > angle-.5) {
			// might need a timer delay
		//			minSpeedCurrent = .3;
					speed = minSpeedCurrent;// + (angleReading-angle)/125;
					if(speed > maxSpeed) speed = maxSpeed;
					speed = 0;
					Robot.drivetrain.m_drive.tankDrive(speed,-speed);
				//	Timer.delay(0.001); // wait for a motor update time
					angleReading = Robot.sensors.gyro.getAngleRelativeTo(startAngle);
					SmartDashboard.putString("Gyro Angle","B "+angleReading);
					if(lastAngleReading != -4207 && Math.abs(angleReading - lastAngleReading) < .005) {
						minSpeedCurrent += .0002;
					}
					SmartDashboard.putString("min Speed","B "+ minSpeedCurrent);
					lastAngleReading = angleReading;
					Timer.delay(.005);
				}
			}
	//		myRobot.setLeftRightMotorOutputs(0,0);
	//		Timer.delay(.01);
/*		} else {
			//SmartDashboard.putString("Gyro Angle","" + startAngle) ; //Reading);
			angleReading = gyro.getAngleRelativeTo(startAngle);
			while(isEnabled() && isAutonomous()&& System.currentTimeMillis() - startTime < maxTime  && angleReading > angle +.5 ) {
				speed = minSpeedCurrent + (angleReading-angle)/250;
				if(speed > maxSpeed) speed = maxSpeed;
				myRobot.setLeftRightMotorOutputs(-speed, speed);
				Timer.delay(0.001); // wait for a motor update time
				angleReading = gyro.getAngleRelativeTo(startAngle);
				SmartDashboard.putString("Gyro Angle","C "+ angleReading);
//				SmartDashboard.putString("angle Change",""+Math.abs(angleReading - lastAngleReading) );
				if(loopCount >= 10)
				{
					if(lastAngleReading !=-4207 && Math.abs(angleReading - lastAngleReading) < .005) {
						minSpeedCurrent += .01;
					}
					lastAngleReading = angleReading;
					loopCount = 0;
				}
//				SmartDashboard.putString("min Speed","C "+ minSpeedCurrent);
				loopCount++;
				
				Timer.delay(.002);
			}
			myRobot.setLeftRightMotorOutputs(0,0);
			Timer.delay(.01);
			minSpeedCurrent = .19;
			angleReading = gyro.getAngleRelativeTo(startAngle);
			while(isEnabled() && isAutonomous()&& System.currentTimeMillis() - startTime < maxTime && angleReading < angle) {
				speed = minSpeedCurrent;// + (angle-angleReading)/125;
				if(speed > maxSpeed) speed = maxSpeed;
				myRobot.setLeftRightMotorOutputs(speed, -speed);
				Timer.delay(0.001); // wait for a motor update time
				angleReading = gyro.getAngleRelativeTo(startAngle);

				if(lastAngleReading != -4207 && Math.abs(angleReading - lastAngleReading) < .005) {
					minSpeedCurrent += .0002;
				}
				lastAngleReading = angleReading;
	//			SmartDashboard.putString("min Speed","D "+ minSpeedCurrent);
				SmartDashboard.putString("Gyro Angle","D "+angleReading);
				Timer.delay(.002);
			}
			myRobot.setLeftRightMotorOutputs(0,0);
		}
		attempts++;
		Timer.delay(0.05);
		angleReading = gyro.getAngleRelativeTo(startAngle);
*/	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if(System.currentTimeMillis() - startTime > maxTime  || attempts >= maxAttempts) return true;
    	if(Math.abs(angleReading - angle)<0.99) return true;
//	  	if(System.currentTimeMillis() - startTime < maxTime  && attempts < maxAttempts && Math.abs(angleReading - angle)>0.99) return false;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.m_drive.curvatureDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      	Robot.drivetrain.m_drive.curvatureDrive(0, 0, false);
    }
}
