package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;
import xbot.common.math.PIDManager;


public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double oldPosition;
    PIDManager pid;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose, PIDManager.PIDManagerFactory pidManagerFactory) {
        this.drive = driveSubsystem;
        this.pose = pose;
        pid=pidManagerFactory.create("pid",1,2,30);
pid.setEnableErrorThreshold(true);
pid.setErrorThreshold(.1);
pid.setEnableDerivativeThreshold(true);
pid.setDerivativeThreshold(.1);

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
        pid.reset();
    }

    @Override
    public void execute() {
        ;
         currentPosition=pose.getPosition();

        double power=pid.calculate(targetPosition,currentPosition);

        if ((currentPosition<(targetPosition-.2))||(currentPosition>(targetPosition+.2)))
       {
            drive.tankDrive(power,power);
        }

    }

    @Override
    public boolean isFinished() {
         return pid.isOnTarget();
    }

}
