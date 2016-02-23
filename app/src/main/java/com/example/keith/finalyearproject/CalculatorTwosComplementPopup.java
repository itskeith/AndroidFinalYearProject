package com.example.keith.finalyearproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.SeekBar;
import android.widget.TextView;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
public class CalculatorTwosComplementPopup extends Activity {
    SeekBar twosCompSeekBar;
    TextView seekbarPosition;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.twocomplement_popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.75), (int) (height * 0.35));

        seekBar();

    }

    public void seekBar() {
        twosCompSeekBar = (SeekBar) findViewById(R.id.seekBar);
        twosCompSeekBar.setMax(30);
        seekbarPosition = (TextView) findViewById(R.id.seekBarTextView);
        // seekbarPosition.setText(twosCompSeekBar.getProgress() + " bit length for 2's Complement");
        seekbarPosition.setText("2 bit length for 2's Complement");

        twosCompSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                position = progress;
                GlobalVar.position = position + 2;
                seekbarPosition.setText((position + 2) + " bit length for 2's Complement");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarPosition.setText((position + 2) + " bit length for 2's Complement");
                GlobalVar.position = position + 2;
            }
        });


    }
}
