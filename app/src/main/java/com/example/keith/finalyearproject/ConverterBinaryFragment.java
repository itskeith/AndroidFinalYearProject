package com.example.keith.finalyearproject;

import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by Keith on 30/11/2015.
 */
public class ConverterBinaryFragment extends Fragment implements TextWatcher {

    EditText txtBinary;
    TextView txtDecimal, txtHexadecimal;
    int decimalVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_binary, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtBinary = (EditText) view.findViewById(R.id.editTextBinary);
        txtDecimal = (TextView) view.findViewById(R.id.textViewDecimal);
        txtHexadecimal = (TextView) view.findViewById(R.id.textViewHexadecimal);
        txtBinary.addTextChangedListener(this);
        txtBinary.setFilters(new InputFilter[]{filter});
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

        if (txtBinary.getText().toString().trim().length() == 0) {
            txtDecimal.setText("");
            return;
        } else {
            calculate(2, txtDecimal);
            decimalVal = Integer.parseInt(txtBinary.getText().toString(), 2);
            txtDecimal.setText(Integer.toString(decimalVal));
            txtHexadecimal.setText(Integer.toHexString(decimalVal));
        }

    }

    public void afterTextChanged(Editable editable) {

    }

    public void calculate(int base, TextView txtView) {


        decimalVal = Integer.parseInt(txtBinary.getText().toString(), base);
        txtDecimal.setText(Integer.toString(decimalVal));
        txtHexadecimal.setText(Integer.toHexString(decimalVal));
    }

}
