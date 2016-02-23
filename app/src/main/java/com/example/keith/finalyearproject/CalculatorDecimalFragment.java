package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
/*
CalculatorDecimalFragment displays buttons for CalculatorActivity when Decimal radio button selected
Only handles button clicks in fragment, all of calculations and displaying of information is carried
out in CalculatorActivity
 */
public class CalculatorDecimalFragment extends Fragment implements View.OnClickListener, TextWatcher {

    int firstInput, secondInput, calculateResult;
    String firstInputString, secondInputString, lastOperation;
    TextView txtInput, txtResult;
    Button oneButton, zeroButton, twoButton, threeButton, fourButton, fiveButton, sixButton,
            sevenButton, eightButton, nineButton, plusButton, minusButton, multiplyButton,
            divideButton, deleteButton, clearButton, pointButton;
    String input;
    Boolean isLastInputOperation;
    Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_decimal, container, false);

        //Text views for displaying users input and results
        txtInput = (TextView) getActivity().findViewById(R.id.textViewInput);
        txtResult = (TextView) getActivity().findViewById(R.id.textViewResult);
        txtInput.addTextChangedListener(this);

        //Make results view scrollable
        txtResult.setMovementMethod(new ScrollingMovementMethod());

        //Setting button listeners for calculators Buttons
        nineButton = (Button) view.findViewById(R.id.buttonNine);
        nineButton.setOnClickListener(this);
        eightButton = (Button) view.findViewById(R.id.buttonEight);
        eightButton.setOnClickListener(this);
        sevenButton = (Button) view.findViewById(R.id.buttonSeven);
        sevenButton.setOnClickListener(this);
        sixButton = (Button) view.findViewById(R.id.buttonSix);
        sixButton.setOnClickListener(this);
        fiveButton = (Button) view.findViewById(R.id.buttonFive);
        fiveButton.setOnClickListener(this);
        fourButton = (Button) view.findViewById(R.id.buttonFour);
        fourButton.setOnClickListener(this);
        threeButton = (Button) view.findViewById(R.id.buttonThree);
        threeButton.setOnClickListener(this);
        twoButton = (Button) view.findViewById(R.id.buttonTwo);
        twoButton.setOnClickListener(this);
        oneButton = (Button) view.findViewById(R.id.buttonOne);
        oneButton.setOnClickListener(this);
        zeroButton = (Button) view.findViewById(R.id.buttonZero);
        zeroButton.setOnClickListener(this);
        minusButton = (Button) view.findViewById(R.id.buttonMinus);
        minusButton.setOnClickListener(this);
        plusButton = (Button) view.findViewById(R.id.buttonPlus);
        plusButton.setOnClickListener(this);
        divideButton = (Button) view.findViewById(R.id.buttonDivide);
        divideButton.setOnClickListener(this);
        multiplyButton = (Button) view.findViewById(R.id.buttonMultiply);
        multiplyButton.setOnClickListener(this);
        pointButton = (Button) view.findViewById(R.id.buttonPoint);
        pointButton.setOnClickListener(this);
        deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(this);
        clearButton = (Button) view.findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(this);

        return view;
    }

    /*Interface for communicating from fragment to activity*/
    public interface calculatorArrayListener {
        public void calculatorArrayActivity(String userInput, boolean fixedPointEnabled);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonZero:
                input = "0";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonOne:
                input = "1";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonTwo:
                input = "2";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonThree:
                input = "3";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonFour:
                input = "4";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonFive:
                input = "5";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonSix:
                input = "6";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonSeven:
                input = "7";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonEight:
                input = "8";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonNine:
                input = "9";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonMinus:
                input = "-";
                isLastInputOperation = true;
                getInput(input);
                break;
            case R.id.buttonPlus:
                input = "+";
                isLastInputOperation = true;
                getInput(input);
                break;
            case R.id.buttonPoint:
                input = ".";
                isLastInputOperation = true;
                if(GlobalVar.fixedPointEnabled==true){
                    getInput(input);
                    break;
                }else{
                    break;
                }
            case R.id.buttonDivide:
                input = "/";
                isLastInputOperation = true;
                getInput(input);
                break;
            case R.id.buttonMultiply:
                input = "*";
                isLastInputOperation = true;
                getInput(input);
                break;
            case R.id.buttonDelete:
                input = "Delete";
                getInput(input);
                break;
            case R.id.buttonClear:
                input = "Clear";
                getInput(input);
        }
    }

    public void getInput(String input) {
        ((calculatorArrayListener) activity).calculatorArrayActivity(input, GlobalVar.fixedPointEnabled);
    }

    /*
    onResume greys out and disables point button if fixed point is disabled or makes it appear like
    other buttons to show it is enabled when fixed point is enabled
     */
    public void onResume(){
        super.onResume();
        if(GlobalVar.fixedPointEnabled == true){
            pointButton.setActivated(true);
            pointButton.setBackgroundResource(R.drawable.buttons_calc);
        } else if(GlobalVar.fixedPointEnabled == false){
            pointButton.setActivated(false);
            pointButton.setBackgroundResource(R.drawable.buttons_disable);
        }
    }
}
