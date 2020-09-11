package com.example.vocaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.vocaking.Adapter.ExpandableListAdapter;

import java.util.ArrayList;

public class MyVocaPage extends AppCompatActivity {
    ExpandableListView exList;
    ExpandableListAdapter adapter;
    ArrayList<String> parentData;
    ArrayList<ArrayList<String[]>> childData;
    DBhelper db;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voca_page);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        TextView t = (TextView)findViewById(R.id.texttemp);
        exList = (ExpandableListView) findViewById(R.id.exList);
        parentData = new ArrayList<>();
        childData = new ArrayList<>();

        setData(true);
        adapter = new ExpandableListAdapter(parentData, childData);
        exList.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioDate)
                    setData(true);
                else
                    setData(false);

                adapter = new ExpandableListAdapter(parentData, childData);
                exList.setAdapter(adapter);

            }
        });

    }

    // 날짜별 정렬인지, 어원별 정렬인지에 따라 (phase) parentData와 childData를 세팅함.
    public void setData(boolean phase) {
        db = new DBhelper(getApplicationContext(), "MyVoca.db", null, 1);

            parentData.clear();
            childData.clear();


        if (phase) {
            parentData.clear();
            parentData = db.getGroup(true);

            for (String s : parentData) {
                childData.add(db.selectVoMe(true, s));
            }
        }
        else {
            parentData.clear();
            parentData = db.getGroup(false);

            for (String s : parentData) {
                childData.add(db.selectVoMe(false, s));
            }
        }

    }

    public void goMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
