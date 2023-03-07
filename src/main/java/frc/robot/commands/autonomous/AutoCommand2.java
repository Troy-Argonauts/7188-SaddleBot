/**----------------------------------------------------------------------------
 * Author: BigHeartRobotics 
 * Date: 04/23/2022
 * Routine Summary:  Autonomous Command Place Holder and Example.  
 * To be replaced with actual routine commands
 * 
 */
 
package frc.robot.commands.autonomous;

import java.text.MessageFormat;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCommand2 extends CommandBase {

  private int m_counter = 0;
  private int m_printNum = 0;

  public AutoCommand2() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    System.out.println(MessageFormat.format("Initialize {0}", this.getName()));
    m_counter = 0;
    m_printNum = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    ++m_counter;
    if (m_counter > 50) {
      m_counter = 0;
      ++m_printNum;
      System.out.println(MessageFormat.format("Execute {0}, Step {1}", this.getName(), m_printNum));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println(MessageFormat.format("End {0}", this.getName()));
  }

  // Returns true when the command should end.
  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }
}
