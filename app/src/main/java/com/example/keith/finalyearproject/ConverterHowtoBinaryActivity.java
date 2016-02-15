package com.example.keith.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Keith on 09/02/2016.
 */
public class ConverterHowtoBinaryActivity extends AppCompatActivity {

    TextView textViewToConvert, textViewDecimalConvert;
    String originalValue;
    double powerOf;
    int test, test2,test3;
    ArrayList<String> converterInputArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_howto);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textViewToConvert = (TextView) findViewById(R.id.textViewValueToConvert);
        textViewDecimalConvert = (TextView) findViewById(R.id.textViewDecimalStep);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            originalValue = "";
        } else {
            originalValue = extras.getString("value");
            textViewToConvert.setText(originalValue);
        }
        convertStep(originalValue);
    }

    public void convertStep(String value) {
        //Break string into a digit per array index
        String values[] = value.split("(?!^)"); // (?!^) = regex-expression negative lookahead
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals("0")) {
                test = 5;
                textViewDecimalConvert.append("0");
            } else if (values[i].equals("1")) {

                powerOf = Math.pow(2, values.length - 1 - i);
                textViewDecimalConvert.append(Integer.toString((int)powerOf));
            }
            if (values.length-1 != i) {
                textViewDecimalConvert.append(" + ");
            }
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
                Intent launchReferencesIntent = new Intent(ConverterHowtoBinaryActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchConverterIntent = new Intent(ConverterHowtoBinaryActivity.this, ConverterActivity.class);
                startActivity(launchConverterIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(ConverterHowtoBinaryActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
