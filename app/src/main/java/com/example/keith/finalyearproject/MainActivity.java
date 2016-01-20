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
                Intent launchReferencesIntent = new Intent(MainActivity.this,ReferenceActivity.class);
                startActivity(launchReferencesIntent);
                return true;

            case R.id.action_calculator:
                Intent launchCalculatorIntent = new Intent(MainActivity.this,CalculatorActivity.class);
                startActivity(launchCalculatorIntent);
                return true;

            case R.id.action_converter:
                Intent launchNewIntent = new Intent(MainActivity.this,ConverterActivity.class);
                startActivity(launchNewIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
/*
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // If we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Configuration configuration = getResources().getConfiguration();

            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                FragmentLandscape fragmentLandscape = new FragmentLandscape();

                fragmentTransaction.replace(android.R.id.content, fragmentLandscape);
            } else {

                FragmentPortrait fragmentPortrait = new FragmentPortrait();

                fragmentTransaction.replace(android.R.id.content, fragmentPortrait);
            }
            fragmentTransaction.commit();
        }
    }
}

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/