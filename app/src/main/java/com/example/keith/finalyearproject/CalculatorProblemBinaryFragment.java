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
public class CalculatorProblemBinaryFragment extends Fragment implements View.OnClickListener {
    Button oneButton, zeroButton, deleteButton, checkButton;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_problem_binary, container, false);

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
