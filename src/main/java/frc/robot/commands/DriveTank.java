// Default command 
// is tank drive. This will run unless another command is scheduled over it.

package frc.robot.commands;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.ChassisDrivetrainSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTank extends CommandBase {
  private final ChassisDrivetrainSubsystem m_drivetrain;
  private final XboxController m_driverController;


  /**
   * Creates a new Tank Drive. This command will drive your robot according to the speed supplier
   * lambdas. This command does not terminate.
   *
   * @param drivetrain The drivetrain subsystem on which this command will run
   * @param driverController Joystick input source.   
   */
  public DriveTank(ChassisDrivetrainSubsystem drivetrain, XboxController driverController) {
    m_drivetrain = drivetrain;    
    m_driverController = driverController;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xaxisSpeedLH, xaxisSpeedRH, SlowMotionSpeed, zaxisRotateDegree;
    double R_axis, L_axis, R_a1, L_a1;
       /**
   * Tank drive method for differential drive platform.
   * 
   * @param xaxisSpeedLH      The robots left drivetrain speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param xaxisSpeedRH      The robots right drivetrain speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param SlowMotionSpeed   set a precentage of max speed the robot can use, if not
   *                          provided will default to full power
   * @param zaxisRotateDegree number of degrees to turn, (0..360)
   * @param zaxisSpeed        The robots drivetrain speed along the Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
   */

       //joystick sensitivity (gain) adjustment
    //equation used.....a1*X^3 + (1-a1)*X

    R_axis=-m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Right_Axis);
    L_axis=-m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Left_Axis);
    R_a1=ChassisDriveConstants.k_a_R_Axis*1;
    L_a1=ChassisDriveConstants.k_a_L_Axis*1;

    //xaxisSpeedLH = -m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Left_Axis);  // Joystick raw data -1 Joystick pushed fwd, 1 joystick pulled back
    xaxisSpeedLH =(L_a1*(Math. pow(L_axis,3)))+((1-L_a1)*L_axis);

    //xaxisSpeedRH = -m_driverController.getRawAxis(ChassisDriveConstants.kDriver_Right_Axis);
    xaxisSpeedRH = (R_a1*(Math. pow(R_axis,3)))+((1-R_a1)*R_axis);
    
    SlowMotionSpeed = 1;
    zaxisRotateDegree = 0;

       //Joystick Deadzone
    if (Math.abs(xaxisSpeedLH ) < 0.13) {
      // within 10% joystick, make it zero 
      xaxisSpeedLH  = 0;
    }
    if (Math.abs(xaxisSpeedRH) < 0.13) {
      // within 10% joystick, make it zero 
      xaxisSpeedRH = 0;
    }
    
    //m_drivetrain.tankDrive( m_xaxisSpeedLH, m_xaxisSpeedRH, true);
    m_drivetrain.MyDrive_Tank( xaxisSpeedLH, xaxisSpeedRH, SlowMotionSpeed, zaxisRotateDegree); //  xaxis + FWD, - Reverse, zaxis +CW, -CCW
  
    //System.out.println(m_xaxisSpeedLH);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
