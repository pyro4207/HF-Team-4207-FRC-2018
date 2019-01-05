package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScaleFromTheRightV2ApproachFront extends CommandGroup {

    public AutoRightScaleFromTheRightV2ApproachFront() {
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
    	
    //	addSequential(new DriveForDistance(385, .8, true));
    	addSequential(new DriveForwardForTime(4100,.6));
    
    	CommandGroup temp = new CommandGroup();
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToScale));
    	temp.addParallel(new IntakeAutoDown(500));
    	temp.addParallel(new DriveForDistance(262,.6, -6));
    	addSequential(temp);

       	addSequential(new IntakeOutForTime(500));       	
    	addSequential(new UpdateCompletionTime());
    	
    	//addSequential(new DriveForDistance(-15, .4, false));
    	addSequential(new DriveForwardForTime(4100, .6));	//Edited By Jake P

    }
}
