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
public class ConverterBinaryFragment extends Fragment implements View.OnClickListener, TextWatcher {

    TextView txtDecimal, txtBinary, txtHexadecimal, txtOctal;
    Button oneButton, zeroButton, deleteButton, howtoButton;
    String valueToConvert;
    int decimalVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_binary, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtBinary = (TextView) view.findViewById(R.id.converterInputBinary);
        txtDecimal = (TextView) view.findViewById(R.id.textViewDecimal);
        txtHexadecimal = (TextView) view.findViewById(R.id.textViewHexadecimal);
        txtOctal = (TextView) view.findViewById(R.id.textViewOctal);
        txtBinary.addTextChangedListener(this);
        txtBinary.setFilters(new InputFilter[]{filter});
        oneButton = (Button) view.findViewById(R.id.converterButtonOne);
        oneButton.setOnClickListener(this);
        zeroButton = (Button) view.findViewById(R.id.converterButtonZero);
        zeroButton.setOnClickListener(this);
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
                    Toast.makeText(getActivity(), "You can only enter a 1 or a 0 when converting from binary.", Toast.LENGTH_LONG).show();
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
            return c == '1'
                    || c == '0';
        }
    };


    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //If no values are input representing a binary value then all textviews display nothing
        if (txtBinary.getText().toString().trim().length() == 0) {
            txtDecimal.setText("");
            txtHexadecimal.setText("");
            txtOctal.setText("");
            return;
        } else {
            //calculate(2, txtDecimal);
            try {
                decimalVal = Integer.parseInt(txtBinary.getText().toString(), 2);
                txtDecimal.setText(Integer.toString(decimalVal));
                txtHexadecimal.setText(Integer.toHexString(decimalVal));
                txtOctal.setText(Integer.toOctalString(decimalVal));
            } catch (Exception decimalTooLarge) {
                Toast.makeText(getActivity(), "Binary value too large", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.converterButtonHowto:
                valueToConvert = txtBinary.getText().toString();
                Intent howtoIntent = new Intent();
                howtoIntent.setClass(getActivity(), ConverterHowtoBinaryActivity.class);

                howtoIntent.putExtra("value",valueToConvert);
                startActivity(howtoIntent);
                break;
            case R.id.converterButtonOne:
                txtBinary.setText(txtBinary.getText() + "1");
                break;
            case R.id.converterButtonZero:
                txtBinary.setText(txtBinary.getText() + "0");
                break;
            case R.id.converterButtonDelete:
                int start = txtBinary.getText().toString().length();
                if (start > 0) {
                    txtBinary.setText(txtBinary.getEditableText().delete(start - 1, start));
                } else {
                    return;
                }
                break;
        }
    }

   /* Believe this is redundant now
   public void calculate(int base, TextView txtView) {


        decimalVal = Integer.parseInt(txtBinary.getText().toString(), base);
        txtDecimal.setText(Integer.toString(decimalVal));
        txtHexadecimal.setText(Integer.toHexString(decimalVal));
        txtOctal.setText(Integer.toOctalString(decimalVal));
    }*/

}
