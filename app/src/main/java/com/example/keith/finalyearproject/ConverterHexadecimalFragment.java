package com.example.keith.finalyearproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
public class ConverterHexadecimalFragment extends Fragment implements View.OnClickListener, TextWatcher {

    TextView txtBinary, txtHexadecimal, txtDecimal, txtOctal;
    Button oneButton, zeroButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton,
            eightButton, nineButton, aButton, bButton, cButton, dButton, eButton, fButton, deleteButton,
            howtoButton;
    String hexVal, valueToConvert;
    int binaryVal, decimalVal;
    Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_hexadecimal, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtHexadecimal = (TextView) view.findViewById(R.id.converterInputHexadecimal);
        txtBinary = (TextView) view.findViewById(R.id.textViewBinary);
        txtOctal = (TextView) view.findViewById(R.id.textViewOctal);
        txtDecimal = (TextView) view.findViewById(R.id.textViewDecimal);
        txtHexadecimal.addTextChangedListener(this);

        oneButton = (Button) view.findViewById(R.id.converterButtonOne);
        oneButton.setOnClickListener(this);
        zeroButton = (Button) view.findViewById(R.id.converterButtonZero);
        zeroButton.setOnClickListener(this);
        twoButton = (Button) view.findViewById(R.id.converterButtonTwo);
        twoButton.setOnClickListener(this);
        threeButton = (Button) view.findViewById(R.id.converterButtonThree);
        threeButton.setOnClickListener(this);
        fourButton = (Button) view.findViewById(R.id.converterButtonFour);
        fourButton.setOnClickListener(this);
        fiveButton = (Button) view.findViewById(R.id.converterButtonFive);
        fiveButton.setOnClickListener(this);
        sixButton = (Button) view.findViewById(R.id.converterButtonSix);
        sixButton.setOnClickListener(this);
        sevenButton = (Button) view.findViewById(R.id.converterButtonSeven);
        sevenButton.setOnClickListener(this);
        eightButton = (Button) view.findViewById(R.id.converterButtonEight);
        eightButton.setOnClickListener(this);
        nineButton = (Button) view.findViewById(R.id.converterButtonNine);
        nineButton.setOnClickListener(this);
        aButton = (Button) view.findViewById(R.id.converterButtonA);
        aButton.setOnClickListener(this);
        bButton = (Button) view.findViewById(R.id.converterButtonB);
        bButton.setOnClickListener(this);
        cButton = (Button) view.findViewById(R.id.converterButtonC);
        cButton.setOnClickListener(this);
        dButton = (Button) view.findViewById(R.id.converterButtonD);
        dButton.setOnClickListener(this);
        eButton = (Button) view.findViewById(R.id.converterButtonE);
        eButton.setOnClickListener(this);
        fButton = (Button) view.findViewById(R.id.converterButtonF);
        fButton.setOnClickListener(this);

        deleteButton = (Button) view.findViewById(R.id.converterButtonDelete);
        deleteButton.setOnClickListener(this);

        howtoButton = (Button) view.findViewById(R.id.converterButtonHowto);
        howtoButton.setOnClickListener(this);

        return view;
    }

    /*
        * Displays only one toast message at a time, preventing excessive messages displaying if user
        * keeps hitting button triggering the toast
        * */
    public void displayToast(String message) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        hexVal = txtHexadecimal.getText().toString();
        //Check if input is empty
        if (hexVal.trim().length() == 0) {
            txtDecimal.setText("");
            txtBinary.setText("");
            txtOctal.setText("");
            return;
        } else {
            try {
                //Convert inputted hexadecimal value to decimal
                decimalVal = Integer.parseInt(hexVal, 16);
                //Set decimal value in textview
                txtDecimal.setText(Integer.toString(decimalVal));
                //Set binary value in textview
                txtBinary.setText(Integer.toBinaryString(decimalVal));
                //Set octal value in textView
                txtOctal.setText(Integer.toOctalString(decimalVal));
            } catch (Exception decimalTooLarge) {
               displayToast("Entered Hexadecimal value too large.");
            }
        }


    }

    public void afterTextChanged(Editable editable) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.converterButtonHowto:
                valueToConvert = txtHexadecimal.getText().toString();
                if (valueToConvert.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a value first!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent howtoIntent = new Intent();
                    howtoIntent.setClass(getActivity(), ConverterHowToHexadecimalActivity.class);

                    howtoIntent.putExtra("value", valueToConvert);
                    startActivity(howtoIntent);
                }
                break;
            case R.id.converterButtonZero:
                txtHexadecimal.setText(txtHexadecimal.getText() + "0");
                break;
            case R.id.converterButtonOne:
                txtHexadecimal.setText(txtHexadecimal.getText() + "1");
                break;
            case R.id.converterButtonTwo:
                txtHexadecimal.setText(txtHexadecimal.getText() + "2");
                break;
            case R.id.converterButtonThree:
                txtHexadecimal.setText(txtHexadecimal.getText() + "3");
                break;
            case R.id.converterButtonFour:
                txtHexadecimal.setText(txtHexadecimal.getText() + "4");
                break;
            case R.id.converterButtonFive:
                txtHexadecimal.setText(txtHexadecimal.getText() + "5");
                break;
            case R.id.converterButtonSix:
                txtHexadecimal.setText(txtHexadecimal.getText() + "6");
                break;
            case R.id.converterButtonSeven:
                txtHexadecimal.setText(txtHexadecimal.getText() + "7");
                break;
            case R.id.converterButtonEight:
                txtHexadecimal.setText(txtHexadecimal.getText() + "8");
                break;
            case R.id.converterButtonNine:
                txtHexadecimal.setText(txtHexadecimal.getText() + "9");
                break;
            case R.id.converterButtonA:
                txtHexadecimal.setText(txtHexadecimal.getText() + "A");
                break;
            case R.id.converterButtonB:
                txtHexadecimal.setText(txtHexadecimal.getText() + "B");
                break;
            case R.id.converterButtonC:
                txtHexadecimal.setText(txtHexadecimal.getText() + "C");
                break;
            case R.id.converterButtonD:
                txtHexadecimal.setText(txtHexadecimal.getText() + "D");
                break;
            case R.id.converterButtonE:
                txtHexadecimal.setText(txtHexadecimal.getText() + "E");
                break;
            case R.id.converterButtonF:
                txtHexadecimal.setText(txtHexadecimal.getText() + "F");
                break;
            case R.id.converterButtonDelete:
                int start = txtHexadecimal.getText().toString().length();
                if (start > 0) {
                    txtHexadecimal.setText(txtHexadecimal.getEditableText().delete(start - 1, start));
                } else {
                    return;
                }
                break;
        }
    }
}
