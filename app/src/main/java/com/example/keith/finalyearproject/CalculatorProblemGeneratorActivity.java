package com.example.keith.finalyearproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Keith on 08/03/2016.
 */
public class CalculatorProblemGeneratorActivity extends AppCompatActivity implements CalculatorProblemBinaryFragment.problemGeneratorArrayListener, CalculatorProblemHexadecimalFragment.problemGeneratorArrayListener {
    TextView txtNumber, txtOperator, txtSecondNumber, txtAnswer, txtAnswerCheck;
    String mode, userInput;
    LinearLayout secondNumberLayout, answerLayout;
    int firstNumber, secondNumber, operator, answer, setProblemFormat, start;
    Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_problem_generator);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Educational Binary Learning App");
        txtNumber = (TextView) findViewById(R.id.textViewNumber);
        txtOperator = (TextView) findViewById(R.id.textViewOperator);
        txtSecondNumber = (TextView) findViewById(R.id.textViewSecondNumber);
        txtAnswer = (TextView) findViewById(R.id.textViewAnswer);
        txtAnswerCheck = (TextView) findViewById(R.id.textViewCheckAnswer);
        secondNumberLayout = (LinearLayout) findViewById(R.id.linearLayoutSecondNumber);
        answerLayout = (LinearLayout) findViewById(R.id.linearLayoutAnswer);
        generateBinaryProblem(findViewById(android.R.id.content));
    }

    public void problemGeneratorArrayActivity(String input) {
        switch (input) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "A":
            case "B":
            case "C":
            case "D":
            case "E":
            case "F":
                if (setProblemFormat == 0) {
                    txtAnswerCheck.setText("Press check to check your answer.");
                    txtSecondNumber.append(input.toUpperCase());
                } else {
                    txtAnswerCheck.setText("Press check to check your answer.");
                    txtAnswer.append(input.toUpperCase());
                }
                break;
            case "Delete":
                if (setProblemFormat == 0) {
                    start = txtSecondNumber.getText().toString().length();
                } else {
                    start = txtAnswer.getText().toString().length();
                }
                if (start > 0) {
                    if (setProblemFormat == 0) {
                        userInput = txtSecondNumber.getText().toString();
                        userInput = userInput.substring(0, userInput.length()-1);
                        txtSecondNumber.setText(userInput.toUpperCase());
                        txtAnswerCheck.setText("Press check to check your answer.");
                        if (txtSecondNumber.getText().toString().isEmpty()) {
                            txtAnswerCheck.setText("Press check to check your answer.");
                        }
                    } else {
                        userInput = txtAnswer.getText().toString();
                        userInput = userInput.substring(0, userInput.length()-1);
                        txtAnswer.setText(userInput.toUpperCase());
                        txtAnswerCheck.setText("Press check to check your answer.");
                        if (txtAnswer.getText().toString().isEmpty()) {
                            txtAnswerCheck.setText("Press check to check your answer.");
                        }
                    }
                } else {
                    return;
                }
                break;
            case "Check":
                if (setProblemFormat == 0) {
                    userInput = txtSecondNumber.getText().toString();
                } else {
                    userInput = txtAnswer.getText().toString();
                }

                if (mode == "Binary") {
                    try {
                        if (setProblemFormat == 0) {
                            if (Integer.parseInt(userInput, 2) == secondNumber) {
                                txtAnswerCheck.setText("Correct!");
                                displayToast("Try generating a new problem");
                            } else {
                                txtAnswerCheck.setText("Incorrect, try again!");
                                displayToast("Try again or generate a new problem");
                            }
                        } else {
                            if (Integer.parseInt(userInput, 2) == answer) {
                                txtAnswerCheck.setText("Correct!");
                                displayToast("Try generating a new problem");
                            } else {
                                txtAnswerCheck.setText("Incorrect, try again!");
                                displayToast("Try again or generate a new problem");
                            }
                        }
                    } catch (Exception e) {
                        displayToast("Please enter a number before pressing Check.");
                    }
                } else if (mode == "Hexadecimal") {
                    try {
                        if (setProblemFormat == 0) {
                            if (Integer.parseInt(userInput, 16) == secondNumber) {
                                txtAnswerCheck.setText("Correct!");
                                displayToast("Try generating a new problem");
                            } else {
                                txtAnswerCheck.setText("Incorrect, try again!");
                                displayToast("Try again or generate a new problem");
                            }
                        } else {
                            if (Integer.parseInt(userInput, 16) == answer) {
                                txtAnswerCheck.setText("Correct!");
                                displayToast("Try generating a new problem");
                            } else {
                                txtAnswerCheck.setText("Incorrect, try again!");
                                displayToast("Try again or generate a new problem");
                            }
                        }
                    } catch (Exception e) {
                        displayToast("Please enter a number before pressing Check.");
                    }
                }
                break;
        }
    }

    public void generateBinaryProblem(View view) {
        Fragment binaryProblemFragment = new CalculatorProblemBinaryFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_problem_frame, binaryProblemFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        mode = "Binary";
        txtAnswerCheck.setText("Press check to check your answer.");
        txtSecondNumber.setText("");
        setRandomProblem();
    }


    public void generateHexadecimalProblem(View view) {
        Fragment hexadecimalProblemFragment = new CalculatorProblemHexadecimalFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.calculator_problem_frame, hexadecimalProblemFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        mode = "Hexadecimal";
        txtAnswerCheck.setText("Press check to check your answer.");
        txtSecondNumber.setText("");
        setRandomProblem();
    }

    public void showSolution(View view) {
        if (mode == "Binary") {
            txtNumber.setText(Integer.toBinaryString(firstNumber));
            txtSecondNumber.setText(Integer.toBinaryString(secondNumber));
            txtAnswer.setText(Integer.toBinaryString(answer));
        } else{
            txtNumber.setText(Integer.toHexString(firstNumber).toUpperCase());
            txtSecondNumber.setText(Integer.toHexString(secondNumber).toUpperCase());
            txtAnswer.setText(Integer.toHexString(answer).toUpperCase());
        }
    }

    public void setRandomProblem() {
        Random rand = new Random();

        setProblemFormat = rand.nextInt(2);
        firstNumber = rand.nextInt(127);
        operator = rand.nextInt(3);
        secondNumber = rand.nextInt(127);
        if (setProblemFormat == 0) {
            secondNumberLayout.setBackgroundResource(R.color.light_grey);
            txtSecondNumber.setTextColor(getResources().getColor(R.color.dark_orange));
            answerLayout.setBackgroundResource(R.color.orange);
            txtAnswer.setTextColor(getResources().getColor(R.color.black));
        } else {
            secondNumberLayout.setBackgroundResource(R.color.orange);
            txtSecondNumber.setTextColor(getResources().getColor(R.color.black));
            answerLayout.setBackgroundResource(R.color.light_grey);
            txtAnswer.setTextColor(getResources().getColor(R.color.dark_orange));
        }
        if (mode == "Binary") {
            txtNumber.setText(Integer.toBinaryString(firstNumber));
            switch (operator) {
                case 0:
                    txtOperator.setText("+");
                    answer = firstNumber + secondNumber;
                    if (setProblemFormat == 0) {
                        txtAnswer.setText(Integer.toBinaryString(answer));
                    } else {
                        txtSecondNumber.setText(Integer.toBinaryString(secondNumber));
                        txtAnswer.setText("");
                    }
                    break;
                case 1:
                    txtOperator.setText("-");
                    answer = firstNumber - secondNumber;
                    if (setProblemFormat == 0) {
                        txtAnswer.setText(Integer.toBinaryString(answer));
                    } else {
                        txtSecondNumber.setText(Integer.toBinaryString(secondNumber));
                        txtAnswer.setText("");
                    }
                    break;
                case 2:
                    txtOperator.setText("*");
                    answer = firstNumber * secondNumber;
                    if (setProblemFormat == 0) {
                        txtAnswer.setText(Integer.toBinaryString(answer));
                    } else {
                        txtSecondNumber.setText(Integer.toBinaryString(secondNumber));
                        txtAnswer.setText("");
                    }
                    break;
            }
        } else if (mode == "Hexadecimal") {
            txtNumber.setText(Integer.toHexString(firstNumber).toUpperCase());
            switch (operator) {
                case 0:
                    txtOperator.setText("+");
                    answer = firstNumber + secondNumber;
                    if (setProblemFormat == 0) {
                        txtAnswer.setText(Integer.toHexString(answer).toUpperCase());
                    } else {
                        txtSecondNumber.setText(Integer.toHexString(secondNumber).toUpperCase());
                        txtAnswer.setText("");
                    }
                    break;
                case 1:
                    txtOperator.setText("-");
                    answer = firstNumber - secondNumber;
                    if (setProblemFormat == 0) {
                        txtAnswer.setText(Integer.toHexString(answer).toUpperCase());
                    } else {
                        txtSecondNumber.setText(Integer.toHexString(secondNumber).toUpperCase());
                        txtAnswer.setText("");
                    }
                    break;
                case 2:
                    txtOperator.setText("*");
                    answer = firstNumber * secondNumber;
                    if (setProblemFormat == 0) {
                        txtAnswer.setText(Integer.toHexString(answer).toUpperCase());
                    } else {
                        txtSecondNumber.setText(Integer.toHexString(secondNumber).toUpperCase());
                        txtAnswer.setText("");
                    }
                    break;
            }
        }

    }

    /*
    * Displays only one toast message at a time, preventing excessive messages displaying if user
    * keeps hitting button triggering the toast
    * */
    public void displayToast(String message) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, menu);
        return true;
    }

    //Menu item options in toolbar for launching other activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_references:
                Intent launchReferencesIntent = new Intent(CalculatorProblemGeneratorActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchConverterIntent = new Intent(CalculatorProblemGeneratorActivity.this, ConverterActivity.class);
                startActivity(launchConverterIntent);
                return true;

            case R.id.action_designer:
                Intent launchDesignerIntent = new Intent(CalculatorProblemGeneratorActivity.this, LogicDesignerActivity.class);
                startActivity(launchDesignerIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(CalculatorProblemGeneratorActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
