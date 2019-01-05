package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSwitchFromTheMiddle extends CommandGroup {

    public AutoLeftSwitchFromTheMiddle() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

    	
    	addSequential(new DriveForwardForTime(200,.6));
    	addSequential(new TurnToHeading(-55, .5, false));
    	addSequential(new DriveForwardForTime(1400,.6));    	
    	
    	CommandGroup temp = new CommandGroup();
    	temp.addParallel(new TurnToHeading(-10, .5,true));
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToSwitch));
    	temp.addParallel(new IntakeAutoDown());     	
    	addSequential(temp);
    	addSequential(new DriveForwardForTime(200,.6));

    	addSequential(new IntakeOutForTime(500));
    	addSequential(new UpdateCompletionTime());
    	addSequential(new IntakeAutoUp(500));
    	//addSequential(new DriveForDistance(-30, .6));
    	addSequential(new DriveForwardForTime(100, -.45));
    }
}
