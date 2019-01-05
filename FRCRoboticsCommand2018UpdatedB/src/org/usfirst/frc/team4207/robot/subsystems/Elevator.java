package org.usfirst.frc.team4207.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import HF2017Utility.HFServo360;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	
	public static long timeUpToScale = 3200;
	public static long timeUpToSwitch = 1200;
	
	private DigitalInput limitSwitchUp = new DigitalInput(4);
//	private DigitalInput switchHeightSensor = new DigitalInput(5);

	private WPI_TalonSRX m_leftMotor;
	private WPI_TalonSRX m_rightMotor;
	private HFServo360 heightLock;
	private double upSpeed = -0.85;
	private double downSpeed = .7;
	
	public Elevator (int leftMotor, int rightMotor) {
		m_leftMotor = new WPI_TalonSRX (leftMotor);
		m_rightMotor = new WPI_TalonSRX (rightMotor);
		heightLock = new HFServo360(0);
		//SmartDashboard.putString("Lock Angle","???");
	//	m_rightMotor.setInverted(true);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void moveUp() {
		
		if(!elevatorFullUp()) {
			m_leftMotor.set(upSpeed);
			m_rightMotor.set(upSpeed);
		} else {
			m_leftMotor.set(0);
			m_rightMotor.set(0);			
		}
		
	}
	public void stop() {
		m_leftMotor.set(0);
		m_rightMotor.set(0);
    	heightLock.stop();
	}
	public void moveDown() {
		m_leftMotor.set(downSpeed);
		m_rightMotor.set(downSpeed);
		
	}
/*	public void moveDown(double speed) {
		speed = Math.abs(downSpeed)*Math.signum(downSpeed);
		m_leftMotor.set(speed);
		m_rightMotor.set(speed);
	}
	*/
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	
    }
    
    public void setLockMoveToEngage(boolean engaged) {
    	if(engaged) {
    		heightLock.startMoveClockwise();
    	} else {
    		heightLock.startMoveCounterClockwise();    		
    	}
    }
    
    public boolean elevatorFullUp() {
    	return !limitSwitchUp.get();
    }
 /*   public boolean elevatorSwitchHeight() {
    	return !switchHeightSensor.get();
    }*/
}

