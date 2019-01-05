package org.usfirst.frc.team4207.robot.subsystems;

import org.usfirst.frc.team4207.robot.Robot;
import org.usfirst.frc.team4207.robot.RobotMap;
import org.usfirst.frc.team4207.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import HF2017Utility.HFPiston;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveTrain extends Subsystem {
	//private HFPiston piston;
	private Encoder encoderRightb;
	private Encoder encoderLeft;
	private WPI_TalonSRX m_frontLeft = new WPI_TalonSRX(RobotMap.DRIVEMOTOR_LEFT_FRONT);
	private WPI_TalonSRX m_rearLeft = new WPI_TalonSRX(RobotMap.DRIVEMOTOR_LEFT_REAR);
	  
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
	
	private WPI_TalonSRX m_frontRight = new WPI_TalonSRX(RobotMap.DRIVEMOTOR_RIGHT_FRONT);
	private WPI_TalonSRX m_rearRight = new WPI_TalonSRX(RobotMap.DRIVEMOTOR_RIGHT_REAR);
	
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	
	public DifferentialDrive m_drive;
	
	public final static int SPEED_MODE_FAST = 1;
	public final static int SPEED_MODE_SLOW = 0;
	
	public double initialAngle;
	
	private double speedMultiplier = 0;  // set in constructor
	  
	public DriveTrain () {
//		piston = new HFPiston(0,1);
//		piston.pistonOut();
		encoderRightb = null;//new Encoder(0, 1, true, Encoder.EncodingType.k4X);
		encoderLeft = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
		double distPerPulse = (6*Math.PI*2.54)/2048;  // ( cm for a 6inch wheel / ppr )
		//encoderLeft.setDistancePerPulse(distPerPulse*4.0033); 
		
		if(encoderRightb != null) encoderRightb.setDistancePerPulse(distPerPulse);
		encoderLeft.setDistancePerPulse(distPerPulse);
		//leftEnc.
	/*	m_frontLeft.setInverted(true);
		m_frontRight.setInverted(true);
		m_rearLeft.setInverted(true);
		m_rearRight.setInverted(true);
		*/
		
		m_drive = new DifferentialDrive(m_left, m_right);
		m_drive.setSafetyEnabled(true);
		m_drive.setExpiration(0.1);
		m_drive.setMaxOutput(1.0);
		
		setSpeedMode(SPEED_MODE_SLOW);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void setSpeedMode(int mode) {
		switch(mode) {
		case SPEED_MODE_SLOW:
			speedMultiplier = .7;
			break;
		case SPEED_MODE_FAST:
			speedMultiplier = 0.9;
			break;
		default:
			speedMultiplier = .7;
		}
		System.out.println("speed is " + speedMultiplier);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
    
    public void tankDrive(Joystick joy) {
//    	double distance = encoderLeft.getDistance();
  ///  	double distance1;
   // 	distance1 = encoderRightb == null ? -4207 : encoderRightb.getDistance();  
   // 	double distance2 = encoderLeft.getDistance();  	
    	
   // 	System.out.println("Right" + " " + distance1 +
   // 						"Left" + distance2);
    	
		m_drive.tankDrive(-1*speedMultiplier*joy.getRawAxis(1),-1*speedMultiplier* joy.getRawAxis(5));
	}
    public void drive(double speed, double angle) {
    	m_drive.curvatureDrive(speed, angle, false);
    }
    public void drive(double speed) {
    	System.out.println("drive");
    	drive(speed,-.05*Robot.sensors.gyro.getAngleRelativeTo(initialAngle));    
//    	SmartDashboard.putString("distance", "" + encoderRight.getDistance());
    }
    public void resetEncoder() {
    	return;
/*    	if(encoderRightb != null) encoderRightb.reset();
    	encoderLeft.reset();
*/
    }
    
    public double getEncoderDistance() {
    	return 0;/*
      	double leftDistance = encoderLeft.getDistance();
    	double rightDistance = encoderRightb == null ? -4207 : encoderRightb.getDistance();
   
    	System.out.print("  R:" + rightDistance);
    	System.out.println("  L:" + leftDistance);
   
    	if(encoderRightb == null) return leftDistance;

    	double encoderAverage = (leftDistance + rightDistance) /2;
    	if(Math.abs(encoderRightb.getDistance()) < 10) return leftDistance;
    	else if(Math.abs(encoderLeft.getDistance()) < 10) return rightDistance;
    	else return encoderAverage;
   */
    }
    
	public void stop() {
		m_drive.curvatureDrive(0, 0, false);
	}
	public static final int GEARSHIFT_FAST = 1;
	public static final int GEARSHIFT_SLOW = 2;
	
/*	public void setGearShift(int gearMode) {
		if (gearMode == GEARSHIFT_FAST) {
			piston.pistonIn();
		} else {
			piston.pistonOut();
		}
	}
	public int getGearShiftSetting() {
		return piston.isIn() ? GEARSHIFT_FAST : GEARSHIFT_SLOW;
	}
	
*/	public void setGyroAngle() {
		initialAngle = Robot.sensors.gyro.getAngle();
	}
	}

