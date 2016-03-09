package com.example.keith.finalyearproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.keith.mylibrary.Stage;

import java.util.ArrayList;

/**
 * Created by Keith on 02/03/2016.
 */
public class LogicDesignerActivity extends AppCompatActivity {
    ArrayList<String> stringArray;
    ListView listView;
    private Stage stage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logic_designer);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        listView = (ListView)findViewById(R.id.logicDesignerList);
        stringArray = new ArrayList<String>();
        stringArray.add("test1");
        stringArray.add("test2");
        ArrayAdapter<String> stringArrayToList = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_activated_1,
                stringArray);
        listView.setAdapter(stringArrayToList);
        stage = (Stage)findViewById(R.id.my_stage);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stage.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stage.onResume();
    }
    /*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        *//*setContentView(R.layout.activity_logic_designer);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        listView = (ListView)findViewById(R.id.logicDesignerList);
        stringArray = new ArrayList<String>();
        stringArray.add("test1");
        stringArray.add("test2");
        ArrayAdapter<String> stringArrayToList = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_activated_1,
                stringArray);
        listView.setAdapter(stringArrayToList);*//*
    }*/
}
