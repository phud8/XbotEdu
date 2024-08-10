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
    public double targetRotation;
    public double oldRotation;
    public double rotationChange;
    @Override
    public void initialize() {
         startRotation=pose.getCurrentHeading().getDegrees();

        if(startRotation<0)
        {
            startRotation=180+(180+pose.getCurrentHeading().getDegrees());
        }
        else
        startRotation=pose.getCurrentHeading().getDegrees();
        System.out.println(startRotation);
        targetRotation=startRotation+90;

        System.out.println(targetRotation);

    }

    @Override
    public void execute() {
        currentRotation=pose.getCurrentHeading().getDegrees();
        if(currentRotation<0)
        {
            currentRotation=180+(180+pose.getCurrentHeading().getDegrees());
        }
        double error=targetRotation-currentRotation;
        rotationChange=currentRotation-oldRotation;
        double power=(.01*error)-(.5*rotationChange);
if(currentRotation<targetRotation-20||currentRotation>targetRotation+20)
{
    drive.arcadeDrive(-1,0);
}
       else if(currentRotation>targetRotation+1||currentRotation<targetRotation-1)
        {

            drive.arcadeDrive(-power,0);
        }
        else
            drive.arcadeDrive(0,0);
oldRotation=currentRotation;

    }

}
