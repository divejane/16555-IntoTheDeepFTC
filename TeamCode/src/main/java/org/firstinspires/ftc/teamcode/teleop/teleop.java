package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // HARDWARE

        DcMotor fL = hardwareMap.dcMotor.get("frontLeft");
        DcMotor bL = hardwareMap.dcMotor.get("backLeft");
        DcMotor fR = hardwareMap.dcMotor.get("frontRight");
        DcMotor bR = hardwareMap.dcMotor.get("backRight");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double forward    =  gamepad1.left_stick_y;
            double strafe     =  gamepad1.left_stick_x;
            double turn       =  -gamepad1.right_stick_x;

            //  Maximum possible power sent to a motor is -1 or 1, but we can sometimes get values from
            //  (forward + strafe + turn) that exceed -1 or 1, so we have to normalize all motors' power
            //  to keep their speed proportional to each other in the off-chance that this does happen
            double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 1);

            double fLPower  = -forward + strafe - turn / denominator;
            double bLPower  =  forward + strafe + turn / denominator;
            double fRPower  =  forward + strafe - turn / denominator;
            double bRPower  = -forward + strafe + turn / denominator;

            fL.setPower(fLPower);
            bL.setPower(bLPower);
            fR.setPower(fRPower);
            bR.setPower(bRPower);

        }
    }
}