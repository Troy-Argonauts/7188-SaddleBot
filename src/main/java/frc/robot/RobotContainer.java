// 
// programming resources
// https://team4159.github.io/main/general_programming/intro

package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.autonomous.*;
import frc.robot.commandgroups.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; //SmartDashboard & Shuffleboard SIM connection IP 127.0.0.1 , else use team number 7188 under File>Prefrences>Team/Host
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.Joystick; //for Flight type Joysticks
import edu.wpi.first.wpilibj.XboxController; //works for the Xbox 360, Xbox One, or Logitech F310 (in X Input mode).
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
//import edu.wpi.first.wpilibj2.command.button.Button; //OLD Method
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import java.util.Map;

//import frc.robot
import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */



@SuppressWarnings("unused")
public class RobotContainer {
  // Subsystems...
  // The robot's subsystems are defined here...
  public static final ChassisDrivetrainSubsystem m_ChassisDrivetrainSubsystem = new ChassisDrivetrainSubsystem();
  public static final ArmSubsystem m_ArmSubsystem = new ArmSubsystem();
  public static final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  
  
  // Joysticks are defined here...
  //Driver - Main control & drive of the Chassis
  public static final XboxController m_xboxController_0_Driver = new XboxController(0);// 0 is the USB Port to be used as indicated on the Driver Station
  //Operator - All peripheral robot functions
  public static final XboxController m_xboxController_1_Operator = new XboxController(1);// 1 is the USB Port to be used as indicated on the Driver Station

 
  //Autonomous Command Chooser
  public static final SendableChooser<Command> m_AutonomousCommandChooser = new SendableChooser<>();;

  public static ShuffleboardDashboard m_Dashboard = new ShuffleboardDashboard();  

  //Commands
  // The robot's Commands are defined here...
  private static final SCG_DriveBreakin DriveBreakin = new SCG_DriveBreakin(m_ChassisDrivetrainSubsystem); // 
  private static final DriveTimed forwardTimed = new DriveTimed(m_ChassisDrivetrainSubsystem, 1.0, 5); // speed 0..1, time in seconds
  private static final DriveDistance forwardDistanceGyro = new DriveDistance(m_ChassisDrivetrainSubsystem, 37, 0.5); // speed 0..1, time in seconds
  //private static final DriveDistance forward12in = new DriveDistance(m_ChassisDrivetrainSubsystem, 12, 0.5); // Distance in inches, FWD speed 0..1 
  //private static final DriveDistance backward12in = new DriveDistance(m_ChassisDrivetrainSubsystem, 12, -0.5); // Distance in inches, REV speed -0..-1 
  private static final DriveTurnTimed turnTimed = new DriveTurnTimed(m_ChassisDrivetrainSubsystem, 0.75, 0.5); // speed 0..1, time in seconds
  //private static final DriveTurnDegrees turn90degrees = new DriveTurnDegrees(m_ChassisDrivetrainSubsystem, 90, .45); // Degrees 0..360 +CW..-CCW, speed 0..1 +FWD..-REV
  //private static final DriveTurnDegreesGyro turn90Gyro = new DriveTurnDegreesGyro(m_ChassisDrivetrainSubsystem, 90, .45); // Degrees 0..360 +CW..-CCW, speed 0..1 +FWD..-REV
  //private final DriveTurnDegrees right90degrees = new DriveTurnDegrees(m_ChassisDrivetrainSubsystem, 90, .5);
  //private final DriveTurnDegrees left90degrees = new DriveTurnDegrees(m_ChassisDrivetrainSubsystem, -90, .5);
  //private final DriveASquare squareDrive = new DriveASquare(m_ChassisDrivetrainSubsystem);
  //private final DriveTurnDegreesGyro gyroTurnRight = new DriveTurnDegreesGyro(m_ChassisDrivetrainSubsystem, 90, .25);
  //private final DriveTurnDegreesGyro gyroTurnLeft = new DriveTurnDegreesGyro(m_ChassisDrivetrainSubsystem, -90, .25);
  //private final DriveTurnDegreesGyroPID gyroPidTurnRightOnce = new DriveTurnDegreesGyroPID(m_ChassisDrivetrainSubsystem, 90, .7, false);
  //private final DriveTurnDegreesGyroPID gyroPidTurnLeftOnce = new DriveTurnDegreesGyroPID(m_ChassisDrivetrainSubsystem, -90); // Degrees 0..360 +CW..-CCW, speed 0..1 +FWD..-REV
  //private final DriveTurnDegreesGyroPID gyroPidTurnRightCont = new DriveTurnDegreesGyroPID(m_ChassisDrivetrainSubsystem, 90, .5, true);
  //private final DriveDistanceGyroStraightPID driveForwardInchesGyro = new DriveDistanceGyroStraightPID(m_ChassisDrivetrainSubsystem, 12, 0.5);//distance / speed +fwd -rev
  //private final DriveTurnNetworkTableAnglePID driveTurnNetworkTable;

  //Arm Commands
  private static final Arm_Lower arm_Lower = new Arm_Lower(m_ArmSubsystem, -ArmConstants.ARM_OUTPUT_POWER,ArmConstants.ARM_CURRENT_LIMIT_A );  
  private static final Arm_Raise arm_Raise = new Arm_Raise(m_ArmSubsystem, ArmConstants.ARM_OUTPUT_POWER,ArmConstants.ARM_CURRENT_LIMIT_A );
  private static final Arm_Hold arm_Hold = new Arm_Hold(m_ArmSubsystem, ArmConstants.ARM_HOLD_POWER ,ArmConstants.ARM_HOLD_CURRENT_LIMIT_A );
  private static final Arm_Off arm_Off = new Arm_Off(m_ArmSubsystem, 0,0 );

  //Intake Commands
  private static final Intake_ConeInCubeOut Intk_ConeInCubeOut = new Intake_ConeInCubeOut(m_IntakeSubsystem, -IntakeConstants.INTAKE_OUTPUT_POWER,IntakeConstants.INTAKE_CURRENT_LIMIT_A );  
  private static final Intake_CubeInConeOut Intk_CubeInConeOut = new Intake_CubeInConeOut(m_IntakeSubsystem, IntakeConstants.INTAKE_OUTPUT_POWER,IntakeConstants.INTAKE_CURRENT_LIMIT_A );
  private static final Intake_Hold Intk_Hold = new Intake_Hold(m_IntakeSubsystem, IntakeConstants.INTAKE_HOLD_POWER ,IntakeConstants.INTAKE_HOLD_CURRENT_LIMIT_A );
  private static final Intake_Off Intk_Off = new Intake_Off(m_IntakeSubsystem, 0,0 );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();



    // Set up the initial configuration of the Dashboard in Shuffleboard
    m_Dashboard.initialize();
  
    // Default command 
    // is tank drive. This will run unless another command is scheduled over it.
    //m_ChassisDrivetrainSubsystem.setDefaultCommand(new DriveTank(m_ChassisDrivetrainSubsystem, m_xboxController_0_Driver));
    m_ChassisDrivetrainSubsystem.setDefaultCommand(new DriveArcade(m_ChassisDrivetrainSubsystem, m_xboxController_0_Driver));
   


    //Create Autonomous Command Selection dropdown box for selecting command during "autonomous period"
    AutonomousCommandChooser();
 
  }

  private void AutonomousCommandChooser(){
    //Creates a dropdown box in the Driver Station with Autonomous Command options to run during the autonomous period. See Commands for execution of chosen auto mode.

    // Setup auto chooser options, SmartDashboard / Shuffleboard
    m_AutonomousCommandChooser.setDefaultOption("ComplexAuto_1", new ComplexAuto_1(m_ChassisDrivetrainSubsystem));
    m_AutonomousCommandChooser.addOption("AutoCommand1", new AutoCommand1());
    m_AutonomousCommandChooser.addOption("AutoCommand2", new AutoCommand2());
    m_AutonomousCommandChooser.addOption("DriveThreeSeconds", new DriveThreeSeconds(m_ChassisDrivetrainSubsystem, 1, .5)); // speed 0..1, time in seconds
        //m_AutonomousCommandChooser.addOption("Left Tarmac, 2 ball", new twoBallLeft(m_driveTrain, m_shooter, m_intake, m_indexer));
        //m_AutonomousCommandChooser.addOption("Right Tarmac, 2 ball", new twoBallRight(m_driveTrain, m_shooter, m_intake, m_indexer, m_ledsubsystem));
        //m_AutonomousCommandChooser.setDefaultOption("Left Tarmac, 2 ball", new twoBallLeft(m_driveTrain, m_shooter, m_intake, m_indexer, m_ledsubsystem));
        //m_AutonomousCommandChooser.addOption("Right Tarmac, 3 ball", new ThreeBall(m_driveTrain, m_shooter, m_intake, m_indexer, m_ledsubsystem));

        //m_AutonomousCommandChooser.addOption("PathA_Red", new PathARedAuto(DRIVE_SUBSYSTEM, MAGAZINE_SUBSYSTEM));
        //m_AutonomousCommandChooser.addOption("PathA_Blue", new PathABlueAuto(DRIVE_SUBSYSTEM, MAGAZINE_SUBSYSTEM));
        //m_AutonomousCommandChooser.addOption("PathB_Red", new PathBRedAuto(DRIVE_SUBSYSTEM, MAGAZINE_SUBSYSTEM));
        //m_AutonomousCommandChooser.addOption("PathB_Blue", new PathBBlueAuto(DRIVE_SUBSYSTEM, MAGAZINE_SUBSYSTEM));
    
        //m_AutonomousCommandChooser.addOption("TestAuto", new TestAuto(DRIVE_SUBSYSTEM));
    
    //SmartDashboard.putData(m_AutonomousCommandChooser);
    //SmartDashboard.putData("Auto mode", m_AutonomousCommandChooser);
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Example of how to Joystick controller buttons
    //Logitech F310 gamepad (Xbox 360) controller button bindings
    JoystickButton js0_buttonA = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kA.value);
    JoystickButton js0_buttonB = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kB.value);
    JoystickButton js0_buttonX = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kX.value);
    JoystickButton js0_buttonY = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kY.value);
    JoystickButton js0_buttonLB = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kLeftBumper.value);
    JoystickButton js0_buttonRB = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kRightBumper.value);
    JoystickButton js0_buttonBack = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kBack.value);
    JoystickButton js0_buttonStart = new JoystickButton(m_xboxController_0_Driver, XboxController.Button.kStart.value);

    JoystickButton js1_buttonA = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kA.value);
    JoystickButton js1_buttonB = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kB.value);
    JoystickButton js1_buttonX = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kX.value);
    JoystickButton js1_buttonY = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kY.value);
    JoystickButton js1_buttonLB = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kLeftBumper.value);
    JoystickButton js1_buttonRB = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kRightBumper.value);
    JoystickButton js1_buttonBack = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kBack.value);
    JoystickButton js1_buttonStart = new JoystickButton(m_xboxController_1_Operator, XboxController.Button.kStart.value);


    //whenActive/whenPressed
    //whileActiveContinuous/whileHeld
    //whileActiveOnce/whenHeld
    //whenInactive/whenReleased
    //toggleWhenActive/toggleWhenPressed
    //cancelWhenActive/cancelWhenPressed
    //https://docs.wpilib.org/en/2020/docs/software/commandbased/binding-commands-to-triggers.html

    /**
     * onTrue / onFalse
     * whileTrue / whileFalse
     * toggleOnTrue / toggleOnFalse
     * Composing Triggers..and, or, negate
     * debounce
     * https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
     * 
     * onTrue
     * This binding schedules a command when a trigger changes from false to true (or, accordingly, when a button changes is 
     * initially pressed). The command will be scheduled on the iteration when the state changes, and will not be scheduled 
     * again unless the trigger becomes false and then true again (or the button is released and then re-pressed).
     * 
     * onFalse
     * The onFalse binding is identical, only that it schedules on false instead of on true.
     *
     * whileTrue
     * This binding schedules a command when a trigger changes from false to true (or, accordingly, when a button is initially pressed)
     * and cancels it when the trigger becomes false again (or the button is released). The command will not be re-scheduled if it finishes
     * while the trigger is still true. For the command to restart if it finishes while the trigger is true, wrap the command in a 
     * RepeatCommand, or use a RunCommand instead of an InstantCommand.
     * 
     * whileFalse
     * The whileFalse binding is identical, only that it schedules on false and cancels on true.
     *
     * toggleOnTrue
     * This binding toggles a command, scheduling it when a trigger changes from false to true (or a button is initially pressed),
     * and canceling it under the same condition if the command is currently running. Note that while this functionality is supported,
     * toggles are not a highly-recommended option for user control, as they require the driver to keep track of the robot state. 
     * The preferred method is to use two buttons; one to turn on and another to turn off. Using a StartEndCommand or a ConditionalCommand
     * is a good way to specify the commands that you want to be want to be toggled between.
     * 
     * toggleOnFalse
     * The toggleOnFalse binding is identical, only that it toggles on false instead of on true.
     * 
     * Composing Triggers..and, or, negate
     * The Trigger class can be composed to create composite triggers through the and(), or(), and negate() methods
     *   Binds an ExampleCommand to be scheduled when both the 'X' and 'Y' buttons of the driver gamepad are pressed 
     *      exampleCommandController.x()
     *      .and(exampleCommandController.y())
     *      .onTrue(new ExampleCommand());
     * 
     * debounce
     * debounces exampleButton with a 0.1s debounce time, rising edges only
     * exampleButton.debounce(0.1).onTrue(new ExampleCommand());
     * debounces exampleButton with a 0.1s debounce time, both rising and falling edges
     * exampleButton.debounce(0.1, Debouncer.DebounceType.kBoth).onTrue(new ExampleCommand());
     * 
     */



     js0_buttonA// js0 is xboxController_0_Driver joystick
      .onTrue(forwardDistanceGyro);
      SmartDashboard.putData("Xbox Button 0A",forwardDistanceGyro);

    js0_buttonB// js0 is xboxController_0_Driver joystick
    .onTrue(DriveBreakin);
    SmartDashboard.putData("Xbox Button 0B",DriveBreakin);

    //js0_buttonB
   

      //.whileHeld(new PrintCommand("JS 0 Button B While Held"))
      //.whenHeld(new PrintCommand("JS 0 Button B When Held"))
      //.whenReleased(new PrintCommand("JS 0 Button B Released"));
    //js0_buttonX
    //.onTrue(ChassisDriveConstants.gSlowMotionEnable=1);

    //js0_buttonX.whenPressed(gyroPidTurnLeftOnce);
    //js0_buttonY.whenPressed(turnTimed);
    //js0_buttonY.whenPressed(turn90degrees);
    //js0_buttonLB.whenPressed(turn90Gyro);
    //js0_buttonLB.whenPressed(gyroPidTurnRightCont);
   // js0_buttonRB.whenPressed(gyroPidTurnLeftOnce);
    //js0_buttonBack.whenPressed();
    //js0_buttonStart.whenPressed(driveForwardInchesGyro);       
  
    // Example of how to Joystick controller POV buttons
    //Logitech F310 gamepad (Xbox 360) controller button bindings
    //POVButton js0_POV_1 = new POVButton(m_xboxController_0_Driver, -1);
    POVButton js0_POV_0 = new POVButton(m_xboxController_0_Driver, 0);
    POVButton js0_POV_45 = new POVButton(m_xboxController_0_Driver, 45);
    POVButton js0_POV_90 = new POVButton(m_xboxController_0_Driver, 90);
    POVButton js0_POV_135 = new POVButton(m_xboxController_0_Driver, 135);
    POVButton js0_POV_180 = new POVButton(m_xboxController_0_Driver, 180);
    POVButton js0_POV_225 = new POVButton(m_xboxController_0_Driver, 225);
    POVButton js0_POV_270 = new POVButton(m_xboxController_0_Driver, 270);
    POVButton js0_POV_315 = new POVButton(m_xboxController_0_Driver, 315);

    //js0_POV_0
      //.whenPressed(new PrintCommand("JS 0 POV 0 Pressed"))
      //.whenReleased(new PrintCommand("JS 0 POV 0 Released"));
    //js0_POV_180
    //  .whenPressed(new PrintCommand("JS 0 POV 180 Pressed"))
    //  .whenReleased(new PrintCommand("JS 0 POV 180 Released"));
    
    js1_buttonLB// js1 is xboxController_1_Operator joystick - button Y cube in or cone out
    .onTrue(arm_Lower);
    SmartDashboard.putData("Xbox Button 1LB",arm_Lower);

    js1_buttonLB
    .onFalse(arm_Off);
    SmartDashboard.putData("Xbox Button 1LB",arm_Off);


    js1_buttonRB// js1 is xboxController_1_Operator joystick - button A cone in or cube out
    .onTrue(arm_Raise);
    SmartDashboard.putData("Xbox Button 1RB",arm_Raise);

    js1_buttonRB
    .onFalse(arm_Hold);
    SmartDashboard.putData("Xbox Button 1RB",arm_Hold);
    
    
    
    
    js1_buttonY// js1 is xboxController_1_Operator joystick - button Y cube in or cone out
    .onTrue(Intk_CubeInConeOut);
    SmartDashboard.putData("Xbox Button 1Y",Intk_CubeInConeOut);

    js1_buttonY
    .onFalse(Intk_Off);
    SmartDashboard.putData("Xbox Button 1Y",Intk_Off);


    js1_buttonA// js1 is xboxController_1_Operator joystick - button A cone in or cube out
    .onTrue(Intk_ConeInCubeOut);
    SmartDashboard.putData("Xbox Button 1A",Intk_ConeInCubeOut);

    js1_buttonA
    .onFalse(Intk_Off);
    SmartDashboard.putData("Xbox Button 1A",Intk_Off);

  }
  // Assumes a Logitech F310 gamepad plugged into channnel 0
  public XboxController getXboxController0() {
    return m_xboxController_0_Driver;
  }
  // Assumes a Logitech F310 gamepad plugged into channnel 1
  public XboxController getXboxController1() {
    return m_xboxController_1_Operator;
  }

    
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_AutonomousCommandChooser.getSelected();
  }

}// End RobotContainer
