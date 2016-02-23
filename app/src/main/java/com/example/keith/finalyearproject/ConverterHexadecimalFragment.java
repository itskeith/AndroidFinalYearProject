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
        txtHexadecimal.setFilters(new InputFilter[]{filter});

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
                    Toast.makeText(getActivity(), "You can only enter a number or letter from A to F for hexadecimal ", Toast.LENGTH_LONG).show();
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
            return Character.isDigit(c)
                    || c == 'a'
                    || c == 'A'
                    || c == 'b'
                    || c == 'B'
                    || c == 'c'
                    || c == 'C'
                    || c == 'd'
                    || c == 'D'
                    || c == 'e'
                    || c == 'E'
                    || c == 'f'
                    || c == 'F';
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
                Toast.makeText(getActivity(), "Hexadecimal value too large", Toast.LENGTH_SHORT).show();
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
                Intent howtoIntent = new Intent();
                howtoIntent.setClass(getActivity(), ConverterHowToHexadecimalActivity.class);

                howtoIntent.putExtra("value", valueToConvert);
                startActivity(howtoIntent);
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
