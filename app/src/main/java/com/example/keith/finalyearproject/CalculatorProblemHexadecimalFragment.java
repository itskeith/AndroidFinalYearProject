package com.example.keith.finalyearproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Keith on 09/03/2016.
 */
public class CalculatorProblemHexadecimalFragment extends Fragment implements View.OnClickListener {
    Button oneButton, zeroButton, twoButton, threeButton, fourButton, fiveButton, sixButton,
            sevenButton, eightButton, nineButton, aButton, bButton, cButton, dButton, eButton,
            fButton, plusButton, minusButton, multiplyButton, divideButton, deleteButton, checkButton;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_problem_hexadecimal, container, false);

        fButton = (Button) view.findViewById(R.id.buttonF);
        fButton.setOnClickListener(this);
        eButton = (Button) view.findViewById(R.id.buttonE);
        eButton.setOnClickListener(this);
        dButton = (Button) view.findViewById(R.id.buttonD);
        dButton.setOnClickListener(this);
        cButton = (Button) view.findViewById(R.id.buttonC);
        cButton.setOnClickListener(this);
        bButton = (Button) view.findViewById(R.id.buttonB);
        bButton.setOnClickListener(this);
        aButton = (Button) view.findViewById(R.id.buttonA);
        aButton.setOnClickListener(this);
        nineButton = (Button) view.findViewById(R.id.buttonNine);
        nineButton.setOnClickListener(this);
        eightButton = (Button) view.findViewById(R.id.buttonEight);
        eightButton.setOnClickListener(this);
        sevenButton = (Button) view.findViewById(R.id.buttonSeven);
        sevenButton.setOnClickListener(this);
        sixButton = (Button) view.findViewById(R.id.buttonSix);
        sixButton.setOnClickListener(this);
        fiveButton = (Button) view.findViewById(R.id.buttonFive);
        fiveButton.setOnClickListener(this);
        fourButton = (Button) view.findViewById(R.id.buttonFour);
        fourButton.setOnClickListener(this);
        threeButton = (Button) view.findViewById(R.id.buttonThree);
        threeButton.setOnClickListener(this);
        twoButton = (Button) view.findViewById(R.id.buttonTwo);
        twoButton.setOnClickListener(this);
        oneButton = (Button) view.findViewById(R.id.buttonOne);
        oneButton.setOnClickListener(this);
        zeroButton = (Button) view.findViewById(R.id.buttonZero);
        zeroButton.setOnClickListener(this);
        checkButton = (Button) view.findViewById(R.id.buttonCheck);
        checkButton.setOnClickListener(this);
        deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOne:
                getInput("1");
                break;
            case R.id.buttonZero:
                getInput("0");
                break;
            case R.id.buttonCheck:
                getInput("Check");
                break;
            case R.id.buttonDelete:
                getInput("Delete");
                break;
            case R.id.buttonTwo:
                getInput("2");
                break;
            case R.id.buttonThree:
                getInput("3");
                break;
            case R.id.buttonFour:
                getInput("4");
                break;
            case R.id.buttonFive:
                getInput("5");
                break;
            case R.id.buttonSix:
                getInput("6");
                break;
            case R.id.buttonSeven:
                getInput("7");
                break;
            case R.id.buttonEight:
                getInput("8");
                break;
            case R.id.buttonNine:
                getInput("9");
                break;
            case R.id.buttonA:
                getInput("A");
                break;
            case R.id.buttonB:
                getInput("B");
                break;
            case R.id.buttonC:
                getInput("C");
                break;
            case R.id.buttonD:
                getInput("D");
                break;
            case R.id.buttonE:
                getInput("E");
                break;
            case R.id.buttonF:
                getInput("F");
                break;
        }

    }

    /*
    getInput uses interface to communicate this fragments button clicks with calculatorProblemGeneratorActivity
    */
    public void getInput(String input) {
        ((problemGeneratorArrayListener) activity).problemGeneratorArrayActivity(input);
    }

    public interface problemGeneratorArrayListener {
        public void problemGeneratorArrayActivity(String userInput);
    }
}
