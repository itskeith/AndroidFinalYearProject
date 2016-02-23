package com.example.keith.finalyearproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/

/**
 * Created by Keith on 12/11/2015.
 * Activity in charge of reference feature
 */
public class ReferenceActivity extends AppCompatActivity {
    /*
    onCreate sets content as activity_reference.xml which contains fragment ReferenceListFragment
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //remembers if there is a previous saved state
        super.onCreate(savedInstanceState);

        //sets content to be displayed based on xml
        setContentView(R.layout.activity_reference);

        //adds toolbar to top of activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // For Setting Logo in toolbar myToolbar.setLogo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_reference, menu);
        return true;
    }

    //Menu item options in toolbar for launching other activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_calculator:
                Intent launchReferencesIntent = new Intent(ReferenceActivity.this, CalculatorActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_converter:
                Intent launchCalculatorIntent = new Intent(ReferenceActivity.this, ConverterActivity.class);
                startActivity(launchCalculatorIntent);
                return true;

            case R.id.home_button:
                Intent launchMainActivityIntent = new Intent(ReferenceActivity.this, MainActivity.class);
                startActivity(launchMainActivityIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
