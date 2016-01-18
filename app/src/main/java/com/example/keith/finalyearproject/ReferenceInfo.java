package com.example.keith.finalyearproject;

import android.graphics.drawable.Drawable;

/**
 * Created by Keith on 17/11/2015.
 * Activity called for information from other reference activities and fragments
 */
public class ReferenceInfo {

    public static final String[] GATES =
            {
                    "AND",
                    "OR",
                    "NOT",
                    "NAND",
                    "NOR",
                    "XOR",
                    "XNOR"
            };

        /*
        Array containing logic gate images
         */
    public static final int[] GATESPIC =
            {
                    R.drawable.and,
                    R.drawable.orgate,
                    R.drawable.and,
                    R.drawable.nandgate,
                    R.drawable.norgate,
                    R.drawable.xorgate,
                    R.drawable.xorgate
            };

    public static final int[] GATESTT =
            {
                    R.drawable.andtable,
                    R.drawable.andtable,
                    R.drawable.andtable,
                    R.drawable.andtable,
                    R.drawable.andtable,
                    R.drawable.andtable,
                    R.drawable.andtable
            };
}
