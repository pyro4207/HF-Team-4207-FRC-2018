package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitchFromTheRight extends CommandGroup {

    public AutoRightSwitchFromTheRight() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//addSequential(new DriveForwardForTime(2100, .6));
    	
    	addSequential(new DriveForwardForTime(1750, .6));
    	//addSequential(new DriveForDistance(325, .6, 200, .8, false));
    	addSequential(new TurnToHeading(-90, .5, true));

    	CommandGroup temp = new CommandGroup();
    //	temp.addParallel(new DriveForDistance(15, .45, true));
    	temp.addParallel(new DriveForwardForTime(1200, .6));	//Jake P
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToSwitch));
    	temp.addParallel(new IntakeAutoDown());
    	addSequential(temp);
    	
    	addSequential(new IntakeOutForTime(500));
    	addSequential(new UpdateCompletionTime());
    	addSequential(new IntakeAutoUp());
    //	addSequential(new DriveForDistance(-20, .6));
    	addSequential(new DriveForwardForTime(100, -.4));	//Jake P
    	

    }
}
