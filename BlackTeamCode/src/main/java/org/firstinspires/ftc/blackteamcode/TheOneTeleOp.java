package org.firstinspires.ftc.blackteamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Benjamin Jensrud on 10/19/2017.
 * One OpMode to rule them all
 * One OpMode to bind them
 * One OpMode to bring them all
 * And in the competition bind them
 */
@TeleOp(name="The One Op Mode", group = "opmodes")
public class TheOneTeleOp extends OpMode {

	private DcMotor lfd, lbd, rfd, rbd;
	private Servo lServo, rServo;
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

		lServo = hardwareMap.servo.get("lServo");
		rServo = hardwareMap.servo.get("rServo");

		lServo.setDirection(Servo.Direction.FORWARD);
		rServo.setDirection(Servo.Direction.REVERSE);
	}

	@Override
	public void loop() {
		driveBase.setSpeedsFromGamepad(gamepad1);

		if (gamepad1.right_bumper) {
			lServo.setPosition(0.5);
			rServo.setPosition(0.5);
		}
		else {
			lServo.setPosition(0.7);
			rServo.setPosition(0.7);
		}
	}
}
