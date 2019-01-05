package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftScaleFromTheRight2 extends CommandGroup {

    public AutoLeftScaleFromTheRight2() {
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
    	
    	
    	
    	addSequential(new DriveForDistance(580, .6, 130, .8));
    	addSequential(new TurnToHeading(-90, .55,true));
    	
    	//after turn
    	addSequential(new DriveForDistanceNoEnd(314, .8, 314,.8, false,true,-90));	
    	
    	CommandGroup temp2 = new CommandGroup();
    	temp2.addParallel(new DriveForDistance(160,.6,9999,.5,false,false,-90));
    	temp2.addParallel(new ElevatorAutoForTime(Elevator.timeUpToScale/2));
    	addSequential(temp2);

    	CommandGroup temp = new CommandGroup();
    	temp.addParallel(new TurnToHeading(0, .7,false));
    	temp.addParallel(new IntakeAutoDown(500));
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToScale/2));
    	addSequential(temp);

    	addSequential(new DriveForDistance(80, .6, 9999,.6,true,true,0));
    	addSequential(new IntakeOutForTime(1000));
       	
    	addSequential(new UpdateCompletionTime());
    	
       	addSequential(new IntakeAutoUp(150));
    	addSequential(new DriveForDistance(-30, .6));

}
}
