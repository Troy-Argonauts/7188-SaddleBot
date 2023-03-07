/*The command-based library supports four basic types of command groups: SequentialCommandGroup, 
ParallelCommandGroup, ParallelRaceGroup, and ParallelDeadlineGroup. Each of these command groups 
combines multiple commands into a composite command - however, they do so in different ways:
https://docs.wpilib.org/en/stable/docs/software/commandbased/command-groups.html
*/


/**
 * SequentialCommandGroup
 * A SequentialCommandGroup runs a list of commands in sequence 
 * - the first command will be executed, then the second, then the third, and so on until the list finishes. 
 * The sequential group finishes after the last command in the sequence finishes. 
 * It is therefore usually important to ensure that each command in the sequence does actually finish 
 * (if a given command does not finish, the next command will never start!).
 */

package frc.robot.commandgroups;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.*;
import frc.robot.subsystems.ChassisDrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class ComplexAuto_1 extends SequentialCommandGroup {

  /** Creates a new SquareDriveSequence. */
  public ComplexAuto_1(ChassisDrivetrainSubsystem drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    addCommands(new DriveDistance(drivetrain,6,.5));// forward
    addCommands(new DriveTurnDegrees(drivetrain,-90,.5));// turn left...use positive to turn right, negative to turn left
    addCommands (new DriveDistance(drivetrain, 13, 0.5).andThen(new WaitCommand(0.5))); // forward
    addCommands (new DriveTurnDegrees(drivetrain, 90, .45).andThen(new WaitCommand(0.5))); // turn right


  }
}
