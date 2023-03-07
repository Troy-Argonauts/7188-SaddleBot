// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
//import frc.robot.ShuffleboardDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
//import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;

//import java.util.Map;


//import frc.robot.sensors.RomiGyro;
import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {

  // Set up the intake motor
  // The intake motor is a NEO 550
  //private final CANSparkMax  m_intakeMotor = new CANSparkMax(IntakeConstants.kIntaketMotorPort, MotorType.kBrushless);
  private final CANSparkMax  m_intakeMotor = new CANSparkMax(IntakeConstants.kIntaketMotorPort, MotorType.kBrushed);

  // Set up the intake motor controller
  //private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotorGroup, m_rightMotorGroup);

  //private final String SUBSYSTEM_NAME = "Intake Subsystem";
  /** Creates a new ChassisDrivetrain. */
  public IntakeSubsystem() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_intakeMotor.setInverted(IntakeConstants.kIntakeMotorInvert); //positive speed input = fwd
    m_intakeMotor.setIdleMode(IdleMode.kBrake); // coast, brake operation.  Set the intake to brake mode to help hold position.
   
  }



  /**
   * Create Shuffleboard tab for this subsystem and display values
   */
  public void shuffleboard() {
  //  ShuffleboardTab tab = Shuffleboard.getTab(SUBSYSTEM_NAME);
    

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
  }

  //Set the intake motor output power.
  /**
   * @param percent desired speed
   * @param amps current limit
   */
  public void setIntakeMotor(double percent, int amps) {
    m_intakeMotor.set(percent);
    m_intakeMotor.setSmartCurrentLimit(amps);

    SmartDashboard.putNumber("intake power (%)", percent);
    SmartDashboard.putNumber("intake motor current (amps)", m_intakeMotor.getOutputCurrent());
    SmartDashboard.putNumber("intake motor temperature (C)", m_intakeMotor.getMotorTemperature());
  }

  public void stop() {
    setIntakeMotor(0,0);
   }
  
}
