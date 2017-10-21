package org.firstinspires.ftc.blackteamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.commonteamcode.MecanumDriveBase;

/**
 * Created by Benjamin Jensrud on 10/21/2017.
 * A temporary opmode written to go into the safety zone and nothing else
 */
@Autonomous(name = "Temp Autonomous", group = "opmodes")
public class TempAutonomous extends LinearOpMode {

	private DcMotor lfd, lbd, rfd, rbd;
	private Servo lServo, rServo;
	private MecanumDriveBase driveBase;

	@Override
	public void runOpMode() throws InterruptedException {
		lfd = hardwareMap.dcMotor.get("lfd");
		lbd = hardwareMap.dcMotor.get("lbd");
		rfd = hardwareMap.dcMotor.get("rfd");
		rbd = hardwareMap.dcMotor.get("rbd");

		lfd.setDirection(DcMotorSimple.Direction.REVERSE);
		lbd.setDirection(DcMotorSimple.Direction.REVERSE);
		rfd.setDirection(DcMotorSimple.Direction.FORWARD);
		rbd.setDirection(DcMotorSimple.Direction.FORWARD);

		driveBase = new MecanumDriveBase(lfd, rfd, lbd, rbd);

		lServo = hardwareMap.servo.get("lServo");
		rServo = hardwareMap.servo.get("rServo");

		lServo.setDirection(Servo.Direction.FORWARD);
		rServo.setDirection(Servo.Direction.REVERSE);

		waitForStart();

		driveBase.setSpeeds(-0.5,0,0);

		Thread.sleep(1200);

		driveBase.setSpeeds(0,0,0);
	}
}
