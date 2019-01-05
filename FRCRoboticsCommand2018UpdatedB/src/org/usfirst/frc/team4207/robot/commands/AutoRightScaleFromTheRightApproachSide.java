package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScaleFromTheRightApproachSide extends CommandGroup {

    public AutoRightScaleFromTheRightApproachSide() {
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
    	addSequential(new DriveForwardForTime(3000,.6));  	
//    	addSequential(new DriveForDistance(701, .4, 350, .8, false, true, -1));
    	addSequential(new TurnToHeading(-90, .8, false));
    	addSequential(new DriveForwardForTime(300, -.6));

    	
    	CommandGroup temp = new CommandGroup();
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToScale));
    	temp.addParallel(new IntakeAutoDown(500));
    	addSequential(temp);

    	
    	addSequential(new IntakeAutoDown(100));
    	addSequential(new IntakeOutForTime(1000));
    	
    	addSequential(new UpdateCompletionTime());

    	addSequential(new IntakeAutoUp(200));
    	addSequential(new ElevatorAutoForTimeDOWN(300));

    //	addSequential(new DriveForDistance(-20,.6));
    	addSequential(new DriveForwardForTime(-300,-.6));	//edited By Jake P
    	
    }
}
