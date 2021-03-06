package com.example.keith.finalyearproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
public class ConverterDecimalFragment extends Fragment implements View.OnClickListener, TextWatcher {

    TextView txtDecimal, txtBinary, txtHexadecimal, txtOctal;
    Button oneButton, zeroButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton,
            eightButton, nineButton, deleteButton, howtoButton;
    String valueToConvert;
    int decimalVal;
    Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_decimal, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtDecimal = (TextView) view.findViewById(R.id.converterInputDecimal);
        txtBinary = (TextView) view.findViewById(R.id.textViewBinary);
        txtOctal = (TextView) view.findViewById(R.id.textViewOctal);
        txtHexadecimal = (TextView) view.findViewById(R.id.textViewHexadecimal);
        txtDecimal.addTextChangedListener(this);

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

        if (txtDecimal.getText().toString().trim().length() == 0) {
            txtBinary.setText("");
            txtOctal.setText("");
            txtHexadecimal.setText("");
            return;
        } else {
            try {
                decimalVal = Integer.parseInt(txtDecimal.getText().toString());
                txtBinary.setText(Integer.toBinaryString(decimalVal));
                txtOctal.setText(Integer.toOctalString(decimalVal));
                txtHexadecimal.setText(Integer.toHexString(decimalVal).toUpperCase());
            } catch (Exception decimalValueTooLarge) {
                displayToast("Entered Decimal value too large.");
            }
        }
    }

    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.converterButtonHowto:
                valueToConvert = txtDecimal.getText().toString();
                if (valueToConvert.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a value first!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent howtoIntent = new Intent();
                    howtoIntent.setClass(getActivity(), ConverterHowToDecimalActivity.class);

                    howtoIntent.putExtra("value", valueToConvert);
                    startActivity(howtoIntent);
                }
                break;
            case R.id.converterButtonOne:
                txtDecimal.setText(txtDecimal.getText() + "1");
                break;
            case R.id.converterButtonZero:
                txtDecimal.setText(txtDecimal.getText() + "0");
                break;
            case R.id.converterButtonTwo:
                txtDecimal.setText(txtDecimal.getText() + "2");
                break;
            case R.id.converterButtonThree:
                txtDecimal.setText(txtDecimal.getText() + "3");
                break;
            case R.id.converterButtonFour:
                txtDecimal.setText(txtDecimal.getText() + "4");
                break;
            case R.id.converterButtonFive:
                txtDecimal.setText(txtDecimal.getText() + "5");
                break;
            case R.id.converterButtonSix:
                txtDecimal.setText(txtDecimal.getText() + "6");
                break;
            case R.id.converterButtonSeven:
                txtDecimal.setText(txtDecimal.getText() + "7");
                break;
            case R.id.converterButtonEight:
                txtDecimal.setText(txtDecimal.getText() + "8");
                break;
            case R.id.converterButtonNine:
                txtDecimal.setText(txtDecimal.getText() + "9");
                break;
            case R.id.converterButtonDelete:
                int start = txtDecimal.getText().toString().length();
                if (start > 0) {
                    txtDecimal.setText(txtDecimal.getEditableText().delete(start - 1, start));
                } else {
                    return;
                }
                break;
        }
    }
}
