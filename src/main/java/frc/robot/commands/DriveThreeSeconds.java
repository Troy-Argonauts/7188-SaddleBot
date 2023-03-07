// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import java.text.MessageFormat;

public class DriveThreeSeconds extends CommandBase {
  /** Creates a new DriveThreeSeconds. */
  private final double m_duration;
  private final double m_speed;
  private final ChassisDrivetrainSubsystem m_drive;
  private long m_startTime;
  private Timer m_timer;
  /** Creates a new DriveThreeSeconds. */

  //Timer m_timer;
  //double currTime;

 

  public DriveThreeSeconds(ChassisDrivetrainSubsystem driveTrain, double speed, double time) {
    m_drive = driveTrain;
    m_speed = speed;
    m_duration = time * 1000;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
    System.out.println(MessageFormat.format("Started {0}", this.getName()));
    m_startTime = System.currentTimeMillis(); //alt timer implementation
    m_drive.MyDrive_Tank(0, 0, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_drive_train.tank_drive_straight(-0.5);
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
    //return m_timer.hasElapsed(m_duration);
  }
}
