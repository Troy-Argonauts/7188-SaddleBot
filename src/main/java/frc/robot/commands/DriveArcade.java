

package frc.robot.commands;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.ChassisDrivetrainSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveArcade extends CommandBase {
  private final ChassisDrivetrainSubsystem m_drivetrain;
  private final XboxController m_driverController;

  /**
   * Creates a new ArcadeDrive. This command will drive your robot according to the speed supplier
   * lambdas. This command does not terminate.
   *
   * @param drivetrain The drivetrain subsystem on which this command will run
   * @param driverController Joystick input source.   
   */
  public DriveArcade(ChassisDrivetrainSubsystem drivetrain, XboxController driverController) {
    m_drivetrain = drivetrain;    
    m_driverController = driverController;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xaxisSpeed, zaxisSpeed, SlowMotionSpeed, zaxisRotateDegree;
    double y_axis, x_axis, y_a1, x_a1;
    double LEC = m_drivetrain.getLeftEncoderCount(); 
    double LED = m_drivetrain.getLeftDistanceInch(); 
       /**
   * Arcade drive method for differential drive platform.
   * 
   * @param xaxisSpeed        robots drivetrain speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param SlowMotionSpeed   set a precentage of max speed the robot can use, if not
   *                          provided will default to full power
   * @param zaxisRotateDegree number of degrees to turn, (0..360)
   * @param zaxisSpeed        The robots drivetrain speed along the Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
   */
    //joystick sensitivity (gain) adjustment
    //https://www.chiefdelphi.com/t/paper-joystick-sensitivity-gain-adjustment/107280
    //equation used.....a1*X^3 + (1-a1)*X

    y_axis=-m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Arcade_Y_Axis);
    x_axis=-m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Arcade_X_Axis);
    y_a1=ChassisDriveConstants.k_a_Y_Axis*1;
    x_a1=ChassisDriveConstants.k_a_X_Axis*1;
    //xaxisSpeed = -m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Arcade_Y_Axis);  // Joystick raw data -1 Joystick pushed fwd, 1 joystick pulled back
    xaxisSpeed =  (y_a1*(Math. pow(y_axis,3)))+((1-y_a1)*y_axis);
    
    //zaxisSpeed = -m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Arcade_X_Axis);
    zaxisSpeed = (x_a1*(Math. pow(x_axis,3)))+((1-x_a1)*x_axis);

    SlowMotionSpeed = 1;
    zaxisRotateDegree = 0;
  
  //Joystick Deadzone
    if (Math.abs(xaxisSpeed) < 0.13) {
      // within 10% joystick, make it zero 
      xaxisSpeed = 0;
    }
    if (Math.abs(zaxisSpeed) < 0.13) {
      // within 10% joystick, make it zero 
      zaxisSpeed = 0;
    }

    m_drivetrain.MyDrive_Arcade(xaxisSpeed,zaxisSpeed,SlowMotionSpeed,zaxisRotateDegree);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
