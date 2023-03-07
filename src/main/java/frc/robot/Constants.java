// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import com.fasterxml.jackson.annotation.JacksonInject.Value;
//import com.revrobotics.CANSparkMax.IdleMode;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 * 
 * All Constants to be identified by begining with a small "k"
 */
public final class Constants {

    public static final class ChassisDriveConstants {

    //global variable
    public static int gDriveArcadeEnable = 1;
    public static int gTurboEnable = 0;
    public static int gSlowMotionEnable = 0;

    ///////////// Chassis Drive Train Specific Values & Calculations /////////////
    public static final int kLeftFrontMotorPort = 1;
    public static final boolean kLeftFrontMotorInvert = false;
    public static final int kLeftRearMotorPort = 2;
    public static final boolean kLeftRearMotorInvert = false;
    public static final int kRightFrontMotorPort = 3;
    public static final boolean kRightFrontMotorInvert = true;
    public static final int kRightRearMotorPort = 4;
    public static final boolean kRightRearMotorInvert  = true;

  // Joysticks are defined here...
    /**
   * The code uses the most generic joystick class.
   * 
   * The code uses a logitech XBoxController class with the gamepad set to XInput
   * mode (switch set to X on the bottom) 
   */
  //Driver - Main control & drive of the Chassis
    //Tank Drive
    public static final int kDriver_Right_Axis = 5; //Use Data from RawAxis #
    public static final int kDriver_Left_Axis = 1; //Use Data from RawAxis #
    //joystick sensitivity (gain) adjustment a
    public static final double k_a_R_Axis = 0.5; //valid range 0 to 1, where 0=no adjustment, 1=full adjustment
    public static final double k_a_L_Axis = 0.5; //valid range 0 to 1, where 0=no adjustment, 1=full adjustment

    //Arcade Drive
    // A split-stick arcade command, with forward/backward controlled by the left
    // hand, and turning controlled by the right.
    public static final int kDriver_Arcade_X_Axis = 4; //Use Data from RawAxis # (Left - Right)
    public static final int kDriver_Arcade_Y_Axis = 1; //Use Data from RawAxis # (FWD - Back)

    //joystick sensitivity (gain) adjustment a
    public static final double k_a_Y_Axis = 0.5; //valid range 0 to 1, where 0=no adjustment, 1=full adjustment
    public static final double k_a_X_Axis = 0.75; //valid range 0 to 1, where 0=no adjustment, 1=full adjustment
    
 /**
   * Encoders
   * Chassis: Andymark AM14U4
   * Toughbox Gear Ratio 10.7:1
   * Wheel Diameter = 6" diameter
   * Encoder: am-3132
   * 
   * Since the Encoder is attached directly to the Output shaft, the 10.7:1 gearbox can be ignored.
   * distance traveled per wheel revolution= pi x d = 3.14 x 6 inches = 18.84
   * 360 counts per revolution = 1 wheel revolution = 18.84 inches traveled
   * 
   * Distance per pulse = 18.84" / 360 pulse = 0.05233333333333333333333333333333
   */

    public static final int kLeftEncoderA = 0; 
    public static final int kLeftEncoderB = 1;
    public static final int kRightEncoderA = 2;
    public static final int kRightEncoderB = 3;
    public static final double kWheelDiameter = 6;// expressed in inches 
    public static final double kPulsesPerRev = 434; // was 360,   12 counts per motor revolution, Gear box ratio = 120:1.  Encoder pulse count per 1 wheel rotation = 12 counts * 120 mtr rev.
    public static final double kInchesPerPulse = (Math.PI * kWheelDiameter) / kPulsesPerRev;

    public static final double kWheelTrack = 16.795; //Wheel track is the distance measured across an axle from the centre line of one tyre tread to the centre line of the opposite tyre tread
    
    // Calculate the inches of the Robot wheel track circumference to travel based on percent degrees of a circle.
    // For example turning the Robot 90 degrees is Rbot Wheel track CIRCUMFERENCE(PI*Wheeltrack diameter) * .25 (1/4th of a circle).
    public static final double kInchesPerDegree = (Math.PI * kWheelTrack) / 360;

    
   

 /**
	 * PID Gains may have to be adjusted based on the responsiveness of control loop.
     * kF: 1023 represents output value to Talon at 100%, 6800 represents Velocity units at 100% output
     * Not all set of Gains are used in this project and may be removed as desired.
     * k=Constant, P=Proportional, I=Integral, D=Derivative, F, Period The period between controller updates in seconds. Must be non-zero and positive(ROBOT LOOP PERIOD).
     * Tolerance the @ position +-tolerance
     * Start with Zeigler-Nichols tuning method values
	 * 	                                    			  		kP      kI      kD      kF      Tolerance   RateTolerance    */
	public final static Gains kGains_Turning =   	  new Gains(0.100,  0.00,   0.01,   0.00,   1.0,        0.00        ); //Gyro
	public final static Gains kGains_Driving =   	  new Gains(0.100,  0.00,   0.05,   0.00,   0.0,        0.00        ); //Gyro
    }

    public static final class ArmConstants {
      ///////////// Arm Drive Specific Values & Calculations /////////////
      public static final int kArmMotorPort = 7;
      public static final boolean kArmMotorInvert = true;
      //public static final int kArmMotorIdleMode = 1; // 0 - Coast, 1 - Brake
     
     // Joysticks are defined here...
     //Driver - Main control & drive of the Chassis
     public static final int kOperatorButton_ArmLift = 5; //Use Data from Button RB
     public static final int kOperatorButton_ArmLower = 4; //Use Data from Button LB
     
    
     //How many amps the arm can use while picking up
     static final int ARM_CURRENT_LIMIT_A = 20;
  
     //How many amps the arm can use while holding
     static final int ARM_HOLD_CURRENT_LIMIT_A = 5;
  
     //Percent output for arm
     static final double ARM_OUTPUT_POWER = 0.4;
  
     //Percent output for holding
     static final double ARM_HOLD_POWER = 0.07;
    }


  public static final class IntakeConstants {
    ///////////// Intake Drive Specific Values & Calculations /////////////
    public static final int kIntaketMotorPort = 6;
    public static final boolean kIntakeMotorInvert = false;
    //public static final int kIntakeMotorIdleMode = 1; // 0 - Coast, 1 - Brake
   
   // Joysticks are defined here...
   //Driver - Main control & drive of the Chassis
   public static final int kOperatorButton_IntakeCone = 3; //Use Data from Button Y
   public static final int kOperatorButton_IntakeCube = 0; //Use Data from Button A
   
  
   //How many amps the intake can use while picking up
   static final int INTAKE_CURRENT_LIMIT_A = 25;

   //How many amps the intake can use while holding
   static final int INTAKE_HOLD_CURRENT_LIMIT_A = 5;

   //Percent output for intaking
   static final double INTAKE_OUTPUT_POWER = 1.0;

   //Percent output for holding
   static final double INTAKE_HOLD_POWER = 0.07;
  }

  public static final class GyroConstants {
    
  public static final boolean kGyroReversed = false;
  }
}
