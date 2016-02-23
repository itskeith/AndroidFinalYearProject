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
 * Created by Keith on 09/02/2016.
 */
public class ConverterHowToBinaryActivity extends AppCompatActivity {

    TextView textViewToConvert, textViewDecimalConvert, textViewHexadecimalConvert, textViewOctalConvert;
    String originalValue;
    double powerOf;
    int decimalValue;
    ArrayList<String> hexArray = new ArrayList<>();
    ArrayList<String> octalArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set appropriate view
        setContentView(R.layout.activity_converter_howto_binary);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textViewToConvert = (TextView) findViewById(R.id.textViewValueToConvert);
        textViewDecimalConvert = (TextView) findViewById(R.id.textViewDecimalStep);
        textViewHexadecimalConvert = (TextView) findViewById(R.id.textViewHexadecimalStep);
        textViewOctalConvert = (TextView) findViewById(R.id.textViewOctalStep);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            originalValue = "";
        } else {
            originalValue = extras.getString("value");
            textViewToConvert.setText(originalValue);
        }
        convertDecimalStep(originalValue);
        convertHexStep(originalValue);
        convertOctalStep(originalValue);
    }

    public void convertDecimalStep(String value) {
        //Break string into a digit per array index
        String values[] = value.split("(?!^)"); // (?!^) = regex-expression negative lookahead
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("0")) {
                textViewDecimalConvert.append("0");
            } else if (values[i].equals("1")) {

                powerOf = Math.pow(2, values.length - 1 - i);
                textViewDecimalConvert.append(Integer.toString((int) powerOf));
            }
            if (values.length - 1 != i) {
                textViewDecimalConvert.append(" + ");
            }
        }
        textViewDecimalConvert.append("\nAll of these added together give you your decimal value: " + Integer.toString(Integer.parseInt(value, 2)));

    }

    /*
    convertHexStep first checks length of input string until it is divisible by 4 so that the binary
    number can be shown in blocks of 4 bits to correspond to hexadecimal values
     */
    public void convertHexStep(String value) {
        if (value.length() % 4 != 0) {
            value = "0" + value;
            convertHexStep(value);
        } else {
            for (int i = 0; i < value.length(); i = i + 4) {
                hexArray.add(value.substring(i, i + 4));
            }
            for (int j = 0; j < hexArray.size(); j++) {
                decimalValue = Integer.parseInt(hexArray.get(j), 2);
                if (j == 0) {
                    textViewHexadecimalConvert.setText(hexArray.get(j) + " = " + Integer.toString(decimalValue) + " = " + Integer.toHexString(decimalValue).toUpperCase() + "\n");
                } else {
                    textViewHexadecimalConvert.append(hexArray.get(j) + " = " + Integer.toString(decimalValue) + " = " + Integer.toHexString(decimalValue).toUpperCase() + "\n");
                }
            }
            textViewHexadecimalConvert.append("All of these in sequence give you your Hexadecimal value: " + Integer.toHexString(Integer.parseInt(value, 2)).toUpperCase());
        }
    }
    /*
    ConvertOctalStep performs the step display for showing how to convert from binary to octal
     */
    public void convertOctalStep(String value) {
        if (value.length() % 3 != 0) {
            value = "0" + value;
            convertOctalStep(value);
        } else {
            for (int i = 0; i < value.length(); i = i + 3) {
                octalArray.add(value.substring(i, i + 3));
            }
            for (int j = 0; j < octalArray.size(); j++) {
                decimalValue = Integer.parseInt(octalArray.get(j), 2);
                if (j == 0) {
                    textViewOctalConvert.setText(octalArray.get(j) + " = " + Integer.toString(decimalValue) + " = " + Integer.toOctalString(decimalValue) + "\n");
                } else {
                    textViewOctalConvert.append(octalArray.get(j) + " = " + Integer.toString(decimalValue) + " = " + Integer.toOctalString(decimalValue) + "\n");
                }
            }
            textViewOctalConvert.append("All of these in sequence give you your Octal value: " + Integer.toOctalString((Integer.parseInt(value, 2))));
        }
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
                Intent launchReferencesIntent = new Intent(ConverterHowToBinaryActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchConverterIntent = new Intent(ConverterHowToBinaryActivity.this, ConverterActivity.class);
                startActivity(launchConverterIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(ConverterHowToBinaryActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
