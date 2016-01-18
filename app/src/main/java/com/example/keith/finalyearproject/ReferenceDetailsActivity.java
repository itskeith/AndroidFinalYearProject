package com.example.keith.finalyearproject;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

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

            finish();
            return;
        }

        //Checks for previous saved state
        if(savedInstanceState == null){

            ReferenceDetailsFragment details = new ReferenceDetailsFragment();

            details.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content,details).commit();
        }
    }
}
