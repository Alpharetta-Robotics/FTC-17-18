package org.firstinspires.ftc.silverteamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.commonteamcode.MecanumDriveBase;

/**
 * Created by Benjamin Jensrud on 10/21/2017.
 */
@TeleOp(name = "Silver Temporary TeleOp", group = "opmodes")
public class SilverTempTeleOp extends OpMode {
	private DcMotor lfd, lbd, rfd, rbd;
	private MecanumDriveBase driveBase;

	@Override
	public void init() {
		lfd = hardwareMap.dcMotor.get("lfd");
		lbd = hardwareMap.dcMotor.get("lbd");
		rfd = hardwareMap.dcMotor.get("rfd");
		rbd = hardwareMap.dcMotor.get("rbd");

		lfd.setDirection(DcMotorSimple.Direction.FORWARD);
		lbd.setDirection(DcMotorSimple.Direction.FORWARD);
		rfd.setDirection(DcMotorSimple.Direction.REVERSE);
		rbd.setDirection(DcMotorSimple.Direction.REVERSE);

		driveBase = new MecanumDriveBase(lfd, rfd, lbd, rbd);
	}

	@Override
	public void loop() {
		driveBase.setSpeedsFromGamepad(gamepad1);
	}
}
