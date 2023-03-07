// turn degrees using Gyro


package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.ChassisDrivetrainSubsystem;

import java.text.MessageFormat;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveTurnDegrees extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })  
  private final ChassisDrivetrainSubsystem m_drive;
  private final double m_degrees;
  private final double m_speed;
  private final double m_distanceToTravel;

  /**
   * Code set to use Gyro, for encoders change coed and......
   * Turns a specific number of degrees based on encoder input only (no gyro).
   * Uses the drivetrain turning circumference (diameter of the circle = distance between wheels).
   *
   * @param drive instance of ChassisDrivetrain
   * @param degrees number of degrees to turn, use positive to turn right, negative to turn left.
   * @param speed the speed to drive, use positive only (negative will change direction of angle turn).
   */
  public DriveTurnDegrees(ChassisDrivetrainSubsystem drive, double degrees, double speed) {
    m_drive = drive;
    m_degrees = degrees;
    m_speed = speed;

    // Calculate the inches of the circumference to travel based on percent degrees of a circle.
    // For example turning 90deg is ROBOT_CIRCUMFERENCE * .25 (1/4th of a circle).
    /* For Encoders, need to convert distance travelled to degrees. The Standard
       Robot Chassis
       The ROBOT_CIRCUMFERENCE is the Wheel track and is the distance measured across an axle from 
       the centre line of one tyre tread to the centre line of the opposite tyre tread
    */
    m_distanceToTravel = Math.abs(ChassisDriveConstants.kInchesPerDegree * m_degrees);
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println(MessageFormat.format("Started {0}", this.getName()));

    m_drive.resetChassisMotorEncoders(); //reseet Encoders
    m_drive.zeroHeading(); // resets Gyro
    System.out.println(MessageFormat.format("Gyro Angle prot {0}", m_drive.getHeading()));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.MyDrive_Arcade(0, m_speed, 1, m_degrees < 0 ? m_speed * -1 : m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println(MessageFormat.format("Ended {0}", this.getName()));
    m_drive.MyDrive_Arcade(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return Math.abs(m_drive.getLeftDistanceInch()) >= m_distanceToTravel && Math.abs(m_drive.getRightDistanceInch()) >= m_distanceToTravel;
    //return Math.abs(m_drive.getLeftDistanceInch()) >= m_distanceToTravel && Math.abs(m_drive.getLeftDistanceInch()) >= m_distanceToTravel;
    
    System.out.println(MessageFormat.format("isFinished {0}", this.getName()));
    System.out.println(MessageFormat.format("Current Angle {0} degrees", m_drive.getHeading()));
    return Math.abs(m_drive.getHeading()) >= Math.abs(m_degrees);
    
  }
}
