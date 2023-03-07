/**
 *  Class that organizes PID gains used
 */
package frc.robot;

public class Gains {
	public final double kP;
	public final double kI;
	public final double kD;
	public final double kF;
	public final double kTolerance; // position tolerance
	public final double kRateTolerance; // Rate tolerance
	
	public Gains(double _kP, double _kI, double _kD, double _kF, double _kTolerance, double _kRateTolerance ){
		kP = _kP;
		kI = _kI;
		kD = _kD;
		kF = _kF;
		kTolerance = _kTolerance;
		kRateTolerance = _kTolerance;
	}
}
