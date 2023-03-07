// Default command 
// is tank drive. This will run unless another command is scheduled over it.

package frc.robot.commands;

import static frc.robot.Constants.*;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Intake_CubeInConeOut extends CommandBase {
  private final IntakeSubsystem m_intakeMotor;
  private final double m_percent; // percent power 0 ~ 1, + or - sets direction
  private final int m_amps; //SmartCurrentLimit



  /**
   * Creates a new Tank Drive. This command will drive your robot according to the speed supplier
   * lambdas. This command does not terminate.
   *
   * @param intakeMotor The drive subsystem on which this command will run
   * @param percent percent power 0 ~ 1, + or - sets direction
   * @param amps SmartCurrentLimit
   */
  public Intake_CubeInConeOut(IntakeSubsystem intakeMotor, double percent, int amps) {
    m_intakeMotor = intakeMotor;  
    m_percent=percent;
    m_amps=amps;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intakeMotor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    m_intakeMotor.setIntakeMotor(m_percent, m_amps);

    //System.out.println(m_xaxisSpeedLH);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
