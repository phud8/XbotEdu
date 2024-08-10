package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;


    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem) {
        this.drive = driveSubsystem;
    }
    public double targetHeading;
    double currentRotation;
    double rotationChange;
    double oldRotation;
    public void setTargetHeading(double heading) {
        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.

        if(targetHeading<0)
        {
            targetHeading=180+(180+heading);
        }
        else
            targetHeading=heading;
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!
        currentRotation=pose.getCurrentHeading().getDegrees();
        if(currentRotation<0)
        {
            currentRotation=180+(180+pose.getCurrentHeading().getDegrees());
        }
        double error=targetHeading-currentRotation;
        rotationChange=currentRotation-oldRotation;
        double power=(.01*error)-(.5*rotationChange);

    if(currentRotation<targetHeading-20||currentRotation>targetHeading+20)
    {
        drive.arcadeDrive(-1,0);
    }
       else if(currentRotation>targetHeading+1||currentRotation<targetHeading-1)
    {

        drive.arcadeDrive(-power,0);
    }
        else
                drive.arcadeDrive(0,0);
    oldRotation=currentRotation;
    }
    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        return false;
    }
}
