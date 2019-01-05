package org.usfirst.frc.team4207.robot.commands;

import org.usfirst.frc.team4207.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightScaleFromTheRightV2TWOCUBE extends CommandGroup {

    public AutoRightScaleFromTheRightV2TWOCUBE() {
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

    	
    	addSequential(new DriveForDistance(385, .8, true));
    	//addSequential(new DriveForwardForTime(4100,.6));
    	
    	CommandGroup temp = new CommandGroup();
    	temp.addParallel(new ElevatorAutoForTime(Elevator.timeUpToScale));
    	temp.addParallel(new IntakeAutoDown(500));
    	temp.addParallel(new DriveForDistance(262,.6, -6));
    	addSequential(temp);

       	addSequential(new IntakeOutForTime(500));
       	
       	addSequential(new ElevatorAutoForTimeDOWN(1140));

       	CommandGroup temp2 = new CommandGroup();
       	temp2.addParallel(new IntakeAutoDown(1300));
       	temp2.addParallel(new ElevatorAutoForTimeDOWN(1200));
      	temp2.addParallel(new DriveForDistance(-30, .6, -130));
       	addSequential(temp2);       	

       	addSequential(new TurnToHeading(-145, .8, false));
       	
      	CommandGroup temp3 = new CommandGroup();
       	temp3.addParallel(new DriveForDistance(120, .6, -145));
       	temp3.addParallel(new IntakeInForTime(800));   	
       	addSequential(temp3);

       	CommandGroup temp4 = new CommandGroup();
       	temp4.addParallel(new DriveForDistance(-20, .6));
       	temp4.addParallel(new IntakeInForTime(300));   	
       	addSequential(temp4);
       	
       	addSequential(new ElevatorAutoForTime(Elevator.timeUpToSwitch));
       	addSequential(new DriveForDistance(25, .6, true));
       	addSequential(new IntakeOutForTime(500));
       	addSequential(new UpdateCompletionTime());

    }
}
