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

/**
 * Created by Keith on 22/02/2016.
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
        //convertHexStep(originalValue);
        //convertOctalStep(originalValue);
    }

    public void convertDecimalStep(String value) {
        //Break string into a digit per array index
        //String valuesarray[] = ;
        //Break string into a digit per array index
        int hexvalue;
        String values[] = value.split("(?!^)"); // (?!^) = regex-expression negative lookahead
        for (int i = 0; i < values.length; i++) {
            hexvalue = Integer.parseInt(values[values.length - (1 + i)],16);
            hexvalue = hexvalue * (int)Math.pow(16, i);
            //textViewBinaryConvert.append(values[values.length - 1 + i] + " = " + values[values.length - 1 + i] + " x 16^" + Integer.toString(i) + " = " + Integer.toHexString(Integer.parseInt(values[values.length - 1 + i])).toUpperCase() + " x " + Double.toString(Math.pow(16,(double)i)) + " = " + Integer.toString(hexvalue) + "\n");
            textViewDecimalConvert.append(values[values.length - (1 + i)] + " = ");
            textViewDecimalConvert.append(values[values.length - (1 + i)] + " x 16^");
            textViewDecimalConvert.append(Integer.toString(i) + " = ");
            textViewDecimalConvert.append(Integer.toHexString(Integer.parseInt(values[values.length - (1 + i)],16)).toUpperCase() + " x ");
            textViewDecimalConvert.append(Integer.toString((int)Math.pow(16,(double)i)) + " = ");
            textViewDecimalConvert.append(Integer.toString(hexvalue) + "\n");
        }

    }

    /*
    convertBinaryStep first checks length of input string until it is divisible by 4 so that the binary
    number can be shown in blocks of 4 bits to correspond to hexadecimal values
     */
    public void convertBinaryStep(String value) {

        int values = Integer.parseInt(value);
        int remainder;
        while (values > 0) {
            textViewDecimalConvert.append(values + "/16 = ");
            remainder = values % 16;
            values = values / 16;
            textViewDecimalConvert.append(values + ", remainder = " + remainder + " = " + Integer.toHexString(remainder).toUpperCase() + "\n");
        }
        textViewDecimalConvert.append("The Hexadecimal value is the remainders as read from the bottom of the list, so in this case the Hexadecimal value is: " + Integer.toHexString(Integer.parseInt(value)).toUpperCase());
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
