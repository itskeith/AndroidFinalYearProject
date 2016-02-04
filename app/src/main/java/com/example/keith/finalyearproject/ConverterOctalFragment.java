package com.example.keith.finalyearproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
/**
 *
 * ConverterOctalFragment Fragment for the Octal converter portion of the converter activity
 */
public class ConverterOctalFragment extends Fragment implements TextWatcher {

    EditText txtOctal;
    TextView txtBinary, txtHexadecimal, txtDecimal;
    String octalVal;
    int binaryVal, decimalVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_octal, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtOctal = (EditText) view.findViewById(R.id.editTextOctal);
        txtBinary = (TextView) view.findViewById(R.id.textViewBinary);
        txtHexadecimal = (TextView) view.findViewById(R.id.textViewHexadecimal);
        txtDecimal = (TextView) view.findViewById(R.id.textViewDecimal);
        txtOctal.addTextChangedListener(this);
        txtOctal.setFilters(new InputFilter[]{filter});
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
            //Convert inputted hexadecimal value to decimal
            decimalVal = Integer.parseInt(octalVal, 8);
            //Set decimal value in textview
            txtDecimal.setText(Integer.toString(decimalVal));
            //Set binary value in textview
            txtBinary.setText(Integer.toBinaryString(decimalVal));
            //Set hexadecimal value in textview
            txtHexadecimal.setText(Integer.toHexString(decimalVal));
        }


    }

    public void afterTextChanged(Editable editable) {

    }


}