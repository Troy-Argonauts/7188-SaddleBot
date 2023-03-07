// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.text.MessageFormat;
import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;

//import java.util.Map;


//import frc.robot.sensors.RomiGyro;
import static frc.robot.Constants.*;

public class ChassisDrivetrainSubsystem extends SubsystemBase {


  // Set up the left and right motors
  private final Spark m_leftFrontMotor = new Spark(ChassisDriveConstants.kLeftFrontMotorPort);
  private final Spark m_leftRearMotor = new Spark(ChassisDriveConstants.kLeftRearMotorPort);
  private final MotorControllerGroup m_leftMotorGroup = new MotorControllerGroup(m_leftFrontMotor, m_leftRearMotor);

  private final Spark m_rightFrontMotor = new Spark(ChassisDriveConstants.kRightFrontMotorPort);
  private final Spark m_rightRearMotor = new Spark(ChassisDriveConstants.kRightRearMotorPort);
  private MotorControllerGroup m_rightMotorGroup = new MotorControllerGroup(m_rightFrontMotor, m_rightRearMotor);

  // Set up the differential drive controller
  //private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final DifferentialDrive m_diffDrive = new DifferentialDrive(m_leftMotorGroup, m_rightMotorGroup);

// Set up Encoders
  private final Encoder m_leftEncoder = new Encoder(ChassisDriveConstants.kLeftEncoderA, ChassisDriveConstants.kLeftEncoderB, true, EncodingType.k4X);
  //private final Encoder m_rightEncoder = new Encoder(ChassisDriveConstants.rightEncoderA, ChassisDriveConstants.rightEncoderB, false, EncodingType.k4X);

  // Set up the Gyro
  //private static final double kAngleSetpoint = 0.0;
	//private static final double kP = 0.005; // propotional turning constant
  //private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
  private final Gyro m_gyro = new ADXRS450_Gyro();
  private final boolean do_calibration = true; //if this is TRUE, do not move for 10 seconds until it is done!

  //private final String SUBSYSTEM_NAME = "Chassis Drivetrain Subsystem";
  /** Creates a new ChassisDrivetrain. */
  public ChassisDrivetrainSubsystem() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_leftFrontMotor.setInverted(ChassisDriveConstants.kLeftFrontMotorInvert); //positive speed input = fwd
    m_leftRearMotor.setInverted(ChassisDriveConstants.kLeftRearMotorInvert); //positive speed input = fwd    
    m_rightFrontMotor.setInverted(ChassisDriveConstants.kRightFrontMotorInvert); //positive speed input = fwd
    m_rightRearMotor.setInverted(ChassisDriveConstants.kRightRearMotorInvert ); //positive speed input = fwd
  
    m_diffDrive.setSafetyEnabled(true);
    m_diffDrive.setExpiration(0.1);
    m_diffDrive.setMaxOutput(1.0);


    // Use inches as unit for encoder distances
    m_leftEncoder.setDistancePerPulse(ChassisDriveConstants.kInchesPerPulse);
    //m_rightEncoder.setDistancePerPulse(ChassisDriveConstants.kInchesPerPulse);
    resetChassisMotorEncoders();

    if (do_calibration){
      //m_gyro.calibrate();
      initializeCalibrate();
    }

  }


  /**
   * Create Shuffleboard tab for this subsystem and display values
   */
  public void shuffleboard() {
    //ShuffleboardTab tab = Shuffleboard.getTab(SUBSYSTEM_NAME);
    

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
  }


  //public void arcadeDrive(double xaxisSpeed, double zaxisRotate, boolean squaredInputs) {
  //  m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate, squaredInputs);
  //}
   /**
   * Tank drive method for differential drive platform.
   *
   * @param xaxisSpeedLH      The robots left drivetrain speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param xaxisSpeedRH      The robots right drivetrain speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param squareInputs      If set, decreases the input sensitivity at low speeds (true..false).
   * @param SlowMotionSpeed   set a precentage of max speed the robot can use, if not
   *                          provided will default to full power
   */
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
  public void MyDrive_Tank(double xaxisSpeedLH, double xaxisSpeedRH, double SlowMotionSpeed, double zaxisRotateDegree) {
    m_diffDrive.tankDrive(xaxisSpeedLH * SlowMotionSpeed, xaxisSpeedRH * SlowMotionSpeed); 

    SmartDashboard.putNumber("Left Output", xaxisSpeedLH * SlowMotionSpeed);
    SmartDashboard.putNumber("Right Output", xaxisSpeedRH * SlowMotionSpeed);

    
  }
   /**
   * Arcade drive method for differential drive platform.
   * 
   * @param xaxisSpeed        robots drivetrain speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param SlowMotionSpeed   set a precentage of max speed the robot can use, if not
   *                          provided will default to full power
   * @param zaxisRotateDegree number of degrees to turn, (0..360)
   * @param zaxisSpeed        The robots drivetrain speed along the Z axis [-1.0..1.0]. use positive to turn CW or right, negative to turn CCW or left.
   */
  public void MyDrive_Arcade(double xaxisSpeed, double zaxisSpeed, double SlowMotionSpeed, double zaxisRotateDegree) {
    m_diffDrive.arcadeDrive(xaxisSpeed * SlowMotionSpeed, zaxisSpeed * SlowMotionSpeed);

    SmartDashboard.putNumber("X Axis Output", xaxisSpeed);
    SmartDashboard.putNumber("Z Axis Output", zaxisSpeed);

  }

  public void stop() {
    m_diffDrive.tankDrive(0, 0);
    m_diffDrive.arcadeDrive(0, 0);
  }
 

// Encoder Code
  public void resetChassisMotorEncoders() {
    m_leftEncoder.reset();
    //m_rightEncoder.reset();    
  }

  public int getLeftEncoderCount() {
    int LEC = m_leftEncoder.get();
   // Left_Encoder_Count.setDouble(LEC);// update Networktable value
   SmartDashboard.putNumber("Encoder ct", LEC);
    return LEC;
  }

 // public int getRightEncoderCount() {
 //   int REC = m_rightEncoder.get();
 //   Right_Encoder_Count.setDouble(REC);// update Networktable value
 //   return REC;
 // }

  public double getLeftDistanceInch() {
    double LED = m_leftEncoder.getDistance();
    //Left_Encoder_Distance.setDouble(LED);// update Networktable value
    SmartDashboard.putNumber("Encoder Dist", LED);
    return LED;
  }

 // public double getRightDistanceInch() {
 //   double RED = m_rightEncoder.getDistance();
 //   Right_Encoder_Distance.setDouble(RED);// update Networktable value
 //   return RED;
 // }

  public double getAverageDistanceInch() {
    //double AED = (Math.abs(getLeftDistanceInch()) + Math.abs(getRightDistanceInch())) / 2.0;
    double AED = (Math.abs(getLeftDistanceInch()) + Math.abs(getLeftDistanceInch())) / 2.0;
    //Average_Encoder_Distance.setDouble(AED);// update Networktable value
    SmartDashboard.putNumber("Av Encoder Dist", AED);
    return AED;
  }


  //Gyro code
  /**
   * Zeroes the heading of the robot.
   */
  public void initializeCalibrate() {

    System.out.println("Initiating calibration: DO NOT MOVE ROBOT!");
    m_gyro.calibrate(); 
    Timer.delay(10);
    System.out.println("Done Calibrating!!!");
    
    
  }


  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    System.out.println(MessageFormat.format("Gyro Reset", this.getName()));
    m_gyro.reset(); 
   }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from 180 to 180
   */
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (GyroConstants.kGyroReversed ? -1.0 : 1.0);
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_gyro.getRate() * (GyroConstants.kGyroReversed ? -1.0 : 1.0);
  }

}
