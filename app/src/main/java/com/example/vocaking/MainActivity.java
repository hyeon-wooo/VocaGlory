package com.example.vocaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    Intent intent;
    Boolean checkedRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkedRandom = false;




    }
    public void goNext(View v) {
        Intent intent;
        if (v.getId() == R.id.layout_menu_study) {
            intent = new Intent(this, StudyHomeActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.layout_menu_myvoca) {
            intent = new Intent(this, MyVocaPage.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.layout_menu_settings) {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    public void tmp(View v) {
        Log.d(null, String.valueOf(v.getId()));
    }
}
