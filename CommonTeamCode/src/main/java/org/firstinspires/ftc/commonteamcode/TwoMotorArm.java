package org.firstinspires.ftc.commonteamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.commonteamcode.util.Point;
import org.firstinspires.ftc.commonteamcode.util.PointDefinedArea;

/**
 * Created by Benjamin Jensrud on 10/20/2017.
 *
 */

public class TwoMotorArm extends Arm {

	double lengthOne, lengthTwo;
	PointDefinedArea area;
	public TwoMotorArm (DcMotor motorA, DcMotor motorB, double lengthOne, double lengthTwo, PointDefinedArea area) {
		this.lengthOne = lengthOne;
		this.lengthTwo = lengthTwo;
		this.area = area;
	}

	protected double[] getAngles(Point point) {
		double x = point.x();
		double y = point.y();
		double length = Math.min(lengthOne + lengthTwo, Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
		double targetAngle = Math.atan2(y, x);
		double angleA = targetAngle + Math.acos((-Math.pow(lengthOne,2)+Math.pow(lengthTwo,2)+Math.pow(length,2))/(2*lengthOne*length));
		double angleB = Math.acos((Math.pow(lengthOne,2)+Math.pow(lengthTwo,2)-Math.pow(length,2))/(2*lengthOne*lengthTwo));
		return new double[]{angleA, angleB};
	}

	@Override
	void goToPoint(double x, double y) {

	}
}
