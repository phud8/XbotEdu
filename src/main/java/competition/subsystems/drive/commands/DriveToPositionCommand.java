package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double oldPosition;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
 public double targetPosition;
    public double currentPosition;

    public void setTargetPosition(double position) {
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
        targetPosition=position;
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }
    double positionChange=0;
    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!
        double erorr=targetPosition-pose.getPosition();
         currentPosition=pose.getPosition();
        positionChange=currentPosition-oldPosition;


        double power=(1*erorr)-(60*positionChange);
       if(currentPosition<targetPosition-.5)
       {drive.tankDrive(5,5);}
        else if ((currentPosition<(targetPosition-.2))||(currentPosition>(targetPosition+.2)))

       {
            drive.tankDrive(power,power);

        }
        oldPosition=currentPosition;
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        return (currentPosition<targetPosition+.2&&currentPosition>targetPosition-.2&&Math.abs(positionChange)<.002);
    }

}
