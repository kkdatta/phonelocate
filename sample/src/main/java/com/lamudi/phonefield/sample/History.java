package com.lamudi.phonefield.sample;

/**
 * Created by kalyan on 2/5/18.
 */

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private ListView lv;
    private DBUtil dbUtil;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private String calcName="";
    private String []EmptyList={"There is  no number yet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv=(ListView)findViewById(R.id.listView);
        dbUtil =new DBUtil(this);
        calcName=getIntent().getStringExtra("calcName");
        list= dbUtil.showHistory(calcName);
        if(!list.isEmpty())
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        else
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,EmptyList);
        lv.setAdapter(adapter);
    }

    public void onClick(View v)
    {
        dbUtil.deleteRecords(calcName);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,EmptyList);
        lv.setAdapter(adapter);
    }

}

