package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScaleFromTheLeft extends CommandGroup {

    public AutoRightScaleFromTheLeft() {
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
    	
    	addSequential(new DriveForDistance(560, .6));
    	addSequential(new Turn(90, .5));
    	addSequential(new DriveForDistance(500, .6));
    	addSequential(new Turn(-90, .5));
    	addSequential(new DriveForDistance(35, .6));
    	//addSequential(new ElevatorAutoToHigh());
    	addSequential(new ElevatorAutoForTime(Elevator.timeUpToScale));
    	addSequential(new IntakeAutoDown());
    	addSequential(new IntakeOutForTime(1000));
    	addSequential(new UpdateCompletionTime());

    }
}
