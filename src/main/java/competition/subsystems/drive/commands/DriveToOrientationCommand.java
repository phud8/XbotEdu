package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

public class DriveToOrientationCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;


    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose=pose;
    }
    public double targetHeading;
    public double currentHeading;
    public double headingChange;
   public double oldHeading;
    public void setTargetHeading(double heading) {
        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.

        if(heading<0)
        {
            targetHeading=180+(180+heading);
        }
        else
            targetHeading=heading;
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
        currentHeading =pose.getCurrentHeading().getDegrees();
        if(currentHeading <0)
        {
            currentHeading =180+(180+pose.getCurrentHeading().getDegrees());
        }
        System.out.println(currentHeading);
        System.out.println(targetHeading);
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!
        currentHeading =pose.getCurrentHeading().getDegrees();
        if(currentHeading <0)
        {
            currentHeading =180+(180+pose.getCurrentHeading().getDegrees());
        }
        double error=targetHeading- currentHeading;
        headingChange = currentHeading - oldHeading;
        double power=(.01*error)-(.5* headingChange);

    if(currentHeading <targetHeading-20|| currentHeading >targetHeading+20)
    {
        drive.arcadeDrive(-1,0);
    }
       else if(currentHeading >targetHeading+1|| currentHeading <targetHeading-1)
    {

        drive.arcadeDrive(-power,0);
    }

    oldHeading = currentHeading;
    }
    @Override
    public boolean isFinished() {
        return((currentHeading>targetHeading-1&&currentHeading<targetHeading+1)&&Math.abs(headingChange)<.01);

        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)

    }
}
