package com.example.keith.finalyearproject;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Keith on 12/11/2015.
 * ReferenceListFragment shows user a list of reference information
 * Depending on device orientation, it displays a list only if in portrait mode and the list and
 * details simultaneously if in landscape mode
 */
public class ReferenceListFragment extends ListFragment {

    // True/False depending on screen orientation (sets whether to display list and content on same
    // page or two seperate pages
    boolean mDuelPane;

    // Current selected reference item (for saving app state)
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ArrayAdapter takes string values from ReferenceInfo
        ArrayAdapter<String> gateStringArrayToList = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                ReferenceInfo.GATES);

        // Populates ListView with data
        setListAdapter(gateStringArrayToList);

        View referenceDetailsFrame = getActivity().findViewById(R.id.referencedetails);

        // Sets mDuelPane to true or false depending on what the layout currently is
        // Check if referenceDetailsFrame exists and if it is visible
        mDuelPane = referenceDetailsFrame != null
                && referenceDetailsFrame.getVisibility() == View.VISIBLE;

        // If the screen is rotated the current user choice is not changed
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDuelPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        }
    }

    //
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);
    }

    // Displays reference item details on user selecting an item
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    void showDetails(int index) {
        mCurCheckPosition = index;

        if (mDuelPane) {

            getListView().setItemChecked(index, true);

            ReferenceDetailsFragment details = (ReferenceDetailsFragment)
                    getFragmentManager().findFragmentById(R.id.referencedetails);

            if (details == null || details.getShownIndex() != index) {
                details = ReferenceDetailsFragment.newInstance(index);

                FragmentTransaction ft =
                        getFragmentManager().beginTransaction();

                ft.replace(R.id.referencedetails, details);

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            }
        } else {

            Intent intent = new Intent();
            intent.setClass(getActivity(), ReferenceDetailsActivity.class);

            intent.putExtra("index", index);

            startActivity(intent);
        }

    }
}
