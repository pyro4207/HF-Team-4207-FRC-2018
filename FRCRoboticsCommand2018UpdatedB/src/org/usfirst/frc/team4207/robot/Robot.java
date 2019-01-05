/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4207.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4207.robot.commands.DriveForwardForTime;

import org.usfirst.frc.team4207.robot.commands.ElevatorAutoForTime;
import org.usfirst.frc.team4207.robot.commands.ElevatorAutoToHigh;
import org.usfirst.frc.team4207.robot.commands.HFDelay;
import org.usfirst.frc.team4207.robot.commands.IntakeAutoDown;
import org.usfirst.frc.team4207.robot.commands.IntakeOutForTime;
import org.usfirst.frc.team4207.robot.commands.Turn;
import org.usfirst.frc.team4207.robot.commands.Turn90Forward;
import org.usfirst.frc.team4207.robot.commands.TurnToHeading;
import org.usfirst.frc.team4207.robot.commands.UpdateCompletionTime;
import org.usfirst.frc.team4207.robot.commands.oldDriveForwardForDistance2;
import org.usfirst.frc.team4207.robot.commands.AutoDoNothingCommand;
import org.usfirst.frc.team4207.robot.commands.AutoLeftScaleFromTheLeft;
import org.usfirst.frc.team4207.robot.commands.AutoLeftScaleFromTheLeftV2TWOCUBE;
import org.usfirst.frc.team4207.robot.commands.AutoLeftScaleFromTheRight;
import org.usfirst.frc.team4207.robot.commands.AutoLeftScaleFromTheRight2;
import org.usfirst.frc.team4207.robot.commands.AutoLeftSwitchFromTheLeft;
import org.usfirst.frc.team4207.robot.commands.AutoLeftSwitchFromTheMiddle;
import org.usfirst.frc.team4207.robot.commands.AutoRightScaleFromTheLeft;
import org.usfirst.frc.team4207.robot.commands.AutoRightScaleFromTheRightApproachSide;
import org.usfirst.frc.team4207.robot.commands.AutoRightScaleFromTheRightV2ApproachFront;
import org.usfirst.frc.team4207.robot.commands.AutoRightScaleFromTheRightV2TWOCUBE;
import org.usfirst.frc.team4207.robot.commands.AutoRightSwitchFromTheMiddle;
import org.usfirst.frc.team4207.robot.commands.AutoRightSwitchFromTheRight;
import org.usfirst.frc.team4207.robot.commands.CrossAutoLine;
import org.usfirst.frc.team4207.robot.commands.DriveForDistance;
import org.usfirst.frc.team4207.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4207.robot.subsystems.Elevator;
import org.usfirst.frc.team4207.robot.subsystems.Intake;
import org.usfirst.frc.team4207.robot.subsystems.Sensors;

import HF2017Utility.HFCameraPairSwitchable;
import HF2017Utility.HFCameraPairSwitchable2018;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Integer POSITION_LEFT = 1;
	public static final Integer POSITION_MIDDLE = 2;
	public static final Integer POSITION_RIGHT = 3;
	public static final Integer GOAL_SWITCH = 4;
	public static final Integer GOAL_SCALE = 5;
	public static final Integer GOAL_CROSS = 6;
	public static OI oi;
	
//	public static Compressor airCompressor;
	
	public static DriveTrain drivetrain = new DriveTrain();
	
	public static Intake intake = new Intake(RobotMap.INTAKE_LEFT_MOTOR_ID, 
											RobotMap.INTAKE_RIGHT_MOTOR_ID, RobotMap.INTAKE_UP_DOWN);
	
	public static Elevator elevator = new Elevator(RobotMap.ELEVATOR_LEFT_MOTOR_ID,
												RobotMap.ELEVATOR_RIGHT_MOTOR_ID);
	
	public static Sensors sensors = new Sensors();
	
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	SendableChooser<Integer> m_chooserLR = new SendableChooser<Integer>();

	SendableChooser<Integer> m_chooserRL = new SendableChooser<Integer>();
	SendableChooser<Integer> m_chooserLL= new SendableChooser<Integer>();
	SendableChooser<Integer> m_chooserRR = new SendableChooser<Integer>();
	SendableChooser<Integer> position_chooser = new SendableChooser<Integer>();
	public static double autoStartInitialAngle = 0;
	
//	public static HFCameraPairSwitchable cameraPair = new HFCameraPairSwitchable("cam0","cam1",false);
	public static HFCameraPairSwitchable2018 cameraPair = new HFCameraPairSwitchable2018();
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
	//	airCompressor = new Compressor();
		
		oi = new OI();
	//	UsbCamera usbCamera = new UsbCamera("cam0",0);
	//	MjpegServer  mjpegServer1 = new MjpegServer("serv",1181);
	//	mjpegServer1.setSource(usbCamera);
	//	CvSink cvSink = new CvSink("opencs_USB Camera0");
	//	cvSink.setSource(source);
 //    	UsbCamera usbCamera = new UsbCamera("cam 0",0);
  //   	usbCamera.setResolution(320, 240);

		
		
//		CameraServer server = CameraServer.getInstance();
  //   	server.startAutomaticCapture();	
	     	
	     	
     	
   //  	cameraPair.setEnabled(true);
		
		m_chooser.addDefault("Do Nothing", null);
		m_chooser.addObject("Drive Forward with Time", new DriveForwardForTime(3000,.6));
		m_chooser.addObject("NavX Test", new Turn(30,.35));
		m_chooser.addObject("Drive Forward With Distance", new DriveForDistance(200, .5));
		m_chooser.addObject("Left Scale - From the Left", new AutoLeftScaleFromTheLeft());
		m_chooser.addObject("Right Scale - From the Right", new AutoRightScaleFromTheRightV2ApproachFront());
		m_chooser.addObject("Left Scale - From the Right", new AutoLeftScaleFromTheRight2());
		m_chooser.addObject("Right Scale - From the Left", new AutoRightScaleFromTheLeft());
		m_chooser.addObject("Right Switch - From the Middle", new AutoRightSwitchFromTheMiddle());
		m_chooser.addObject("Left Switch - From the Middle", new AutoLeftSwitchFromTheMiddle());
		m_chooser.addObject("Left Switch - From the Left", new AutoLeftSwitchFromTheLeft());
		m_chooser.addObject("Right Switch - From the Right", new AutoRightSwitchFromTheRight());
		m_chooser.addObject("Intake Down", new IntakeAutoDown());
		
		
	//	m_chooser.addObject("Debug step",new DoNothing());//330, .7));
	//	m_chooser.addObject("Debug step",new ElevatorAutoForTime(Elevator.timeUpToScale));//330, .7));
		CommandGroup tmp = new CommandGroup();
		/*
		tmp.addSequential(new DriveForwardForTime(2000,.4));
		tmp.addSequential(new HFDelay(.5));
		
		tmp.addSequential(new TurnToHeading(90,.6,false));
		tmp.addSequential(new DriveForwardForTime(2000,.4));
		tmp.addSequential(new TurnToHeading(115,.6,false));
		tmp.addSequential(new DriveForwardForTime(1500,.4));
		tmp.addSequential(new TurnToHeading(90,.4,false));
		tmp.addSequential(new DriveForwardForTime(1500,.3));
		tmp.addSequential(new IntakeAutoDown());
		tmp.addSequential(new ElevatorAutoForTime(3000));
		tmp.addSequential(new IntakeOutForTime(500));
		*/
		tmp.addSequential(new DriveForwardForTime(4300,.4));
		tmp.addSequential(new TurnToHeading(-90,.4,false));
		tmp.addSequential(new DriveForwardForTime(2700,.4));
		tmp.addSequential(new TurnToHeading(-120,.4,false));
		tmp.addSequential(new IntakeAutoDown());
		tmp.addSequential(new ElevatorAutoForTime(1500));
		tmp.addSequential(new IntakeOutForTime(500));
		
		m_chooser.addObject("Debug step",tmp);//330, .7));
		
		
		
		
		m_chooserLR.addDefault("LR-Cross", GOAL_CROSS);
		m_chooserLR.addObject("LR-Switch", GOAL_SWITCH);
		m_chooserLR.addObject("LR-Scale", GOAL_SCALE);
		
		m_chooserRL.addDefault("RL-Cross", GOAL_CROSS);
		m_chooserRL.addObject("RL-Switch", GOAL_SWITCH);
		m_chooserRL.addObject("RL-Scale", GOAL_SCALE);
		
		m_chooserLL.addDefault("LL-Cross", GOAL_CROSS);
		m_chooserLL.addObject("LL-Switch", GOAL_SWITCH);
		m_chooserLL.addObject("LL-Scale", GOAL_SCALE);
		
		m_chooserRR.addDefault("RR-Cross", GOAL_CROSS);
		m_chooserRR.addObject("RR-Switch", GOAL_SWITCH);
		m_chooserRR.addObject("RR-Scale", GOAL_SCALE);

		position_chooser.addObject("Left", POSITION_LEFT);
		position_chooser.addDefault("Middle", POSITION_MIDDLE);
		position_chooser.addObject("Right", POSITION_RIGHT);
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("LR", m_chooserLR);
		SmartDashboard.putData("LL", m_chooserLL);
		SmartDashboard.putData("RL", m_chooserRL);
		SmartDashboard.putData("RR", m_chooserRR);
		SmartDashboard.putData("Pos", position_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		//airCompressor.stop();
		elevator.stop();
		intake.stopAll();
		Robot.drivetrain.resetEncoder();
	}

	private int displayCount=0;
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if(displayCount==0) {
			SmartDashboard.putString("DisabledAngled", "" + sensors.gyro.getAngle());
			SmartDashboard.putString("Disabled Encoder Distance", "a" + Robot.drivetrain.getEncoderDistance());
		}
		displayCount = (displayCount+1)%50;
	}
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public static long autoStartTime = 0;
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = (Command) m_chooser.getSelected();
		
		int position =(int) position_chooser.getSelected();
		
		String gameData;
		String lightArrange;
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		int retries = 100;
		while(gameData.length() < 2 && retries > 0) {
			retries--;
			try {
				Thread.sleep(5);
			} catch (InterruptedException ie) {
				// ignore
			}
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		
		
		SmartDashboard.putString("Game Data", gameData);
		if (gameData == null || gameData.length() == 0) {
			m_autonomousCommand = (Command) m_chooser.getSelected();
		}
		else {
			lightArrange = gameData.substring(0, 2);
			SmartDashboard.putString("Game Data",lightArrange + " b");
			
			if (lightArrange.compareTo("LL")==0) {
				int track = (int) m_chooserLL.getSelected();
				if (track == GOAL_SWITCH) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new AutoLeftSwitchFromTheLeft();
					}
					else if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new AutoLeftSwitchFromTheMiddle();
					}
					else if (position == POSITION_RIGHT) {
						m_autonomousCommand = new CrossAutoLine();
					}
				} 
				else if (track == GOAL_SCALE) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new AutoLeftScaleFromTheLeftV2TWOCUBE();
					}
					if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new CrossAutoLine(); // should not happen
					}
					if (position == POSITION_RIGHT) {
						m_autonomousCommand = new AutoLeftScaleFromTheRight();
					}
				}
				else if (track == GOAL_CROSS) {
					m_autonomousCommand = new CrossAutoLine();
				}
	
			} else if (lightArrange.compareTo("LR")==0) {
				int track = (int) m_chooserLR.getSelected();
				if (track == GOAL_SWITCH) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new AutoLeftSwitchFromTheLeft();
					}
					else if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new AutoLeftSwitchFromTheMiddle();
					}
					else if (position == POSITION_RIGHT) {
						m_autonomousCommand = new CrossAutoLine();  // should not happen
					}
				} 
				else if (track == GOAL_SCALE) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new AutoRightScaleFromTheLeft();
					}
					if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new CrossAutoLine(); //no
					}
					if (position == POSITION_RIGHT) {
						m_autonomousCommand = new AutoRightScaleFromTheRightApproachSide();
					}
				}
				else if (track == GOAL_CROSS) {
					m_autonomousCommand = new CrossAutoLine();
				}
	
			} else if (lightArrange.compareTo("RL") == 0) {

				int track = (int) m_chooserRL.getSelected();
				SmartDashboard.putString("ChosenAuto",lightArrange + "-" + track + " from " + position);
				if (track == GOAL_SWITCH) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new CrossAutoLine();  // never choose
					}
					else if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new AutoRightSwitchFromTheMiddle();
					}
					else if (position == POSITION_RIGHT) {
						m_autonomousCommand = new AutoRightSwitchFromTheRight();
					}
				} 
				else if (track == GOAL_SCALE) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new AutoLeftScaleFromTheLeft();
					}
					if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new CrossAutoLine();  // never choose						
					}
					if (position == POSITION_RIGHT) {
						m_autonomousCommand = new AutoLeftScaleFromTheRight();
					}
				}
				else if (track == GOAL_CROSS) {
					// do default
				}	
			} else if (lightArrange.compareTo("RR")==0) {
				int track = (int) m_chooserRR.getSelected();
				if (track == GOAL_SWITCH) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new CrossAutoLine();
					}
					else if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new AutoRightSwitchFromTheMiddle();
					}
					else if (position == POSITION_RIGHT) {
						m_autonomousCommand = new AutoRightSwitchFromTheRight();
					}
				} 
				else if (track == GOAL_SCALE) {
					if (position == POSITION_LEFT) {
						m_autonomousCommand = new AutoRightScaleFromTheLeft();
					}
					if (position == POSITION_MIDDLE) {
						m_autonomousCommand = new CrossAutoLine();
					}
					if (position == POSITION_RIGHT) {
						m_autonomousCommand = new AutoRightScaleFromTheRightApproachSide();
					}
				}
				else if (track == GOAL_CROSS) {
					m_autonomousCommand = new CrossAutoLine();
				}
	
			}
		}
		
		if (m_autonomousCommand != null) {
			autoStartInitialAngle = sensors.gyro.getAngle();
			autoStartTime = System.currentTimeMillis();
			m_autonomousCommand.start();
		}
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	//	airCompressor.start();
	//	drivetrain.setGearShift(DriveTrain.GEARSHIFT_SLOW);
	}

	/**
	 * This function is called periodically during operator control.
	 */	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		SmartDashboard.putString("MagSwich", elevator.elevatorSwitchHeight()? "Yes" : "No");
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	

}
