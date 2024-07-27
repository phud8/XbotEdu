package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
 public double targetPosition;

    public void setTargetPosition(double position) {
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
        targetPosition=position;
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

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
        double power=1*erorr;

        if (pose.getPosition()!=targetPosition)
        {

            drive.tankDrive(power,power);

        }
        else{
            drive.tankDrive(0,0);
        }
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        return (pose.getPosition()==targetPosition);
    }

}
