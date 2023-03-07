// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ChassisDrivetrainSubsystem;
import static frc.robot.Constants.*;
import java.text.MessageFormat;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {
  private final ChassisDrivetrainSubsystem m_drive;
  private final double m_distance;
  private final double m_speed;

  // The gain for a simple P loop
  private final double kP = ChassisDriveConstants.kGains_Driving.kP;

  // The gyro sensor
  
  private static final double kAngleSetpoint = 0.0;
  private static final double Gyro_kP = 0.005; // propotional turning constante Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
  

  /**
   * Creates a new DriveDistance. This command will drive your your robot for a desired distance at
   * a desired speed.
   *
   * @param speed  The speed (-1 to 1) at which the robot will drive, use negative to drive backwards.
   * @param inches The number of inches the robot will drive
   * @param drive  The drivetrain subsystem on which this command will run
   */
  public DriveDistance(ChassisDrivetrainSubsystem drive,double inches, double speed) {
    m_drive = drive;  
    m_distance = inches;
    m_speed = speed;
    addRequirements(m_drive);// Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println(MessageFormat.format("Started {0}", this.getName()));
    //m_drive.MyDrive_Tank(0, 0, 0, 0);
    m_drive.MyDrive_Arcade(0, 0, 0, 0);
    m_drive.resetChassisMotorEncoders(); // resets Encoders
    m_drive.zeroHeading(); // resets Gyro
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double directionValue = 1; //  fwd / =rev
    double headingValue = m_drive.getHeading();
    double turningValue = (kAngleSetpoint - headingValue) * Gyro_kP; //The robots drivetrain speed along the Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
    // post the mechanism to the dashboard

    SmartDashboard.putNumber("Gyro-Hdg", headingValue );
    SmartDashboard.putNumber("turn value", turningValue);

		// Invert the direction of the turn if we are going backwards (- speed value )
		directionValue  = Math.copySign(directionValue , m_speed); //returns the first argument with the sign of the second argument.
    turningValue = turningValue * directionValue;
    
    //Encoders can be used to ensure that a robot drives straight in a manner quite similar to how it is done with a gyroscope.
    // A simple implementation with a P loop is implemented below
    //double error = m_drive.getLeftDistanceInch() - m_drive.getRightDistanceInch();
    //System.out.println(MessageFormat.format("error, LH Speed, RH Speed {0}\t{1}\t{2}", error,m_speed-kP*error, m_speed+kP*error));
    double LEC = m_drive.getLeftEncoderCount();//Left Encoder Count
    double LED = m_drive.getLeftDistanceInch();//Left Encoder Distance
    
    //m_drive.MyDrive_Tank(m_speed, m_speed, 1, 0);
    // Standarded driving command above updated with a simple us of the P in PID to stabilize straight driving via the LH & RH motor encoders
   // m_drive.MyDrive_Tank(m_speed-kP*error, m_speed+kP*error, 1, 0); //+error indicates fast LH drivetrain side, -error indicates fast RH drivetrain side

   m_drive.MyDrive_Arcade(m_speed, turningValue, 1, 0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println(MessageFormat.format("Ended {0}", this.getName()));
    //m_drive.MyDrive_Tank(0, 0, 0, 0);
    m_drive.MyDrive_Arcade(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println(MessageFormat.format("isFinished {0}", this.getName()));
    // Compare distance travelled from start to desired distance
    return Math.abs(m_drive.getAverageDistanceInch()) >= m_distance;

  }
}
