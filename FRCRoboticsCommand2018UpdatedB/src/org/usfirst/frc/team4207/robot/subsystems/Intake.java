package org.usfirst.frc.team4207.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	public WPI_TalonSRX m_intakeUpDown;
	public WPI_TalonSRX m_intakeLeft;
	private WPI_TalonSRX m_intakeRight;
	private double intakeInSpeed = -.6;
	private double intakeOutSpeed = .6;
	private double intakeUpDownSpeed = 1;
	
	public Intake (int intakeLeft, int intakeRight, int intakeUpDown) {
		m_intakeUpDown = new WPI_TalonSRX (intakeUpDown);
		m_intakeLeft = new WPI_TalonSRX (intakeLeft);
		m_intakeRight = new WPI_TalonSRX (intakeRight);
		m_intakeRight.setInverted(true);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void startIn() {
		m_intakeLeft.set(intakeInSpeed);
		m_intakeRight.set(intakeInSpeed);
	}
	public void stopAll() {
		m_intakeUpDown.set(0);
		m_intakeLeft.set(0);
		m_intakeRight.set(0);
	}
	public void startOut() {
		m_intakeLeft.set(intakeOutSpeed);
		m_intakeRight.set(intakeOutSpeed);
	}
	
	public void intakeUp() {
		m_intakeUpDown.set(-intakeUpDownSpeed);		
	}
	public void intakeDown() {
		m_intakeUpDown.set(intakeUpDownSpeed);		
	}
	public void stopVertical() {
		m_intakeUpDown.set(0);
	}
	public void stopInOut() {
		m_intakeLeft.set(0);
		m_intakeRight.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	
    	
    }
}

