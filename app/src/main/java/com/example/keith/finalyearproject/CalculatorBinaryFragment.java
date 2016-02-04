package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Keith on 14/12/2015.
 */
public class CalculatorBinaryFragment extends Fragment implements View.OnClickListener, TextWatcher {


    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<>();

    int firstInput, secondInput, calculateResult, currentIndex;
    String lastOperation, input, operation, calculateResultString;
    TextView txtInput, txtResult;
    Button oneButton, zeroButton, plusButton, minusButton, multiplyButton, divideButton,
            deleteButton, clearButton, equalsButton;
    String userInput;
    Activity activity;

    Boolean isLastInputOperation;
    Boolean isTherePreviousInput = false, isTherePreviousOperation = false;
    String firstInputString = "", secondInputString = "";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_binary, container, false);
        currentIndex = 0;
        //Text views for displaying users input and results
        txtInput = (TextView) getActivity().findViewById(R.id.textViewInput);
        txtResult = (TextView) getActivity().findViewById(R.id.textViewResult);
        txtInput.addTextChangedListener(this);

        //Make results view scrollable
        // txtResult.setMovementMethod(new ScrollingMovementMethod());

        //Setting button listeners for calculators Buttons
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
        equalsButton = (Button) view.findViewById(R.id.buttonEquals);
        equalsButton.setOnClickListener(this);
        deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(this);
        clearButton = (Button) view.findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(this);
        return view;
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

    /*
    onClick handles any button clicks for the fragment, entering a 1 or 0 adds to textView and
    sends value to array list in CalculatorActivity.
    When entering an operation it first checks that there is a number before the operation before
    sending its value to the array list in CalculatorActivity.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOne:
                input = "1";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonZero:
                input = "0";
                isLastInputOperation = false;
                getInput(input);
                break;
            case R.id.buttonMinus:

                    input = "-";
                    isLastInputOperation = true;
                    getInput(input);
                break;
            case R.id.buttonPlus:
                    isLastInputOperation = true;
                    input = "+";
                    getInput(input);
                break;/*
            case R.id.buttonEquals:
                isLastInputOperation = true;
                input = "=";
                getInput(input);
                break;*/
            case R.id.buttonDivide:
                isLastInputOperation = true;
                input = "/";
                getInput(input);
                break;
            case R.id.buttonMultiply:
                isLastInputOperation = true;
                input = "*";
                getInput(input);
                break;
            case R.id.buttonDelete:
                input = "Delete";
                getInput(input);
                //int start = txtInput.getText().toString().length();
                //txtInput.setText(txtInput.getEditableText().delete(start - 1,start - 1));
                break;
            case R.id.buttonClear:
                input = "Clear";
                getInput(input);;
        }
    }

    /*Interface for communicating from fragment to activity*/
    public interface calculatorArrayListener {
        public void calculatorArrayActivity(String userInput);
    }

    public void getInput(String input) {
        //
        switch (input) {
            case "1":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            case "0":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            case "+":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            case "-":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            case "*":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            case "/":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            /*case "Equals":
                lastOperation = "Equals";
                if (isTherePreviousInput) {
                    firstInputString = calculateResultString;
                    secondInputString = "";
                }
                isTherePreviousInput = true;
                break;*/
            case "Delete":
                //need delete logic still
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;
            case "Clear":
                ((calculatorArrayListener) activity).calculatorArrayActivity(input);
                break;

        }

    /*
        getInput gets the values for the firstInputString and secondInputString based on what a user
           enters, the values are assigned when an operation is selected such as addition.
           The firstInputString is initially assigned as the first string before the first operation
           the secondInputString is assigned as whatever comes between the first operation and the
           second operation. The firstInputString is continually reassigned to be the result of
           the operation between firstInputString and secondInputString and secondInputString is
           reassigned to be empty everytime this is done.
     */
    /*public void getInput(String input) {
        //
        switch (input) {
            case "1":
                if (isTherePreviousInput == false) {
                    firstInputString = firstInputString + "1";
                } else {
                    secondInputString = secondInputString + "1";
                    calculate(firstInputString, secondInputString, lastOperation);
                }
                break;
            case "0":
                if (isTherePreviousInput == false) {
                    firstInputString = firstInputString + "0";
                } else {
                    secondInputString = secondInputString + "0";
                    calculate(firstInputString, secondInputString, lastOperation);
                }
                break;
            case "Plus":
                lastOperation = "Plus";
                if(isTherePreviousInput){
                    firstInputString = calculateResultString;
                    secondInputString = "";
                }
                isTherePreviousInput = true;
                break;
            case "Minus":
                lastOperation = "Minus";

                if(isTherePreviousInput){
                    firstInputString = calculateResultString;
                    secondInputString = "";
                }
                isTherePreviousInput = true;
                break;
            case "Multiply":
                lastOperation = "Multiply";

                if(isTherePreviousInput){
                    firstInputString = calculateResultString;
                    secondInputString = "";
                }
                isTherePreviousInput = true;
                break;
            case "Divide":
                lastOperation = "Divide";
                if(isTherePreviousInput){
                    firstInputString = calculateResultString;
                    secondInputString = "";
                }
                isTherePreviousInput = true;
                break;
            case "Equals":
                lastOperation = "Equals";
                if(isTherePreviousInput){
                    firstInputString = calculateResultString;
                    secondInputString = "";
                }
                isTherePreviousInput = true;
                break;
            case "Delete":
                //need delete logic still
                lastOperation = "Delete";
                break;
            case "Clear":
                lastOperation = "Clear";
                isTherePreviousInput = false;
                firstInputString = "";
                secondInputString = "";
                break;

        }*/
//        switch (operation) {
//            case "Plus":
//                if (firstInputString.isEmpty() || firstInputString != null) {
//                    lastOperation = "Plus";
//                    isTherePreviousInput = true;
//                } else if (firstInputString.isEmpty() != true && secondInputString.isEmpty()) {
//                    secondInputString = input;
//                } else if (firstInputString.isEmpty() != true && secondInputString.isEmpty() != true) {
//                    calculate(firstInputString, secondInputString, lastOperation);
//                }
//                lastOperation = "+";
//                break;
//            case "=":
//                if(input.isEmpty() != true){
//                    secondInputString = input;
//                    calculate(firstInputString, secondInputString, lastOperation);
//                }
//                txtResult.setText("");
//                break;
//            default:
//                /*  Checks if there is any value already for firstInput String
//                    if not then the firstInputString is
//                  */
//                if (firstInputString == "") {
//                    firstInputString = input;
//                } else if (firstInputString.length() > 1) {
//
//                }
//        }
    }

    /*public String calculate(String firstInputString, String secondInputString, String mode) {
        firstInput = Integer.parseInt(firstInputString, 2);
        secondInput = Integer.parseInt(secondInputString, 2);

        switch (mode) {
            case "Plus":
                calculateResult = firstInput + secondInput;
                break;
            case "Minus":
                calculateResult = firstInput - secondInput;
                break;
            case "Divide":
                calculateResult = firstInput / secondInput;
                break;
            case "Multiply":
                calculateResult = firstInput * secondInput;
                break;
        }

        calculateResultString = Integer.toBinaryString(calculateResult);
        txtResult.setText(calculateResultString);

        return calculateResultString;
    }*/
}
