package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
public class CalculatorActivity extends AppCompatActivity implements CalculatorBinaryFragment.calculatorArrayListener,
        CalculatorHexadecimalFragment.calculatorArrayListener, CalculatorDecimalFragment.calculatorArrayListener, OnItemSelectedListener {

    ArrayList<String> calculatorArray = new ArrayList<>();
    /*RadioButton radioBinaryButton, radioHexadecimalButton;*/
    TextView txtInput, txtResult;
    boolean isTwoComplimentEnabled;
    int decimalVal, currentIndex;
    String lastMode, currentMode, firstInputString, secondInputString, Operator;
    Spinner calculatorMode;
    int firstInput, secondInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //adds toolbar to top of activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Sets dropdown from resource string array
        calculatorMode = (Spinner) findViewById(R.id.spinnerSelectCalculator);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.number_formats, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        calculatorMode.setAdapter(spinnerAdapter);
        calculatorMode.setOnItemSelectedListener(this);

        // For Setting Logo in toolbar myToolbar.setLogo();
        currentIndex = 0;
        isTwoComplimentEnabled = false;
        /*radioBinaryButton = (RadioButton) findViewById(R.id.binarybuttoncalculator);
        radioHexadecimalButton = (RadioButton) findViewById(R.id.hexadecimalbuttoncalculator);*/
        txtInput = (TextView) findViewById(R.id.textViewInput);
        txtResult = (TextView) findViewById(R.id.textViewResult);
    }

    public void decimalCalculator(View view) {
        Fragment decimalFragment = new CalculatorDecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.calculator_frame, decimalFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        currentMode = "Decimal";
        convertInputAndResult(lastMode, currentMode);
        lastMode = "Decimal";
    }

    public void binaryCalculator(View view) {
        Fragment binaryFragment = new CalculatorBinaryFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_frame, binaryFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        currentMode = "Binary";
        convertInputAndResult(lastMode, currentMode);
        lastMode = "Binary";
    }

    /*
    Displays the hexadecimal mode of operation of the calculator activity when user selects the
    radio hexadecimal button
    */
    public void hexadecimalCalculator(View view) {
        Fragment hexadecimalFragment = new CalculatorHexadecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_frame, hexadecimalFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        currentMode = "Hexadecimal";
        convertInputAndResult(lastMode, currentMode);
        lastMode = "Hexadecimal";
    }

    /*
    calculatorArrayActivity checks what to do with users input
    If it is a digit it will add to its own array entry or append it to an existing array entry if
    the latest array entry is a digit.
    If the input is an operation it checks to make sure that there is a digit entered
    before the operator otherwise it is not accepted.
     */
    @Override
    public void calculatorArrayActivity(String userInput) {
        switch (userInput) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "A":
            case "B":
            case "C":
            case "D":
            case "E":
            case "F":
                if (calculatorArray.size() == 0) {
                    calculatorArray.add(userInput);
                } else if (calculatorArray.get(currentIndex) == "+" ||
                        calculatorArray.get(currentIndex) == "-" ||
                        calculatorArray.get(currentIndex) == "*" ||
                        calculatorArray.get(currentIndex) == "/") {
                    currentIndex++;
                    calculatorArray.add(userInput);
                } else {
                    //if the array is not empty and the current index in the array is not an operator
                    //then the array entry at the current index must be a number so then the current
                    //user input is appended to the end
                    calculatorArray.set(currentIndex, calculatorArray.get(currentIndex) + userInput);
                }

                setCalculatorResultDisplay();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (calculatorArray.isEmpty()) {
                    Toast.makeText(this, "Need to enter a number before an operation", Toast.LENGTH_SHORT).show();
                } else if (calculatorArray.get(currentIndex) == "+" ||
                        calculatorArray.get(currentIndex) == "-" ||
                        calculatorArray.get(currentIndex) == "*" ||
                        calculatorArray.get(currentIndex) == "/") {
                    //if current array entry is an operation, replace it with the latest user input operation
                    calculatorArray.set(currentIndex, userInput);
                } else {
                    currentIndex++;
                    calculatorArray.add(userInput);
                }

                //currentIndex += 2;
                //calculatorArray.add("");
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
                setCalculatorResultDisplay();
                break;
            case "Clear":
                currentIndex = 0;
                calculatorArray.clear();
                txtResult.setText("");
                break;
            case "TwoCompliment":
                if (isTwoComplimentEnabled == false) isTwoComplimentEnabled = true;
                else isTwoComplimentEnabled = false;
        }
        setCalculatorInputDisplay();
    }

    public void setCalculatorInputDisplay() {
        switch (currentMode) {
            case "Decimal":
                if (calculatorArray.size() == 0) {
                    txtInput.setText("");
                }
                for (int i = 0; i < calculatorArray.size(); i++) {
                    if (i == 0) {
                        txtInput.setText(calculatorArray.get(i));
                    } else {
                        txtInput.append(" " + calculatorArray.get(i) + " ");
                    }
                }
                break;
            case "Binary":
                if (calculatorArray.size() == 0) {
                    txtInput.setText("");
                }
                for (int i = 0; i < calculatorArray.size(); i++) {
                    if (i == 0) {
                        txtInput.setText(calculatorArray.get(i));
                    } else {
                        txtInput.append(" " + calculatorArray.get(i) + " ");
                    }
                }
                break;
            case "Hexadecimal":
                if (calculatorArray.size() == 0) {
                    txtInput.setText("");
                }
                for (int i = 0; i < calculatorArray.size(); i++) {
                    if (i == 0) {
                        txtInput.setText(calculatorArray.get(i));
                    } else {
                        txtInput.append(" " + calculatorArray.get(i) + " ");
                    }
                }
                break;
        }

    }


    public void setCalculatorResultDisplay() {
        switch (currentMode) {
            case "Decimal":
                if (calculatorArray.isEmpty() != true) {
                    firstInputString = calculatorArray.get(0);
                    firstInput = Integer.parseInt(firstInputString);
                    if (calculatorArray.size() > 1) {
                        for (int i = 1; i < calculatorArray.size(); i++) {

                            if (calculatorArray.get(i - 1) == "+") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString);
                                firstInput = firstInput + secondInput;
                            } else if (calculatorArray.get(i - 1) == "-") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString);
                                firstInput = firstInput - secondInput;
                            } else if (calculatorArray.get(i - 1) == "*") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString);
                                firstInput = firstInput * secondInput;
                            } else if (calculatorArray.get(i - 1) == "/") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString);
                                firstInput = firstInput / secondInput;
                            }
                            txtResult.setText(Integer.toString(firstInput));

                        }
                    } else {
                        txtResult.setText(Integer.toString(firstInput));
                    }
                } else {
                    firstInputString = "";
                    txtResult.setText(firstInputString);
                }
                break;
            case "Binary":
                if (calculatorArray.isEmpty() != true) {
                    if (isTwoComplimentEnabled == false) {
                        firstInputString = calculatorArray.get(0);
                        firstInput = Integer.parseInt(firstInputString, 2);
                        if (calculatorArray.size() > 1) {
                            for (int i = 1; i < calculatorArray.size(); i++) {

                                if (calculatorArray.get(i - 1) == "+") {
                                    secondInputString = calculatorArray.get(i);
                                    secondInput = Integer.parseInt(secondInputString, 2);
                                    firstInput = firstInput + secondInput;
                                } else if (calculatorArray.get(i - 1) == "-") {
                                    secondInputString = calculatorArray.get(i);
                                    secondInput = Integer.parseInt(secondInputString, 2);
                                    firstInput = firstInput - secondInput;
                                } else if (calculatorArray.get(i - 1) == "*") {
                                    secondInputString = calculatorArray.get(i);
                                    secondInput = Integer.parseInt(secondInputString, 2);
                                    firstInput = firstInput * secondInput;
                                } else if (calculatorArray.get(i - 1) == "/") {
                                    secondInputString = calculatorArray.get(i);
                                    secondInput = Integer.parseInt(secondInputString, 2);
                                    firstInput = firstInput / secondInput;
                                }
                                txtResult.setText(Integer.toBinaryString(firstInput));

                            }
                        } else {
                            txtResult.setText(Integer.toBinaryString(firstInput));
                        }
                    } else if(isTwoComplimentEnabled == true){
                        
                    }
                } else {
                    firstInputString = "";
                    txtResult.setText(firstInputString);
                }
                break;
            case "Hexadecimal":
                if (calculatorArray.isEmpty() != true) {
                    firstInputString = calculatorArray.get(0);
                    firstInput = Integer.parseInt(firstInputString, 16);
                    if (calculatorArray.size() > 1) {
                        for (int i = 1; i < calculatorArray.size(); i++) {

                            if (calculatorArray.get(i - 1) == "+") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString, 16);
                                firstInput = firstInput + secondInput;
                            } else if (calculatorArray.get(i - 1) == "-") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString, 16);
                                firstInput = firstInput - secondInput;
                            } else if (calculatorArray.get(i - 1) == "*") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString, 16);
                                firstInput = firstInput * secondInput;
                            } else if (calculatorArray.get(i - 1) == "/") {
                                secondInputString = calculatorArray.get(i);
                                secondInput = Integer.parseInt(secondInputString, 16);
                                firstInput = firstInput / secondInput;
                            }
                            txtResult.setText(Integer.toHexString(firstInput));

                        }
                    } else {
                        txtResult.setText(Integer.toHexString(firstInput));
                    }
                } else {
                    firstInputString = "";
                    txtResult.setText(firstInputString);
                }
                break;

        }
    }

    /*
        Displays the binary mode of operation of the calculator activity when user selects the
        radio binary button
        */


    /*
    convertInputAndResult is responsible for converting all previous inputs and the result to
    whatever user chosen format and displaying so that as user changes modes the displayed number format
    also changes
     */
    public void convertInputAndResult(String lastMode, String currentMode) {
        //Switch case statements first check the newly selected mode and then what the previous used
        //mode was and behaves accordingly for conversion
        if (lastMode == "" || lastMode == null) {
            return;
        }
        switch (lastMode) {
            //Convert from decimal to the currentMode
            case "Decimal":
                switch (currentMode) {
                    case "Binary":
                        for (int i = 0; i < calculatorArray.size(); i++) {
                            if (calculatorArray.get(i).contains("+") ||
                                    calculatorArray.get(i).contains("-") ||
                                    calculatorArray.get(i).contains("*") ||
                                    calculatorArray.get(i).contains("/")) {

                            } else {
                                decimalVal = Integer.parseInt(calculatorArray.get(i));
                                calculatorArray.set(i, Integer.toBinaryString(decimalVal));
                            }
                        }
                        break;
                    case "Hexadecimal":
                        for (int i = 0; i < calculatorArray.size(); i++) {
                            if (calculatorArray.get(i).contains("+") ||
                                    calculatorArray.get(i).contains("-") ||
                                    calculatorArray.get(i).contains("*") ||
                                    calculatorArray.get(i).contains("/")) {

                            } else {
                                decimalVal = Integer.parseInt(calculatorArray.get(i));
                                calculatorArray.set(i, Integer.toHexString(decimalVal));
                            }
                        }
                        break;
                }
                break;
            //Convert from binary to the currentMode
            case "Binary":
                switch (currentMode) {
                    case "Decimal":
                        for (int i = 0; i < calculatorArray.size(); i++) {
                            if (calculatorArray.get(i).contains("+") ||
                                    calculatorArray.get(i).contains("-") ||
                                    calculatorArray.get(i).contains("*") ||
                                    calculatorArray.get(i).contains("/")) {

                            } else {
                                decimalVal = Integer.parseInt(calculatorArray.get(i), 2);
                                calculatorArray.set(i, Integer.toString(decimalVal));
                            }
                        }
                        break;
                    case "Hexadecimal":
                        for (int i = 0; i < calculatorArray.size(); i++) {
                            if (calculatorArray.get(i).contains("+") ||
                                    calculatorArray.get(i).contains("-") ||
                                    calculatorArray.get(i).contains("*") ||
                                    calculatorArray.get(i).contains("/")) {
                                continue;
                            } else {
                                decimalVal = Integer.parseInt(calculatorArray.get(i), 2);
                                calculatorArray.set(i, Integer.toHexString(decimalVal));
                            }
                        }
                        break;
                }
                break;
            //Convert from hexadecimal to the currentMode
            case "Hexadecimal":
                switch (currentMode) {
                    case "Binary":
                        for (int i = 0; i < calculatorArray.size(); i++) {
                            if (calculatorArray.get(i).contains("+") ||
                                    calculatorArray.get(i).contains("-") ||
                                    calculatorArray.get(i).contains("*") ||
                                    calculatorArray.get(i).contains("/")) {

                            } else {
                                decimalVal = Integer.parseInt(calculatorArray.get(i), 16);
                                calculatorArray.set(i, Integer.toBinaryString(decimalVal));
                            }
                        }
                        break;
                    case "Decimal":
                        for (int i = 0; i < calculatorArray.size(); i++) {
                            if (calculatorArray.get(i).contains("+") ||
                                    calculatorArray.get(i).contains("-") ||
                                    calculatorArray.get(i).contains("*") ||
                                    calculatorArray.get(i).contains("/")) {

                            } else {
                                decimalVal = Integer.parseInt(calculatorArray.get(i), 16);
                                calculatorArray.set(i, Integer.toString(decimalVal));
                            }
                        }
                        break;
                }
                break;
        }
        setCalculatorInputDisplay();
        setCalculatorResultDisplay();
    }

    /*
    onItemSelected and onNothingSelected are selection handlers for dropdown
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                decimalCalculator(view);
                //decimalCalculator();
                break;
            case 1:
                binaryCalculator(view);
                break;
            case 2:
                //octalCalculator(view);
                break;
            case 3:
                hexadecimalCalculator(view);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
