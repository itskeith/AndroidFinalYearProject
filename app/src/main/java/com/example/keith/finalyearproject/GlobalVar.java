package com.example.keith.finalyearproject;

import android.app.Application;

/**
 * Created by Keith on 16/02/2016.
 */
public class GlobalVar extends Application {
    //Global variable for position of seekbar for 2's complement
    public static int position;
    //Global variables for postion of seekbar's for fixed point
    public static int positionLeft, positionRight;
    //Global boolean variable for fixedpoint being enabled or not
    public static boolean fixedPointEnabled;
}
