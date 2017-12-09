package org.firstinspires.ftc.blackteamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.commonteamcode.MecanumDriveBase;

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
	private DcMotor armShoulder, armElbow, armWrist;
	private Servo lServo, rServo;
	private MecanumDriveBase driveBase;
	private DcMotorControllerEx shoulderController, elbowController, wristController;
	private int shoulderTarget, elbowTarget, wristTarget;
	private int previousShoulderTarget, previousElbowTarget, previousWristTarget;

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

		armShoulder = hardwareMap.dcMotor.get("armShoulder");
		armElbow	= hardwareMap.dcMotor.get("armElbow");
		armWrist	= hardwareMap.dcMotor.get("armWrist");

		armShoulder	.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		armElbow	.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		armWrist	.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

		armShoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		armElbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		armWrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);

		armShoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		armElbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		armWrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		lServo = hardwareMap.servo.get("lServo");
		rServo = hardwareMap.servo.get("rServo");

		lServo.setDirection(Servo.Direction.FORWARD);
		rServo.setDirection(Servo.Direction.REVERSE);

		shoulderTarget	= armShoulder	.getCurrentPosition();
		elbowTarget		= armElbow		.getCurrentPosition();
		wristTarget		= armWrist		.getCurrentPosition();

		previousShoulderTarget	=armShoulder.getCurrentPosition();
		previousElbowTarget		=armElbow	.getCurrentPosition();
		previousWristTarget		=armWrist	.getCurrentPosition();
	}


	@Override
	public void loop() {
		driveBase.setSpeedsFromGamepad(gamepad1);

		if (gamepad2.right_bumper) {
			lServo.setPosition(0.2);
			rServo.setPosition(0.5);
		}
		else {
			lServo.setPosition(0.7);
			rServo.setPosition(0.7);
		}

		shoulderTarget	+= gamepad2.left_stick_y*10;
		elbowTarget		+= gamepad2.right_stick_y*10;
		wristTarget		+= (gamepad2.left_trigger-gamepad2.right_trigger)*10;

		telemetry.addData("Shoulder: ","Target="+shoulderTarget+", Encoder="+armShoulder.getCurrentPosition()+", TargetSet="+armShoulder.getTargetPosition());
		telemetry.addData("Elbow:","Target="+elbowTarget+", Encoder="+armElbow.getCurrentPosition()+", TargetSet="+armElbow.getTargetPosition());
		telemetry.addData("Wrist:","Target="+wristTarget+", Encoder="+armWrist.getCurrentPosition()+", TargetSet="+armWrist.getTargetPosition());
		telemetry.update();


		if (previousShoulderTarget!=shoulderTarget) {
			armShoulder.setTargetPosition(shoulderTarget);
			armShoulder.setPower(1);
		}

		if (previousElbowTarget!=elbowTarget) {
			armElbow.setTargetPosition(elbowTarget);
			armElbow.setPower(1);
		}

		if (previousWristTarget!=wristTarget) {
			armWrist.setTargetPosition(wristTarget);
			armWrist.setPower(1);
		}

		previousShoulderTarget=shoulderTarget;
		previousWristTarget=wristTarget;
		previousElbowTarget=elbowTarget;
	}
}
