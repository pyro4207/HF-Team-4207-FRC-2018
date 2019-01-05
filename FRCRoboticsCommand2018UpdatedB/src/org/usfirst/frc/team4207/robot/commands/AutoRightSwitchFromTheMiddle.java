package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.Robot;
import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import HF2017Utility.HFQuickCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitchFromTheMiddle extends CommandGroup {

    public AutoRightSwitchFromTheMiddle() {
     
    	CommandGroup temp = new CommandGroup();
    	temp.addParallel(new DriveForwardForTime(1200, .6));
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToSwitch ));
    	addSequential(temp);

    	addSequential(new IntakeAutoDown(1300));
    	addSequential(new IntakeOutForTime(500));
    	addSequential(new UpdateCompletionTime());
    	
    	//backaway from switch
    	addSequential(new IntakeAutoUp(1000));
    	//addSequential(new DriveForDistance(-50, .6));
    	addSequential(new DriveForwardForTime(100, -.45));
    	
    	//Second cube
    	//CommandGroup grpTurnToGrabSecond = new CommandGroup();
    	//grpTurnToGrabSecond.addParallel(new ElevatorAutoForTimeDOWN(1050));
    	//grpTurnToGrabSecond.addParallel(new Turn(-45, .6));
    	//grpTurnToGrabSecond.addParallel(new HFQuickCommand(()-> { Robot.intake.startIn(); }));
    	//addSequential(grpTurnToGrabSecond);
    	
    	//CommandGroup grpDriveForwardandGrab = new CommandGroup();
    	//grpDriveForwardandGrab.addParallel(new IntakeAutoDown(1000));
    	//grpDriveForwardandGrab.addParallel(new DriveForDistance(150,.35));
    	//addSequential(grpDriveForwardandGrab);

    	

    	
    }
}
