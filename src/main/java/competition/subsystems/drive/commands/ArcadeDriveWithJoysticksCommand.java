package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.operator_interface.OperatorInterface;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    OperatorInterface operatorInterface;
    DriveSubsystem drive;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        this.operatorInterface = oi;
        this.drive = driveSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {

        double xValue = -operatorInterface.gamepad.getLeftVector().x;
        double yValue=operatorInterface.gamepad.getLeftVector().y;
    drive.arcadeDrive(xValue,yValue);

    }

}
