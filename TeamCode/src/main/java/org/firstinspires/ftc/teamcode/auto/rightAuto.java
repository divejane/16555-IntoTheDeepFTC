package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous
public class rightAuto extends LinearOpMode {
    private DcMotor fL, bL, fR, bR;

    @Override
    public void runOpMode() throws InterruptedException {
        // HARDWARE

        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");


        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        fR.setDirection(DcMotorSimple.Direction.REVERSE);

        Servo flipServo = hardwareMap.servo.get("flipServo");

        flipServo.setPosition(0);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // put the thingy on the thingy
            strafe(1500, 0.5);
            sleep(250);

            strafe(-350, 0.5);
            sleep(250);

            drive(2250,2250,2250,2250, 0.5);
            sleep(250);

            drive(-900,500,500,-900,0.5);
            sleep(250);

            // put the things in the thing zone
            for (int x = 0; x < 2; x++) {

                drive(350,350,350,350, 0.5);
                sleep(250);

                strafe(2250,0.5);
                sleep(250);

                strafe(-2250, 0.5);
                sleep(250);
            }
            drive(100,100,100,100, 0.5);
            sleep(250);

            strafe(2250,0.5);
            sleep(50000);
        }
    }

    // + right
    public void strafe(int dir, double power) {

        drive(dir, -dir, dir, -dir, power);

    }

    public void drive(int rB, int lB, int rF, int lF, double power) {

        bR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bR.setTargetPosition(rB);
        bL.setTargetPosition(-lB);
        fR.setTargetPosition(rF);
        fL.setTargetPosition(-lF);

        bR.setPower(power);
        bL.setPower(power);
        fR.setPower(power);
        fL.setPower(power);

        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Blocking While Loop: doesn't break until all 4 motors have stopped moving
        // Sets power of motors to 0 after the loop breaks
        while (opModeIsActive() && (bR.isBusy() || bL.isBusy() || fR.isBusy() || fL.isBusy())) {
        }

        bR.setPower(0);
        bL.setPower(0);
        fR.setPower(0);
        fL.setPower(0);
    }

}
