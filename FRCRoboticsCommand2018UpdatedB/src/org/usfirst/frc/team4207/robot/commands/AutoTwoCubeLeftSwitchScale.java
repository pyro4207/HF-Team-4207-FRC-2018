package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTwoCubeLeftSwitchScale extends CommandGroup {

    public AutoTwoCubeLeftSwitchScale() {
    	
    	//SWITCH
    	
    	//addSequential(new DriveForwardForTime(2100, .6));
    	addSequential(new DriveForDistance(395, .6));
    	addSequential(new Turn(90, .5));
    	addSequential(new DriveForDistance(66, .45));
    	//addSequential(new ElevatorAutoForTime(2000));
    	addSequential(new ElevatorAutoForTime(Elevator.timeUpToSwitch));
    	addSequential(new IntakeAutoDown());
    	addSequential(new IntakeOutForTime(1));
    	
    	//MOVE DOWN AND GET SECOND CUBE
    	
    	addSequential(new DriveForwardForTime(1000, -.6));
    	addSequential(new ElevatorAutoForTimeDOWN(1750));
    	addSequential(new Turn(-45, .5));
    	addSequential(new DriveForwardForTime(1000, .6));
    	addSequential(new Turn(45, .5));
    	addSequential(new DriveForwardForTime(1000, .6));
    	addSequential(new IntakeInForTime(1000));
    	
    	//GO TO THE SCALE
    	
    	addSequential(new Turn(-90, .5));
    	addSequential(new UpdateCompletionTime());

    }
}
