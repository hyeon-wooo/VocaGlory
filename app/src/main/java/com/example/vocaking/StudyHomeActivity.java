package com.example.vocaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class StudyHomeActivity extends AppCompatActivity {


    Intent intent;
    Boolean checkedRandom;
//    LinearLayout layout_cap;
//    LinearLayout layout_best_head;
//    LinearLayout layout_head;
//    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_home);

//        layout_best_head = findViewById(R.id.layout_best_head);
//        layout_head = findViewById(R.id.layout_head);
//        layout_cap = findViewById(R.id.layout_cap);


    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        int height = layout_cap.getHeight();
//        int width = layout_cap.getWidth();
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(layout_cap.getWidth(), layout_cap.getHeight());
//        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) layout_cap.getLayoutParams();
//        layout_best_head.setLayoutParams(params2);
//
//
////        layout_head.setLayoutParams(params);
//
//    }

    public void goNext(View v) {
        intent = new Intent(StudyHomeActivity.this, CapActivity.class);

        intent.putExtra("head", (String)v.getTag());
        intent.putExtra("switchRandom", checkedRandom);

        startActivity(intent);
    }


    public void goMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
