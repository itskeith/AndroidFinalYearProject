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

/**
 * ConverterOctalFragment Fragment for the Octal converter portion of the converter activity
 */
public class ConverterOctalFragment extends Fragment implements View.OnClickListener, TextWatcher {

    TextView txtOctal, txtBinary, txtHexadecimal, txtDecimal;
    Button oneButton, zeroButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton,
            deleteButton, howtoButton;
    String octalVal, valueToConvert;
    int binaryVal, decimalVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_octal, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtOctal = (TextView) view.findViewById(R.id.converterInputOctal);
        txtBinary = (TextView) view.findViewById(R.id.textViewBinary);
        txtHexadecimal = (TextView) view.findViewById(R.id.textViewHexadecimal);
        txtDecimal = (TextView) view.findViewById(R.id.textViewDecimal);
        txtOctal.addTextChangedListener(this);
        txtOctal.setFilters(new InputFilter[]{filter});

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

        deleteButton = (Button) view.findViewById(R.id.converterButtonDelete);
        deleteButton.setOnClickListener(this);

        howtoButton = (Button) view.findViewById(R.id.converterButtonHowto);
        howtoButton.setOnClickListener(this);

        return view;
    }

    InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            boolean keepOriginal = true;
            StringBuilder sb = new StringBuilder(end - start);
            for (int i = start; i < end; i++) {
                char c = source.charAt(i);
                if (isCharAllowed(c)) // put your condition here
                    sb.append(c);
                else
                    Toast.makeText(getActivity(), "You can only enter a number from 0 to 7 for octal", Toast.LENGTH_LONG).show();
                keepOriginal = false;
            }
            if (keepOriginal)
                return null;
            else {
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                } else {
                    return sb;
                }
            }
        }

        private boolean isCharAllowed(char c) {
            return c == '0'
                    || c == '1'
                    || c == '2'
                    || c == '3'
                    || c == '4'
                    || c == '5'
                    || c == '6'
                    || c == '7';
        }
    };
    //    @Override
//    public void onClick(View v) {
//        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonDecimal) {
//            String choice = radioButtonDecimal.getText().toString();
//
//            Toast.makeText(this, "You chose: " + choice, Toast.LENGTH_LONG).show();
//        }
//        int selectedId = radioGroup.getCheckedRadioButtonId();
//        //radioGroup=(RadioButton)findViewById(selectedId);111111111111111
//    }

    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        octalVal = txtOctal.getText().toString();
        //Check if input is empty
        if (octalVal.trim().length() == 0) {
            txtDecimal.setText("");
            txtBinary.setText("");
            txtHexadecimal.setText("");
            return;
        } else {
            try {
                //Convert inputted hexadecimal value to decimal
                decimalVal = Integer.parseInt(octalVal, 8);
                //Set decimal value in textview
                txtDecimal.setText(Integer.toString(decimalVal));
                //Set binary value in textview
                txtBinary.setText(Integer.toBinaryString(decimalVal));
                //Set hexadecimal value in textview
                txtHexadecimal.setText(Integer.toHexString(decimalVal).toUpperCase());
            } catch (Exception decimalValueTooLarge) {
                Toast.makeText(getActivity(), "Octal value too large", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void afterTextChanged(Editable editable) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.converterButtonHowto:
                valueToConvert = txtOctal.getText().toString();
                Intent howtoIntent = new Intent();
                howtoIntent.setClass(getActivity(), ConverterHowToOctalActivity.class);

                howtoIntent.putExtra("value", valueToConvert);
                startActivity(howtoIntent);
                break;
            case R.id.converterButtonZero:
                txtOctal.setText(txtOctal.getText() + "0");
                break;
            case R.id.converterButtonOne:
                txtOctal.setText(txtOctal.getText() + "1");
                break;
            case R.id.converterButtonTwo:
                txtOctal.setText(txtOctal.getText() + "2");
                break;
            case R.id.converterButtonThree:
                txtOctal.setText(txtOctal.getText() + "3");
                break;
            case R.id.converterButtonFour:
                txtOctal.setText(txtOctal.getText() + "4");
                break;
            case R.id.converterButtonFive:
                txtOctal.setText(txtOctal.getText() + "5");
                break;
            case R.id.converterButtonSix:
                txtOctal.setText(txtOctal.getText() + "6");
                break;
            case R.id.converterButtonSeven:
                txtOctal.setText(txtOctal.getText() + "7");
                break;
            case R.id.converterButtonEight:
                txtOctal.setText(txtOctal.getText() + "8");
                break;
            case R.id.converterButtonNine:
                txtOctal.setText(txtOctal.getText() + "9");
                break;
            case R.id.converterButtonDelete:
                int start = txtOctal.getText().toString().length();
                if (start > 0) {
                    txtOctal.setText(txtOctal.getEditableText().delete(start - 1, start));
                } else {
                    return;
                }
                break;
        }
    }
}