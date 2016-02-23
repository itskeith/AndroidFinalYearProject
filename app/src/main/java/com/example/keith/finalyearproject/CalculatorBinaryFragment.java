package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
/*
CalculatorBinaryFragment displays buttons for CalculatorActivity when Decimal radio button selected
Only handles button clicks in fragment, all of calculations and displaying of information is carried
out in CalculatorActivity
 */
public class CalculatorBinaryFragment extends Fragment implements View.OnClickListener, TextWatcher {


    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<String> operators = new ArrayList<>();

    int firstInput, secondInput, calculateResult, currentIndex;
    String lastOperation, input, operation, calculateResultString;
    TextView txtInput, txtResult;
    Button oneButton, zeroButton, plusButton, minusButton, multiplyButton, divideButton,
            deleteButton, clearButton, pointButton, twosComplementButton;
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
        pointButton = (Button) view.findViewById(R.id.buttonPoint);
        pointButton.setOnClickListener(this);
        deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(this);
        clearButton = (Button) view.findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(this);
        twosComplementButton = (Button) view.findViewById(R.id.buttonTwoComplement);
        twosComplementButton.setOnClickListener(this);
        /*
        twosComplimentButton = (Button) view.findViewById(R.id.buttonTwosComp);
        twosComplimentButton.setOnClickListener(this);*/
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
                break;
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
                getInput(input);
                break;
            case R.id.buttonPoint:
                input = ".";
                getInput(input);
                break;
            case R.id.buttonTwoComplement:
                startActivity(new Intent(getActivity(),CalculatorTwosComplementPopup.class));
                break;
            /*
            case R.id.buttonTwosComp:
                input = "TwoCompliment";
                getInput(input);
                break;*/
        }
    }


    public void getInput(String input) {
        //
        ((calculatorArrayListener) activity).calculatorArrayActivity(input,GlobalVar.fixedPointEnabled);

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
}

