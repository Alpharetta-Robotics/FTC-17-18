package org.firstinspires.ftc.commonteamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.commonteamcode.util.Point;
import org.firstinspires.ftc.commonteamcode.util.PointDefinedArea;

/**
 * Created by Benjamin Jensrud on 10/20/2017.
 */

public class TwoMotorChainedArm extends TwoMotorArm {

	public TwoMotorChainedArm (DcMotor motorA, DcMotor motorB, double lengthOne, double lengthTwo, PointDefinedArea area) {
		super(motorA, motorB, lengthOne, lengthTwo, area);
	}

	@Override
	public double[] getAngles(Point point) {
		double[] raw = super.getAngles(point);
		double angleB = 180 - Math.atan2(point.y(), point.x());
		return new double[]{raw[0],angleB};
	}
}
