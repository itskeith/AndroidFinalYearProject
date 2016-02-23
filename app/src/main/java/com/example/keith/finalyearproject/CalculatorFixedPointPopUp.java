package com.example.keith.finalyearproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Keith on 18/02/2016.
 */
public class CalculatorFixedPointPopUp extends Activity implements View.OnClickListener {
    SeekBar leftFixedPoint, rightFixedPoint;
    TextView seekbarPosition, seekbarPositionRight;
    Button enableDisableButton;
    int positionLeft, positionRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.fixedpoint_popup);

        enableDisableButton = (Button) findViewById(R.id.buttonFPEnableDisable);
        enableDisableButton.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.75), (int) (height * 0.45));

        seekBar();

        if (GlobalVar.fixedPointEnabled == true) {
            enableDisableButton.setText("Disable Fixed Point");
        } else if (GlobalVar.fixedPointEnabled == false) {
            enableDisableButton.setText("Enable Fixed Point");
        }

    }

    public void seekBar() {

        leftFixedPoint = (SeekBar) findViewById(R.id.seekBarLeft);
        leftFixedPoint.setMax(64);
        rightFixedPoint = (SeekBar) findViewById(R.id.seekBarRight);
        rightFixedPoint.setMax(64);
        seekbarPosition = (TextView) findViewById(R.id.seekBarResult);
        // seekbarPosition.setText(twosCompSeekBar.getProgress() + " bit length for 2's Complement");
        seekbarPosition.setText("Bits to the left of the fixed point are defaulted to 64.");

        leftFixedPoint.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                positionLeft = progress;
                GlobalVar.positionLeft = positionLeft;
                if (positionLeft + positionRight > 64) {
                    positionRight = 64 - positionLeft;
                    GlobalVar.positionRight = positionRight;
                    rightFixedPoint.setProgress(positionRight);
                } else positionRight = positionRight;
                seekbarPosition.setText(positionLeft + " bits to left of fixed point \n" + positionRight + " bits to right of fixed point");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalVar.positionLeft = positionLeft;
                if (positionLeft + positionRight > 64) {
                    positionRight = 64 - positionLeft;
                    GlobalVar.positionRight = positionRight;
                    rightFixedPoint.setProgress(positionRight);
                } else positionRight = positionRight;
                seekbarPosition.setText(positionLeft + " bits to left of fixed point \n" + positionRight + " bits to right of fixed point");
            }

        });

        rightFixedPoint.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBarRight, int progress, boolean fromUser) {
                positionRight = progress;
                GlobalVar.positionRight = positionRight;
                if (positionLeft + positionRight > 64) {
                    positionLeft = 64 - positionRight;
                    GlobalVar.positionLeft = positionLeft;
                    leftFixedPoint.setProgress(positionLeft);
                } else positionLeft = positionLeft;
                seekbarPosition.setText(positionLeft + " bits to left of fixed point \n" + positionRight + " bits to right of fixed point");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBarRight) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBarRight) {
                GlobalVar.positionRight = positionRight;
                if (positionLeft + positionRight > 64) {
                    positionLeft = 64 - positionRight;
                    GlobalVar.positionLeft = positionLeft;
                    leftFixedPoint.setProgress(positionLeft);
                } else positionLeft = positionLeft;
                seekbarPosition.setText(positionLeft + " bits to left of fixed point \n" + positionRight + " bits to right of fixed point");
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonFPEnableDisable:
                //Set text for button when user clicks button to show whether fixedpoint is enabled/disabled
                if (GlobalVar.fixedPointEnabled == false) {
                    enableDisableButton.setText("Disable Fixed Point");
                    GlobalVar.fixedPointEnabled = true;
                } else if (GlobalVar.fixedPointEnabled == true) {
                    enableDisableButton.setText("Enable Fixed Point");
                    GlobalVar.fixedPointEnabled = false;
                }
        }
    }
}
