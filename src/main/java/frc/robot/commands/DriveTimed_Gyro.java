// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.ChassisDrivetrainSubsystem;

import java.text.MessageFormat;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTimed_Gyro extends CommandBase {
  private final double m_duration;
  private final double m_speed;
  private final ChassisDrivetrainSubsystem m_drive;
  private long m_startTime;

  // The gyro sensor
  
  private static final double kAngleSetpoint = 0.0;
  private static final double kP = 0.005; // propotional turning constante Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
  

  /**
   * Creates a new DriveTime. This command will drive your robot for a desired speed and time.
   *
   * @param speed The speed which the robot will drive. positive to turn CW or right, negative to turn CCW or left
   * @param time How much time to drive in seconds
   * @param drive The drivetrain subsystem on which this command will run
   */
  public DriveTimed_Gyro(ChassisDrivetrainSubsystem drive, double speed, double time) {
    m_speed = speed;
    m_duration = time * 1000;
    m_drive = drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    m_drive.MyDrive_Arcade(0, 0, 0, 0);
    m_drive.zeroHeading(); // resets Gyro
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double directionValue = 1; //  fwd / =rev
    double headingValue = m_drive.getHeading();
    double turningValue = (kAngleSetpoint - headingValue) * kP; //The robots drivetrain speed along the Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
    // post the mechanism to the dashboard

    SmartDashboard.putNumber("Gyro-Hdg", headingValue );
    SmartDashboard.putNumber("turn value", turningValue);

		// Invert the direction of the turn if we are going backwards (- speed value )
		directionValue  = Math.copySign(directionValue , m_speed); //returns the first argument with the sign of the second argument.
    turningValue = turningValue * directionValue;
    m_drive.MyDrive_Arcade(m_speed, turningValue, 1, 0);

    // Invert the direction of the turn if we are going backwards
    // turningValue = Math.copySign(turningValue, m_joystick.getY());
    //m_drive.MyDrive_Tank(m_speed, m_speed, 1, 0);
    //m_drive.MyDrive_Arcade(m_speed, turningValue, 1, 0);


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
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
