package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Keith on 14/12/2015.
 */
public class CalculatorActivity extends AppCompatActivity {
    RadioButton radioBinaryButton, radioHexadecimalButton;
    TextView txtInput, txtResult;
    int decimalVal;
    String lastMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //adds toolbar to top of activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // For Setting Logo in toolbar myToolbar.setLogo();

        radioBinaryButton = (RadioButton) findViewById(R.id.binarybuttoncalculator);
        radioHexadecimalButton = (RadioButton) findViewById(R.id.hexadecimalbuttoncalculator);
        txtInput = (TextView) findViewById(R.id.textViewInput);
    }

    //Displays the binary mode of operation of the calculator activity when user selects the
    //radio binary button
    public void binaryCalculator(View view) {
        if(lastMode == "Hexadecimal"){
            decimalVal = Integer.parseInt(txtInput.getText().toString(),16);
            txtInput.setText(Integer.toBinaryString(decimalVal));
        }
        Fragment binaryFragment = new CalculatorBinaryFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_frame, binaryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        lastMode="Binary";
    }

    //Displays the hexadecimal mode of operation of the calculator activity when user selects the
    //radio hexadecimal button
    public void hexadecimalCalculator(View view) {
        if(lastMode == "Binary"){
            decimalVal = Integer.parseInt(txtInput.getText().toString(),2);
            txtInput.setText(Integer.toHexString(decimalVal));
        }
        Fragment hexadecimalFragment = new CalculatorHexadecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_frame, hexadecimalFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        lastMode="Hexadecimal";
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
                Intent launchReferencesIntent = new Intent(CalculatorActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchConverterIntent = new Intent(CalculatorActivity.this, ConverterActivity.class);
                startActivity(launchConverterIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(CalculatorActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
