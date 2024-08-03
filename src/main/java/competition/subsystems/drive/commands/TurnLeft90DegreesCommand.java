package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;

    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
public double startRotation;
    public double currentRotation;
    @Override
    public void initialize() {
         startRotation=pose.getCurrentHeading().getDegrees();
         currentRotation=pose.getCurrentHeading().getDegrees();
        System.out.println(startRotation);
    }

    @Override
    public void execute() {

        currentRotation=pose.getCurrentHeading().getDegrees();
        if(currentRotation<startRotation+90)
        {

            drive.arcadeDrive(-1,0);
        }
        else
            drive.arcadeDrive(0,0);


        System.out.println(currentRotation);
    }

}
