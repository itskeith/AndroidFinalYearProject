package com.example.keith.finalyearproject;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/

/**
 * Created by Keith on 17/11/2015.
 * ReferenceDetailsActivity checks screen orientation and calls ReferenceDetailsFragment for
 * displaying details
 */
public class ReferenceDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Checks phone orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ReferenceDetailsFragment details = new ReferenceDetailsFragment();

            details.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
//            finish();
//            return;
        }

        //Checks for previous saved state
        if (savedInstanceState == null) {

            ReferenceDetailsFragment details = new ReferenceDetailsFragment();

            details.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
