package com.example.keith.finalyearproject;

import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by Keith on 25/11/2015.
 */
public class ConverterDecimalFragment extends Fragment implements TextWatcher {

    EditText txtDecimal;
    TextView txtBinary, txtHexadecimal;
    int decimalVal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter_decimal, container, false);
        //radioGroup.setOnClickListener(this);
        //radioButtonDecimal.setChecked(true);
        txtDecimal = (EditText) view.findViewById(R.id.editText);
        txtBinary = (TextView) view.findViewById(R.id.textView2);
        txtHexadecimal = (TextView) view.findViewById(R.id.textView3);
        txtDecimal.addTextChangedListener(this);
        txtDecimal.setFilters(new InputFilter[]{filter});
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
                    Toast.makeText(getActivity(), "You can only enter numbers when converting from decimal", Toast.LENGTH_LONG).show();
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
            return Character.isDigit(c);
        }
    };

    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (txtDecimal.getText().toString().trim().length() == 0) {
            txtBinary.setText("");
            txtHexadecimal.setText("");
            return;
        }
        else{

            decimalVal = Integer.parseInt(txtDecimal.getText().toString());
            txtBinary.setText(Integer.toBinaryString(decimalVal));
            txtHexadecimal.setText(Integer.toHexString(decimalVal));
        }
    }

    public void afterTextChanged(Editable editable) {

    }

}
