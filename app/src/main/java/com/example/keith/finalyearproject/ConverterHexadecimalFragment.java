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

/**
 * Created by Keith on 30/11/2015.
 */
public class ConverterHexadecimalFragment extends Fragment implements TextWatcher {

    EditText txtHexadecimal;
    TextView txtBinary, txtDecimal;
    String hexVal;
    int binaryVal, decimalVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_hexadecimal, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtHexadecimal = (EditText) view.findViewById(R.id.editTextHexadecimal);
        txtBinary = (TextView) view.findViewById(R.id.textViewBinary);
        txtDecimal = (TextView) view.findViewById(R.id.textViewDecimal);
        txtHexadecimal.addTextChangedListener(this);
        txtHexadecimal.setFilters(new InputFilter[]{filter});
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
            return;
        }
        else{
            //Convert inputted hexadecimal value to decimal
            decimalVal = Integer.parseInt(hexVal,16);
            //Set decimal value in textview
            txtDecimal.setText(Integer.toString(decimalVal));
            //Set binary value in textview
            txtBinary.setText(Integer.toBinaryString(decimalVal));
        }


    }

    public void afterTextChanged(Editable editable) {

    }



}
