// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ChassisDrivetrainSubsystem;
import java.text.MessageFormat;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTimed extends CommandBase {
  private final double m_duration;
  private final double m_speed;
  private final ChassisDrivetrainSubsystem m_drive;
  private long m_startTime;

  /**
   * Creates a new DriveTime. This command will drive your robot for a desired speed and time.
   *
   * @param speed The speed which the robot will drive. positive to turn CW or right, negative to turn CCW or left
   * @param time How much time to drive in seconds
   * @param drive The drivetrain subsystem on which this command will run
   */
  public DriveTimed(ChassisDrivetrainSubsystem drive, double speed, double time) {
    m_speed = speed;
    m_duration = time * 1000;
    m_drive = drive;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println(MessageFormat.format("Started {0}", this.getName()));
    m_startTime = System.currentTimeMillis();
    m_drive.MyDrive_Tank(0, 0, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.MyDrive_Tank(m_speed, m_speed, 1, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println(MessageFormat.format("Ended {0}", this.getName()));
    m_drive.MyDrive_Tank(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
