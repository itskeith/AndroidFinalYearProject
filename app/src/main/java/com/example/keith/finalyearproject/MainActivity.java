package com.example.keith.finalyearproject;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // For Setting Logo in toolbar myToolbar.setLogo();
    }

    /*
    showReference called on user click of Reference button
    displays a reference guide for users, containing information on various gates etc.
     */
    public void showReference(View view) {
        Intent intent = new Intent(this, ReferenceActivity.class);
        startActivity(intent);
    }

    /*
    showConverter called on user click of Converter button
    presents user with a unit converter for converting between binary, decimal and hexadecimal
     */
    public void showConverter(View view) {
        Intent intent = new Intent(this, ConverterActivity.class);
        startActivity(intent);
    }

    /*
    showCalculator called on user click of Calculator button
    presents user with a calculator for binary and hexadecimal
     */
    public void showCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Menu item options in toolbar for launching other activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_references:
                Intent launchReferencesIntent = new Intent(MainActivity.this, ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_calculator:
                Intent launchCalculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(launchCalculatorIntent);
                return true;

            case R.id.action_converter:
                Intent launchNewIntent = new Intent(MainActivity.this, ConverterActivity.class);
                startActivity(launchNewIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}