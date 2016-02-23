package com.example.keith.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
public class ConverterHowToOctalActivity extends AppCompatActivity {
    TextView textViewToConvert, textViewBinaryConvert, textViewHexadecimalConvert, textViewOctalConvert;
    String originalValue;
    double powerOf;
    int decimalValue;
    ArrayList<String> hexArray = new ArrayList<>();
    ArrayList<String> octalArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set appropriate view
        setContentView(R.layout.activity_converter_howto_octal);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textViewToConvert = (TextView) findViewById(R.id.textViewValueToConvert);
        textViewBinaryConvert = (TextView) findViewById(R.id.textViewBinaryStep);
        textViewHexadecimalConvert = (TextView) findViewById(R.id.textViewHexadecimalStep);
        textViewOctalConvert = (TextView) findViewById(R.id.textViewOctalStep);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            originalValue = "";
        } else {
            originalValue = extras.getString("value");
            textViewToConvert.setText(originalValue);
        }
        convertBinaryStep(originalValue);
        convertHexStep(originalValue);
        convertOctalStep(originalValue);
    }

    public void convertBinaryStep(String value) {
        //Break string into a digit per array index
        //String valuesarray[] = ;
        int values = Integer.parseInt(value);
        int remainder;
        while (values > 0) {
            textViewBinaryConvert.append(values + "/2 = ");
            remainder = values % 2;
            values = values / 2;
            textViewBinaryConvert.append(values + ", remainder = " + remainder + "\n");
        }
        textViewBinaryConvert.append("The binary value is the remainders as read from the bottom of the list, so in this case the binary value is: " + Integer.toBinaryString(Integer.parseInt(value)));
    }

    /*
    convertHexStep first checks length of input string until it is divisible by 4 so that the binary
    number can be shown in blocks of 4 bits to correspond to hexadecimal values
     */
    public void convertHexStep(String value) {

        int values = Integer.parseInt(value);
        int remainder;
        while (values > 0) {
            textViewHexadecimalConvert.append(values + "/16 = ");
            remainder = values % 16;
            values = values / 16;
            textViewHexadecimalConvert.append(values + ", remainder = " + remainder + " = " + Integer.toHexString(remainder).toUpperCase() + "\n");
        }
        textViewHexadecimalConvert.append("The Hexadecimal value is the remainders as read from the bottom of the list, so in this case the Hexadecimal value is: " + Integer.toHexString(Integer.parseInt(value)).toUpperCase());
    }

    /*
    ConvertOctalStep performs the step display for showing how to convert from binary to octal
     */
    public void convertOctalStep(String value) {
        int values = Integer.parseInt(value);
        int remainder;
        while (values > 0) {
            textViewOctalConvert.append(values + "/8 = ");
            remainder = values % 8;
            values = values / 8;
            textViewOctalConvert.append(values + ", remainder = " + remainder + " = " + Integer.toHexString(remainder) + "\n");
        }
        textViewOctalConvert.append("The octal value is the remainders as read from the bottom of the list, so in this case the octal value is: " + Integer.toHexString(Integer.parseInt(value)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, menu);

        return true;
    }

    //Menu item options in toolbar for launching other activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_references:
                Intent launchReferencesIntent = new Intent(ConverterHowToOctalActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchConverterIntent = new Intent(ConverterHowToOctalActivity.this, ConverterActivity.class);
                startActivity(launchConverterIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(ConverterHowToOctalActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
