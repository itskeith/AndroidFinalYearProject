package com.example.keith.finalyearproject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/*
Created by: Keith Ryan
Student Number: 11125268
Electronic And Computer Engineering(LM118) 4th year
Final Year Project
*/

/**
 * ReferenceDetailsFragment is responsible for displaying the details of the reference item chosen
 * by the user
 */
public class ReferenceDetailsFragment extends Fragment {

    public static ReferenceDetailsFragment newInstance(int index) {

        ReferenceDetailsFragment f = new ReferenceDetailsFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);

        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Declare scroller and set layout Parameters
        ScrollView scroller = new ScrollView(getActivity());
        ScrollView.LayoutParams scrollerLayoutParams = new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scroller.setLayoutParams(scrollerLayoutParams);

        LinearLayout dataLinearLayout = new LinearLayout(getActivity());
        dataLinearLayout.setOrientation(LinearLayout.VERTICAL);

        //Set layout parameters for images added to scrollView
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutParams.gravity = Gravity.CENTER;
        //Set padding for images
        int padding = (int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                        getActivity().getResources().getDisplayMetrics());

        //Creates imageView image for displaying details
        ImageView image = new ImageView(getActivity());
        image.setLayoutParams(linearLayoutParams);
        //image.setPadding(padding, padding, padding, padding);
        ImageView.ScaleType CENTER;
        dataLinearLayout.addView(image);

        image.setImageResource(ReferenceInfo.GATESPIC[getShownIndex()]);
        //End of imageView image

        //Creates imageview imageView image2 for displaying details
        ImageView image2 = new ImageView(getActivity());
        image2.setPadding(padding, padding, padding, padding);

        //dataLinearLayout.addView(image);
        dataLinearLayout.addView(image2);

        image2.setImageResource(ReferenceInfo.GATESTT[getShownIndex()]);
        //End of imageView image2

        TextView textView = new TextView(getActivity());

        textView.setPadding(padding, padding, padding, padding);
        textView.setGravity(Gravity.CENTER);
        dataLinearLayout.addView(textView);
        textView.setText(ReferenceInfo.TEXTINFO[getShownIndex()]);
        scroller.addView(dataLinearLayout);

        return scroller;
    }
}
