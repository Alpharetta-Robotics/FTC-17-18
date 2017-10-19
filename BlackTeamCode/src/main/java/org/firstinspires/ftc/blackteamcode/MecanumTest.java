package org.firstinspires.ftc.blackteamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Benjamin Jensrud on 10/2/2017.
 * Mecanum drive base test
 */

@TeleOp(name = "Mecanum Test", group = "test")
@Disabled
public class MecanumTest extends OpMode {

	private DcMotor fl,fr,bl,br;

	@Override
	public void init() {
		fl=hardwareMap.dcMotor.get("fl");
		fr=hardwareMap.dcMotor.get("fr");
		bl=hardwareMap.dcMotor.get("bl");
		br=hardwareMap.dcMotor.get("br");

		fl.setDirection(DcMotorSimple.Direction.REVERSE);
		fr.setDirection(DcMotorSimple.Direction.FORWARD);
		bl.setDirection(DcMotorSimple.Direction.REVERSE);
		br.setDirection(DcMotorSimple.Direction.FORWARD);
	}

	@Override
	public void loop() {

		double back = gamepad1.left_stick_y;
		double right = gamepad1.left_stick_x;
		double rot = gamepad1.right_stick_x;

		double speed=Math.min(Math.sqrt(Math.pow(back,2.0)+Math.pow(right,2)),1);

		//Figure out theta in the worst way possible, because edge cases
		int casenum = (int) ((Math.signum(right)+2)+((Math.signum(back)+2)*10));
		double arctan = Math.atan(right/back);
		double theta = 0;

		//First digit is the sign of the horizontal stick, second digit is the sign of the vertical stick as:
		//1=negative, 2=0, 3=positive
		switch (casenum) {
			case 11: theta = arctan;break;				//forward left
			case 12: theta = 0;break;					//forward straight
			case 13: theta = arctan + 2*Math.PI;break;	//forward right
			case 21: theta = Math.PI/2;break;			//straight left
			case 22: theta = 0;break;					//stopped
			case 23: theta = Math.PI*3/2;break;			//straight right
			case 31: theta = arctan + Math.PI;break;	//backward left
			case 32: theta = Math.PI;break;				//backward straight
			case 33: theta = arctan + Math.PI;break;	//backward right
		}
		double flmult = (speed*Math.sin(theta+Math.PI/4)+rot)/Math.max(1,speed+rot);
		double frmult = (speed*Math.cos(theta+Math.PI/4)-rot)/Math.max(1,speed+rot);
		double blmult = (speed*Math.cos(theta+Math.PI/4)+rot)/Math.max(1,speed+rot);
		double brmult = (speed*Math.sin(theta+Math.PI/4)-rot)/Math.max(1,speed+rot);

		/*
		fl.setDirection((flmult<0)? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
		fr.setDirection((frmult>0)? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
		bl.setDirection((blmult<0)? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
		br.setDirection((brmult>0)? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
		*/



		telemetry.addData("In","Back:"+back+", Right:"+right+", Rot:"+rot);
		telemetry.addData("Translation","Speed:"+speed+", Theta:"+theta);
		telemetry.addData("Mults","FL:"+flmult+", FR:"+frmult+", BL:"+blmult+", BR:"+brmult);
		telemetry.addData("Angle",""+theta*180/Math.PI);
		telemetry.update();

		fl.setPower(flmult);
		fr.setPower(brmult);
		bl.setPower(blmult);
		br.setPower(frmult);
	}
}
