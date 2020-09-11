package com.example.vocaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    Switch switch1;
    Switch switch2;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        db = new DBhelper(getApplicationContext(), "MyVoca.db", null, 1);

        switch1.setChecked(db.getCheckd()[0]);
        switch2.setChecked(db.getCheckd()[1]);


    }

    public void submit(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        boolean[] input = new boolean[2];
        input[0] = switch1.isChecked();
        input[1] = switch2.isChecked();
        db.saveSettings(input);


        startActivity(intent);
    }

}
