// SequentialCommandGroup (SCG).

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.ChassisDrivetrainSubsystem;


/**
 *
 */
public class SCG_DriveBreakin extends SequentialCommandGroup {

    public SCG_DriveBreakin(ChassisDrivetrainSubsystem ChassisDrivetrainSubsystem){

    addCommands(
        // Add Commands here:
        // Also add parallel commands using the
        //
        // addCommands(
        //      new command1(argsN, subsystem),
        //      parallel(
        //          new command2(argsN, subsystem),
        //          new command3(argsN, subsystem)
        //      )    
        //  );

            //Drive Forward Full Speed (1) for 60 seconds
            new DriveTimed_Gyro(ChassisDrivetrainSubsystem, 1, 60),


            //Drive Stop Speed (0) for 30 seconds
            new DriveTimed_Gyro(ChassisDrivetrainSubsystem, 0, 30),

            //Turn Speed (0) for 60 seconds
            new DriveTurnTimed(ChassisDrivetrainSubsystem, 0.75, 60), // speed -1..1, positive to turn CW or right, negative to turn CCW or left, time in seconds

            //Drive Stop Speed (0) for 30 seconds
            new DriveTimed_Gyro(ChassisDrivetrainSubsystem, 0, 30),
        
            //Drive Reverse Full Speed (-1) for 60 seconds
            new DriveTimed_Gyro(ChassisDrivetrainSubsystem, -1, 60),
        
            //Drive Stop Speed (0) for 30 seconds
            new DriveTimed_Gyro(ChassisDrivetrainSubsystem, 0, 30),
            
            //Turn Speed (0) for 60 seconds
            new DriveTurnTimed(ChassisDrivetrainSubsystem, -0.75, 60), // speed -1..1, positive to turn CW or right, negative to turn CCW or left, time in seconds
        
            //Drive Stop Speed (0) for 30 seconds
            new DriveTimed_Gyro(ChassisDrivetrainSubsystem, 0, 30)

        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
