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

public class ArmSubsystem extends SubsystemBase {

  // Set up the arm motor controller
  // The arm motor is a CIM
  private final CANSparkMax  m_armMotor = new CANSparkMax(ArmConstants.kArmMotorPort, MotorType.kBrushed);


  //private final String SUBSYSTEM_NAME = "Arm Subsystem";
  /** Creates a new ChassisDrivetrain. */
  public ArmSubsystem() {
    // Set the arm output power. Positive is out, negative is in.
    m_armMotor.setInverted(ArmConstants.kArmMotorInvert); //positive speed input = out
    m_armMotor.setIdleMode(IdleMode.kBrake); // coast, brake operation.  Set the Arm to brake mode to help hold position.
   
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

  //Set the arm motor output power.
  /**
   * @param percent desired speed
   * @param amps current limit
   */
  public void setArmMotor(double percent, int amps) {
    m_armMotor.set(percent);
    m_armMotor.setSmartCurrentLimit(amps);

    SmartDashboard.putNumber("arm power (%)", percent);
    SmartDashboard.putNumber("arm motor current (amps)", m_armMotor.getOutputCurrent());
    SmartDashboard.putNumber("arm motor temperature (C)", m_armMotor.getMotorTemperature());
  }

  public void stop() {
    setArmMotor(0,0);
   }
  
}
