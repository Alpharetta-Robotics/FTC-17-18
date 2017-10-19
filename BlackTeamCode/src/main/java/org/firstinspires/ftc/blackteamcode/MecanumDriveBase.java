package org.firstinspires.ftc.blackteamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Benjamin Jensrud on 10/19/2017.
 * Generic Mecanum drive base controller
 */

public class MecanumDriveBase {

	private DcMotor leftFront, rightFront, leftBack, rightBack;

	public MecanumDriveBase(DcMotor leftFront, DcMotor rightFront, DcMotor leftBack, DcMotor rightBack) {
		this.leftFront	= leftFront;
		this.rightFront	= rightFront;
		this.leftBack	= leftBack;
		this.rightBack	= rightBack;
	}

	public void setSpeeds(double speed, double direction, double rotate) {
		double baseCos = speed*Math.cos(direction + Math.PI/4);
		double baseSin = speed*Math.sin(direction + Math.PI/4);
		double speedLimiter = Math.max(rotate, 1);

		leftFront	.setPower((baseSin + rotate)/speedLimiter);
		rightFront	.setPower((baseCos - rotate)/speedLimiter);
		leftBack	.setPower((baseCos + rotate)/speedLimiter);
		rightBack	.setPower((baseSin - rotate)/speedLimiter);
	}

	public void setSpeedsFromGamepad(Gamepad gamepad) {
		double sideways	= gamepad.left_stick_x;
		double forward	= gamepad.left_stick_y;
		double rotate	= -gamepad.right_stick_x;

		double speed = Math.sqrt(Math.pow(sideways,2)+Math.pow(forward,2));
		double direction = Math.atan2(-sideways, forward);

		setSpeeds(speed, direction, rotate);
	}
}
