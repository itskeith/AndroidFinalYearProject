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
public class ConverterHowToHexadecimalActivity extends AppCompatActivity {
    TextView textViewToConvert, textViewBinaryConvert, textViewDecimalConvert, textViewOctalConvert;
    String originalValue;
    double powerOf;
    int decimalValue;
    ArrayList<String> hexArray = new ArrayList<>();
    ArrayList<String> octalArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set appropriate view
        setContentView(R.layout.activity_converter_howto_hexadecimal);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textViewToConvert = (TextView) findViewById(R.id.textViewValueToConvert);
        textViewDecimalConvert = (TextView) findViewById(R.id.textViewDecimalStep);
        textViewBinaryConvert = (TextView) findViewById(R.id.textViewBinaryStep);
        textViewOctalConvert = (TextView) findViewById(R.id.textViewOctalStep);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            originalValue = "";
        } else {
            originalValue = extras.getString("value");
            textViewToConvert.setText(originalValue);
        }
        convertDecimalStep(originalValue);
        convertBinaryStep(originalValue);
        convertOctalStep(originalValue);
    }

    public void convertDecimalStep(String value) {
        int hexvalue;
        String values[] = value.split("(?!^)"); // (?!^) = regex-expression negative lookahead
        for (int i = 0; i < values.length; i++) {
            hexvalue = Integer.parseInt(values[values.length - (1 + i)], 16);
            hexvalue = hexvalue * (int) Math.pow(16, i);
            //textViewBinaryConvert.append(values[values.length - 1 + i] + " = " + values[values.length - 1 + i] + " x 16^" + Integer.toString(i) + " = " + Integer.toHexString(Integer.parseInt(values[values.length - 1 + i])).toUpperCase() + " x " + Double.toString(Math.pow(16,(double)i)) + " = " + Integer.toString(hexvalue) + "\n");
            textViewDecimalConvert.append(values[values.length - (1 + i)] + " = ");
            textViewDecimalConvert.append(values[values.length - (1 + i)] + " x 16^");
            textViewDecimalConvert.append(Integer.toString(i) + " = ");
            textViewDecimalConvert.append(Integer.toHexString(Integer.parseInt(values[values.length - (1 + i)], 16)).toUpperCase() + " x ");
            textViewDecimalConvert.append(Integer.toString((int) Math.pow(16, (double) i)) + " = ");
            textViewDecimalConvert.append(Integer.toString(hexvalue) + "\n");
        }
        textViewDecimalConvert.append("These values all added together then give you: " + Integer.toString(Integer.parseInt(value, 16)));

    }

    /*
    convertBinaryStep first checks length of input string until it is divisible by 4 so that the binary
    number can be shown in blocks of 4 bits to correspond to hexadecimal values
     */
    public void convertBinaryStep(String value) {

        String values[] = value.split("(?!^)"); // (?!^) = regex-expression negative lookahead
        for (int i = 0; i < values.length; i++) {
            textViewBinaryConvert.append(values[i] + " = ");
            textViewBinaryConvert.append(Integer.toString(Integer.parseInt(values[i], 16)) + " = ");
            textViewBinaryConvert.append(Integer.toBinaryString(Integer.parseInt(values[i], 16)) + "\n");
        }
        textViewBinaryConvert.append("Putting all these together then gives you: " + Integer.toBinaryString(Integer.parseInt(value, 16)));
    }

    /*
    ConvertOctalStep performs the step display for showing how to convert from binary to octal
     */
    public void convertOctalStep(String value) {
        int hexvalue = Integer.parseInt(value, 16);
        String binaryValue = Integer.toBinaryString(hexvalue);
        while (binaryValue.length() % 3 != 0) {
            binaryValue = "0" + binaryValue;
        }
        for (int i = 0; i < binaryValue.length(); i = i + 3) {
            octalArray.add(binaryValue.substring(i, i + 3));
        }
        for (int j = 0; j < octalArray.size(); j++) {
            decimalValue = Integer.parseInt(octalArray.get(j), 2);
            if (j == 0) {
                textViewOctalConvert.setText(octalArray.get(j) + " = " + Integer.toOctalString(decimalValue) + "\n");
            } else {
                textViewOctalConvert.append(octalArray.get(j) + " = " + Integer.toOctalString(decimalValue) + "\n");
            }
        }
        textViewOctalConvert.append("All of these in sequence give you your Octal value: " + Integer.toOctalString((Integer.parseInt(binaryValue, 2))));
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
                Intent launchReferencesIntent = new Intent(ConverterHowToHexadecimalActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchConverterIntent = new Intent(ConverterHowToHexadecimalActivity.this, ConverterActivity.class);
                startActivity(launchConverterIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(ConverterHowToHexadecimalActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
