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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Keith on 14/12/2015.
 */
public class CalculatorActivity extends AppCompatActivity implements CalculatorBinaryFragment.calculatorArrayListener {

    ArrayList<String> calculatorArray = new ArrayList<>();
    RadioButton radioBinaryButton, radioHexadecimalButton;
    TextView txtInput, txtResult;
    int decimalVal, currentIndex;
    String lastMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //adds toolbar to top of activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // For Setting Logo in toolbar myToolbar.setLogo();
        currentIndex = 0;
        radioBinaryButton = (RadioButton) findViewById(R.id.binarybuttoncalculator);
        radioHexadecimalButton = (RadioButton) findViewById(R.id.hexadecimalbuttoncalculator);
        txtInput = (TextView) findViewById(R.id.textViewInput);
    }

    @Override
    public void calculatorArrayActivity(String userInput) {
        switch (userInput) {
            case "0":
            case "1":
                if (calculatorArray.size() == 0) {
                    calculatorArray.add(userInput);
                } else {
                    calculatorArray.set(currentIndex, calculatorArray.get(currentIndex) + userInput);
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (calculatorArray.isEmpty()) {
                    Toast.makeText(this, "Need to enter a number before an operation", Toast.LENGTH_SHORT).show();
                }
                calculatorArray.add(userInput);
                currentIndex += 2;
                calculatorArray.add("");
                break;
            case "Delete":
                int arrayEntryLength;
                if (calculatorArray.isEmpty()) {
                    currentIndex = 0;
                } else if (calculatorArray.get(currentIndex).length() == 0) {
                    calculatorArray.remove(currentIndex);
                    currentIndex--;
                    arrayEntryLength = calculatorArray.get(currentIndex).length();
                    if (arrayEntryLength > 0) {
                        calculatorArray.set(currentIndex, calculatorArray.get(currentIndex).substring(0, arrayEntryLength - 1));
                    }
                } else {
                    arrayEntryLength = calculatorArray.get(currentIndex).length();
                    calculatorArray.set(currentIndex, calculatorArray.get(currentIndex).substring(0, arrayEntryLength - 1));
                    if (calculatorArray.get(currentIndex).length() == 0) {
                        calculatorArray.remove(currentIndex);
                        if (currentIndex > 0) {
                            currentIndex--;
                        }
                    }
                }
                break;
            case "Clear":
                currentIndex = 0;
                calculatorArray.clear();
                break;
        }
        setCalculatorInputDisplay();
    }

    public void setCalculatorInputDisplay() {
        if (lastMode == "Binary") {
            if(calculatorArray.size() == 0){
                txtInput.setText("");
            }
            for (int i = 0; i < calculatorArray.size(); i++) {
                if (i == 0) {
                    txtInput.setText(calculatorArray.get(i));
                } else {
                    txtInput.append(" " + calculatorArray.get(i) + " ");
                }
            }
        }
    }

    //Displays the binary mode of operation of the calculator activity when user selects the
    //radio binary button
    public void binaryCalculator(View view) {
        if (lastMode == "Hexadecimal") {
            decimalVal = Integer.parseInt(txtResult.getText().toString(), 16);
            txtResult.setText(Integer.toBinaryString(decimalVal));
        }
        Fragment binaryFragment = new CalculatorBinaryFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_frame, binaryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        lastMode = "Binary";
    }

    //Displays the hexadecimal mode of operation of the calculator activity when user selects the
    //radio hexadecimal button
    public void hexadecimalCalculator(View view) {
        if (lastMode == "Binary") {
            decimalVal = Integer.parseInt(txtResult.getText().toString(), 2);
            txtResult.setText(Integer.toHexString(decimalVal));
        }
        Fragment hexadecimalFragment = new CalculatorHexadecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_frame, hexadecimalFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        lastMode = "Hexadecimal";
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
