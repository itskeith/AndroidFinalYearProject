package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
public class ConverterActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    EditText txtDecimal;
    TextView txtBinary, txtHexadecimal;
    RadioGroup radioGroup;
    RadioButton radioButtonDecimal, radioButtonBinary, radioButtonOctal, radioButtonHexadecimal;
    ImageButton referenceButtonDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        //adds toolbar to top of activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // For Setting Logo in toolbar myToolbar.setLogo();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupConverter);
        radioButtonDecimal = (RadioButton) findViewById(R.id.radioButtonDecimal);
        radioButtonBinary = (RadioButton) findViewById(R.id.radioButtonBinary);
        radioButtonOctal = (RadioButton) findViewById(R.id.radioButtonOctal);
        radioButtonHexadecimal = (RadioButton) findViewById(R.id.radioButtonHexadecimal);
        //radioGroup.setOnClickListener(this);
//        radioButtonDecimal.setChecked(true);
//        txtDecimal = (EditText) findViewById(R.id.editText);
//        txtBinary = (TextView) findViewById(R.id.textView2);
//        txtHexadecimal = (TextView) findViewById(R.id.textView3);
//        txtDecimal.addTextChangedListener(this);

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group,int checkedId) {
//                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonDecimal) {
//                    String choice = radioButtonDecimal.getText().toString();
//                    Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();
//                }
//                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonBinary) {
//                    String choice = radioButtonBinary.getText().toString();
//                    Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();
//                }
//                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonHexadecimal) {
//                    String choice = radioButtonHexadecimal.getText().toString();
//                    Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        referenceButtonDecimal = (ImageButton) findViewById(R.id.decimalReferenceButton);
        referenceButtonDecimal.setOnClickListener(this);
    }


    public void selectDecimal(View view) {
        String choice = radioButtonDecimal.getText().toString();
        Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();

        Fragment newFragment = new ConverterDecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.converter_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void selectBinary(View view) {
        String choice = radioButtonBinary.getText().toString();
        Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();

        Fragment newFragment = new ConverterBinaryFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.converter_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    //Called on radio button click for octal, inflates ConverterOctalFragment
    public void selectOctal(View view){
        String choice = radioButtonOctal.getText().toString();
        Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();

        Fragment newFragment = new ConverterOctalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.converter_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Called on radio button click for hexadecimal, inflates ConverterHexadecimalFragment
    public void selectHexadecimal(View view) {
        String choice = radioButtonHexadecimal.getText().toString();
        Toast.makeText(ConverterActivity.this, "You chose: " + choice, Toast.LENGTH_SHORT).show();

        Fragment newFragment = new ConverterHexadecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.converter_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
//    @Override
//    public void onClick(View v) {
//        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonDecimal) {
//            String choice = radioButtonDecimal.getText().toString();
//
//            Toast.makeText(this, "You chose: " + choice, Toast.LENGTH_LONG).show();
//        }
//        int selectedId = radioGroup.getCheckedRadioButtonId();
//        //radioGroup=(RadioButton)findViewById(selectedId);
//    }

    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        calculate(2, txtBinary);        // for base 2 (binary)
//        //calculate(8, txtOctal);        // for base 8 (octal)
//        calculate(16, txtHexadecimal);    // for base 16 (hexadecimal)
    }

    public void afterTextChanged(Editable editable) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_converter, menu);
        return true;
    }

    //Menu item options in toolbar for launching other activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_references:
                Intent launchReferencesIntent = new Intent(ConverterActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_calculator:
                Intent launchCalculatorIntent = new Intent(ConverterActivity.this, CalculatorActivity.class);
                startActivity(launchCalculatorIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(ConverterActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.decimalReferenceButton:
                //TODO
                break;
        }
    }
//    public void calculate(int base, TextView txtView) {
//        if (txtDecimal.getText().toString().trim().length() == 0) {
//            txtView.setText("");
//            return;
//        }
//        try {
//            Stack<Object> stack = new Stack<Object>();
//            int number = Integer.parseInt(txtDecimal.getText().toString());
//            while (number > 0) {
//                int remainder = number % base;
//                if (remainder < 10) {
//                    stack.push(remainder);
//                } else {
//                    switch (remainder) {
//                        case 10:
//                            stack.push("A");
//                            break;
//                        case 11:
//                            stack.push("B");
//                            break;
//                        case 12:
//                            stack.push("C");
//                            break;
//                        case 13:
//                            stack.push("D");
//                            break;
//                        case 14:
//                            stack.push("E");
//                            break;
//                        case 15:
//                            stack.push("F");
//                            break;
//                    }
//                }
//                number /= base;
//            }
//            StringBuffer buffer = new StringBuffer();
//            while (!stack.isEmpty()) {
//                buffer.append(stack.pop().toString());
//            }
//            txtView.setText(buffer.toString());
//        } catch (Exception e) {
//            txtView.setText(e.getMessage());
//        }
//
//    }
}