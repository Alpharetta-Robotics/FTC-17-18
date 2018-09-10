package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.HardwarePushBot.colorSense;
import static org.firstinspires.ftc.teamcode.HardwarePushBot.colorSensorArm;
import static org.firstinspires.ftc.teamcode.HardwarePushBot.colorSensorArmRotate;

/**
 * Created by shrey on 2018-01-25.
 */


@Autonomous (name = "Red ColorSensor Right", group = "Autonomous")
public class RedColorRight extends LinearOpMode{
    HardwarePushBot robot = new HardwarePushBot();
    private ElapsedTime runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;
    private double color;

    @Override
    public void runOpMode(){
         /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        //FOR COLOR SENSOR ONLY
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F,0F,0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //Step 1. Wait for 3.0 seconds on the spot
        driveMethod(0, 0, 2.0);

        DriveMethods.closeGlyph();

        //Step 2. Drive backwards for 0.3 seconds
        colorSense.enableLed(true);

        driveMethod(-FORWARD_SPEED * 0.2 , -FORWARD_SPEED * 0.2, 0.5);

        driveMethod(0, 0, 2.5);
//Step 3. Drop the color sensor in between the jewels and take reading
        colorSensorArmRotate.setPosition(0.45);
        doNothing();

        driveMethod(FORWARD_SPEED * 0.4, FORWARD_SPEED * 0.4, 0.4);
        colorSensorArm.setPosition(1);

        doNothing();


        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 3.0))
        {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();


            //Step 4. Read the color of jewels
            if (colorSense.red() >(colorSense.blue() + 20 )) {
                color = 0;
                telemetry.addData(colorSense.red()+"I see red!", "RED");
                telemetry.update();
            }
            else if (colorSense.red() <(colorSense.blue()+20)) {
                color = -1;
                telemetry.addData("I see blue!", "BLUE");
            }
            else
            {
                color = 3;
                telemetry.addData("I see nothing!", "NOTHING");
            }

        }


        //Step 5. Perform action according to color
        if(color != 0){
            colorSensorArmRotate.setPosition(1);

            doNothing();

            colorSensorArmRotate.setPosition(0.5);

            doNothing();

            colorSensorArm.setPosition(0);

            doNothing();

            colorSense.enableLed(false);

            //Step 8. Drive forward onto balance board
            driveMethod(FORWARD_SPEED * 0.3, FORWARD_SPEED * 0.3, 0.6);

            doNothing();

            //Step 9. Lift glyph lift for 0.8 seconds
            robot.glyphLift.setPower(0.15);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.8)) {
                telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }

            //Step 10. Turn Right for 1.4 seconds
            driveMethod(-TURN_SPEED * 0.3, TURN_SPEED * 0.3, 2.8);

            doNothing();

            //Step 11. Drive forward into parking spot for 2.2 seconds
            driveMethod(FORWARD_SPEED * 0.5, FORWARD_SPEED * 0.5, 1.8);

            doNothing();

            driveMethod(-TURN_SPEED * 0.3, TURN_SPEED * 0.3, 0.8);

            doNothing();

            //Step 13. Drop glyph into Cryptograph
            DriveMethods.openGlyph();

            doNothing();

            //Step 14. Move backwards for 0.8 seconds
            driveMethod(-FORWARD_SPEED * 0.3, -FORWARD_SPEED * 0.3, 0.8);

            doNothing();

            //Step 15. Move forwards for 1.4 seconds
            driveMethod(FORWARD_SPEED * 0.3, FORWARD_SPEED * 0.3, 1.4);

            doNothing();

            //Step 16. Move backwards for 0.4 seconds
            driveMethod(-FORWARD_SPEED * 0.3, -FORWARD_SPEED * 0.3, 0.4);

            doNothing();
        }
        else{
            colorSensorArmRotate.setPosition(0);

            doNothing();

            colorSensorArmRotate.setPosition(0.4);

            doNothing();

            colorSensorArm.setPosition(0);

            doNothing();

            colorSense.enableLed(false);

            //Step 8. Drive forward onto the balance board
            driveMethod(FORWARD_SPEED * 0.3, FORWARD_SPEED * 0.3, 0.6);

            doNothing();

            //Step 9. Lift glyph lift for 0.8 seconds
            robot.glyphLift.setPower(0.15);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.8)) {
                telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }

            //Step 10. Turn Right for 1.4 seconds
            driveMethod(-TURN_SPEED * 0.3, TURN_SPEED * 0.3, 2.8);

            doNothing();

            //Step 11. Drive forward into parking spot for 2.2 seconds
            driveMethod(FORWARD_SPEED * 0.5, FORWARD_SPEED * 0.5, 1.8);

            doNothing();

            //Step 12. Turn Right for 0.2 seconds
            driveMethod(-TURN_SPEED * 0.3, TURN_SPEED * 0.3, 0.8);

            doNothing();

            //Step 13. Drop glyph into Cryptograph
            DriveMethods.openGlyph();

            doNothing();

            //Step 14. Move backwards for 0.8 seconds
            driveMethod(-FORWARD_SPEED * 0.3, -FORWARD_SPEED * 0.3, 0.8);

            doNothing();

            //Step 15. Move forwards for 1.4 seconds
            driveMethod(FORWARD_SPEED * 0.3, FORWARD_SPEED * 0.3, 1.4);

            doNothing();

            //Step 16. Move backwards for 0.4 seconds
            driveMethod(-FORWARD_SPEED * 0.3, -FORWARD_SPEED * 0.3, 0.4);

            doNothing();

        }

    }
    public void driveMethod(double rightSpeed, double leftSpeed, double secs){
        robot.rightFrontDrive.setPower(rightSpeed);
        robot.rightBackDrive.setPower(rightSpeed);
        robot.leftFrontDrive.setPower(leftSpeed);
        robot.leftBackDrive.setPower(leftSpeed);

        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < secs)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }

    public void doNothing(){
        driveMethod(0, 0, 0.3);
    }
}
