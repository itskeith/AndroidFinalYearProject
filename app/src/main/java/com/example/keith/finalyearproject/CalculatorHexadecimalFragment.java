package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.Nullable;
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

public class CalculatorHexadecimalFragment extends Fragment implements View.OnClickListener, TextWatcher {

    int firstInput, secondInput, calculateResult;
    String firstInputString, secondInputString, lastOperation;
    TextView txtInput, txtResult;
    Button oneButton, zeroButton, twoButton, threeButton, fourButton, fiveButton, sixButton,
            sevenButton, eightButton, nineButton, aButton, bButton, cButton, dButton, eButton,
            fButton, plusButton, minusButton, multiplyButton, divideButton, deleteButton,
            clearButton, equalsButton;
    String userInput;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_hexadecimal, container, false);

        //Text views for displaying users input and results
        txtInput = (TextView) getActivity().findViewById(R.id.textViewInput);
        txtResult = (TextView) getActivity().findViewById(R.id.textViewResult);
        txtInput.addTextChangedListener(this);

        //Make results view scrollable
        txtResult.setMovementMethod(new ScrollingMovementMethod());

        //Setting button listeners for calculators Buttons
        fButton = (Button) view.findViewById(R.id.buttonF);
        fButton.setOnClickListener(this);
        eButton = (Button) view.findViewById(R.id.buttonE);
        eButton.setOnClickListener(this);
        dButton = (Button) view.findViewById(R.id.buttonD);
        dButton.setOnClickListener(this);
        cButton = (Button) view.findViewById(R.id.buttonC);
        cButton.setOnClickListener(this);
        bButton = (Button) view.findViewById(R.id.buttonB);
        bButton.setOnClickListener(this);
        aButton = (Button) view.findViewById(R.id.buttonA);
        aButton.setOnClickListener(this);
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
        equalsButton = (Button) view.findViewById(R.id.buttonEquals);
        equalsButton.setOnClickListener(this);
        deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(this);
        clearButton = (Button) view.findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonZero:
                txtInput.append("0");
                break;
            case R.id.buttonOne:
                txtInput.append("1");
                break;
            case R.id.buttonTwo:
                txtInput.append("2");
                break;
            case R.id.buttonThree:
                txtInput.append("3");
                break;
            case R.id.buttonFour:
                txtInput.append("4");
                break;
            case R.id.buttonFive:
                txtInput.append("5");
                break;
            case R.id.buttonSix:
                txtInput.append("6");
                break;
            case R.id.buttonSeven:
                txtInput.append("7");
                break;
            case R.id.buttonEight:
                txtInput.append("8");
                break;
            case R.id.buttonNine:
                txtInput.append("9");
                break;
            case R.id.buttonA:
                txtInput.append("A");
                break;
            case R.id.buttonB:
                txtInput.append("B");
                break;
            case R.id.buttonC:
                txtInput.append("C");
                break;
            case R.id.buttonD:
                txtInput.append("D");
                break;
            case R.id.buttonE:
                txtInput.append("E");
                break;
            case R.id.buttonF:
                txtInput.append("F");
                break;
            case R.id.buttonMinus:
                txtResult.append(txtInput.getText().toString() + " - ");
                firstInputString = txtInput.getText().toString();
                txtInput.setText("");
                break;
            case R.id.buttonPlus:
                txtResult.append(txtInput.getText().toString() + " + ");
                txtInput.setText("");
                break;
            case R.id.buttonEquals:
                txtResult.append(txtInput.getText().toString() + "\n=\n");
                txtInput.setText("");
                break;
            case R.id.buttonDivide:
                txtResult.append(txtInput.getText().toString() + " / ");
                txtInput.setText("");
                break;
            case R.id.buttonMultiply:
                txtResult.append(txtInput.getText().toString() + " * ");
                txtInput.setText("");
                break;
            case R.id.buttonDelete:
                int txtInputLength = txtInput.getText().toString().length();
                if (txtInputLength > 0) {
                    userInput = txtInput.getText().toString();
                    txtInput.setText(userInput.substring(0, userInput.length() - 1));
                }
                //int start = txtInput.getText().toString().length();
                //txtInput.setText(txtInput.getEditableText().delete(start - 1,start - 1));
                break;
            case R.id.buttonClear:
                txtInput.setText("");
                txtResult.setText("");
        }
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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_calculator, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
