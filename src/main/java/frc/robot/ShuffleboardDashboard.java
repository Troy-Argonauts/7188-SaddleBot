/*----------------------------------------------------------------------------*/
/* This code based on "Console" from FRC                                      */
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/**
 * Author: BigHeartRobotics 
 * Date Created: 04/23/2022
 * Last Edited: 04/23/2022 BHR
 * Routine Summary:  This Routine builds the Shuffleboard Dashboard (DB) for use with the Drivers Station (DS).  
 * Customize as needed to support robot functions durin competition
 * Use debug tab for program troubleshooting
 */

//Reference:  https://firstmncsa.org/2018/12/15/debugging-shuffleboard/

//The import section holds all the imports of various libraries that are required for use in the current class.
package frc.robot;

import java.text.MessageFormat; //supports System messages

//import frc.robot.subsystems.ChassisDrivetrainSubsystem;


//import edu.wpi.first.wpilibj.shuffleboard.*; //.* imports all, no need to list individually
//import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

//import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.networktables.TableEntryListener;
//import edu.wpi.first.networktables.EntryListenerFlags;

//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; //SmartDashboard & Shuffleboard SIM connection IP 127.0.0.1 , else use team number 7188 under File>Prefrences>Team/Host

//import java.util.Map;


// Class that wrappers all of the interaction with the Shuffleboard

// All decisions about content and layout of the Shuffleboard are consolidated in this file
// to make it easier to change things rather than having to look throughout all of the 
// classes or routines for subsystems, commands, etc.
// The Console class knows about the subsystems, commands, etc.  but generally not vice versa

//Class Declaration
//In every Java program the class needs to be declared
public class ShuffleboardDashboard {


//Declaring Objects
//This section declares objects that are required for use later in the class.
    // Tabs
    private ShuffleboardTab mainTab;
    //private ShuffleboardTab driveTab;
    //private ShuffleboardTab subsystemsTab;
    //private ShuffleboardTab debugTab;

    //private static NetworkTable table = NetworkTableInstance.getDefault().getTable("SmartDashboard");
    // Updatable entries, either by user or under program control
    //private NetworkTableEntry driver_matchTime;
    private NetworkTableEntry drive_targetDistanceEntry;
    public static NetworkTableEntry Chassis_Gyro_Z;

  // Build up the driver's Dashboard   
    public ShuffleboardDashboard() {

    }

    public void initialize() {
        System.out.println(MessageFormat.format("Initialize ", "Shuffleboard Dashboard"));
      
    //Build the Tabs.....add Tabs as needed to facilitate tank drive & function of the drive team during competition
        setupSmartdashboard();

        setupMainTab(); // Main Drive Team panel
        RobotContainer.m_ChassisDrivetrainSubsystem.shuffleboard();
        //setupDriveTab();
        //setupSubsystemTab();
        //setupDebugTab(); //for debug use
    }

    public void update() {
        // Once built, tabs only require update of their data or information
        //updateMainTab();
        //updateDriveTab();
        //updateSubsystemTab();
        //updateDebugTab();
    }

    private void setupMainTab() {

        mainTab = Shuffleboard.getTab("Main");

        // Match time
        //driver_matchTime = mainTab.add("Match Time",0.0)
        //    .withPosition(2,0)
        //    .withWidget(BuiltInWidgets.kDial)
        //    .withProperties(Map.of("min", -1, "max", 135))  // this property setting isn't working
        //    .getEntry();

        // Autonomous Command Chooser. used to define routine that will run during autonomous mode
        mainTab.add("Autonomous Chooser", RobotContainer.m_AutonomousCommandChooser).withWidget(BuiltInWidgets.kComboBoxChooser)
            .withSize(2,1)
            .withPosition(0,0);
    }

    //private void setupSubsystemTab() {

    //    subsystemsTab = Shuffleboard.getTab("Subsystems");

    //}

    
    //private void setupDriveTab() {

    //    driveTab = Shuffleboard.getTab("Drive");


     //   driveTab.add("Drive Subsystem",RobotContainer.m_ChassisDrivetrainSubsystem)
     //       .withSize(2,1)
     //       .withPosition(0,0);

        // Target Distance for Drive Distance command
        //drive_targetDistanceEntry = driveTab.add("Target Distance",2.0)
        //        .withPosition(0,1)
         //       .withWidget(BuiltInWidgets.kTextView)
         //       .getEntry();

        //driveTab.add("Drive Distance", new DriveDistanceCmd())
        //    .withSize(2,1)
        //    .withPosition(0,2);

    // The drive tab is roughly 9 x 5 (columns x rows)
    // Camera can be 4 x 4, gyro 
    // driveTab.add("Cargo Cam", new HttpCamera("Cargo Photon", "http://10.50.24.11:5800"))
    //                                         .withWidget(BuiltInWidgets.kCameraStream).withPosition(0, 0)
    //                                         .withSize(4, 4);
    // Add heading and outputs to the driver views
    //driveTab.add("Gyro", m_ChassisDrivetrainSubsystem.getGyro()).withPosition(4, 0).withWidget(BuiltInWidgets.kGyro);
   
    //driveTab.add("Left Output", 0).withSize(1, 1).withPosition(2, 0).withWidget(BuiltInWidgets.kDial)
    //                              .withProperties(Map.of("Min", -1, "Max", 1))
     //                             .getEntry();
    //driveTab.add("Right Output", 0).withSize(1, 1).withPosition(3, 0).withWidget(BuiltInWidgets.kDial)
    //                              .withProperties(Map.of("Min", -1, "Max", 1));
//
    // Shuffleboard.getTab("Combined Test").add(new TestIntakeIndexerAndShooter(m_indexer, m_intake, m_shooter)).withPosition(0, 1).withSize(2, 1);
    // Shuffleboard.getTab("Combined Test").add(new SetForwardLimit(m_intake)).withPosition(0, 3).withSize(2, 1);
    // Shuffleboard.getTab("Arm MM Testing").add("ReSet Intake Arm", new SetArm(m_intake)).withPosition(0, 3).withSize(2, 1);
    // Shuffleboard.getTab("Intake").add("Extend Intake", new ArmMM(m_intake, Intake.INTAKE_ARM_EXTEND)).withPosition(0, 2).withSize(2,1);
    // Shuffleboard.getTab("Intake").add("Retract Intake", new ArmMM(m_intake, Intake.INTAKE_ARM_RETRACT)).withPosition(2,2).withSize(2,1);
    // Shuffleboard.getTab("Arm MM Testing").add(new ResetIntakeArmEncoder(m_intake)).withPosition(0, 2).withSize(2, 1);
    // Shuffleboard.getTab("ShooterPID").add("Shoot" , new ShooterPIDTuning(m_shooter, m_indexer)).withPosition(0, 3);
    // Shuffleboard.getTab("Turn MM Testing").add("Turn MM", new TurnToAngle(m_driveTrain, 0)).withPosition(0, 3).withSize(2, 1);
    // Shuffleboard.getTab("Intake").add(new CollectBalls(m_intake, m_indexer)).withPosition(0, 1).withSize(2, 1);
    // Shuffleboard.getTab("Intake").add(new DropIntakeAndCollectBalls(m_intake, m_indexer)).withPosition(2, 1).withSize(2, 1);
    // Shuffleboard.getTab("Intake").add(new EjectBalls(m_indexer, m_shooter)).withPosition(0, 3).withSize(2, 1);
                                      

    //}


    /**
     * Setting up a Custom Debug Tab
     * The primary users for Shuffleboard are the robot drivers.  
     * At the beginning of a match, the drivers will fire up their driver station and they will want to see only the widgets that assist them.  
     * Programmers are secondary users of Shuffleboard, so we shouldn’t clutter up the main Shuffleboard screen with our diagnostic widgets.  
     * For this reason, we may shift our widgets off to secondary tabs, or we may configure them to go away when we aren’t debugging.
     */
    //private void setupDebugTab() {
    //    debugTab = Shuffleboard.getTab("Debug");
    //}


    // This is for stuff that can't be displayed easily in custom Shuffleboard tabs
    // Will end up on the SmartDashboard tab
    private void setupSmartdashboard() {

        // SmartDashboard.putData("Command Scheduler",Scheduler.getInstance());

    }

    //private void updateMainTab() {

    //    double matchTime = DriverStation.getMatchTime();
     //   driver_matchTime.setDouble(matchTime);

    //}

   // private void updateDriveTab() {
        //double matchTime = DriverStation.getAngleZ();
        //Chassis_Gyro_Z.setDouble(matchTime); ??

   // }    
    
    //private void updateSubsystemTab() {

    //}

    //private void updateDebugTab() {

    //}

    // -------------------
    // Access Methods
    // -------------------
    public double getTargetDriveDistance() {
        return drive_targetDistanceEntry.getDouble(1.0);
    }
}

// Refrence Junk
    //Can be used to send data to Labview based "Dashboard" https://docs.wpilib.org/en/stable/docs/software/dashboards/labview-dashboard/using-the-labview-dashboard-with-c%2B%2B-java-code.html
    //SmartDashboard.putNumber("Gyro", m_ChassisDrivetrainSubsystem.getHeading()); //Sending to the “Gyro” NetworkTables entry will populate the gyro
    /**
     * There are four outputs that show the motor power to the drivetrain. 
     * This is configured for 2 motors per side and a tank style drivetrain.
     * This is done by setting “RobotDrive Motors” like the example below.
     */
    //SmartDashboard.putNumberArray("RobotDrive Motors", {m_ChassisDrivetrainSubsystem.getLeftFront(), m_ChassisDrivetrainSubsystem.getRightFront(), m_ChassisDrivetrainSubsystem.getLeftBack(), drivetrain.getRightBack()});
      
    //SmartDashboard.putData("Forward", new DriveDistance(m_ChassisDrivetrainSubsystem, 12, 0.5));
    //SmartDashboard.putData("Turn90", new DriveTurnDegrees(m_ChassisDrivetrainSubsystem, 90, .45));
    //SmartDashboard.putData("TurnN90", new DriveTurnDegrees(m_ChassisDrivetrainSubsystem, -90, .45));
    //SmartDashboard.putData("Labyrinth", new Labyrinth(m_ChassisDrivetrainSubsystem));