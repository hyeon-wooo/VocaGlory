package com.example.vocaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ExamActivity extends AppCompatActivity {
    ArrayList<String[]> examData;
    DBhelper db;

    public TextView textVoca;
    public TextView textMean;
    public TextView textPronounce;
    public int indx;
    public int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        textVoca = (TextView)findViewById(R.id.textVoca);
        textMean = (TextView)findViewById(R.id.textMean);
        textPronounce = (TextView)findViewById(R.id.textPronounce);

        db = new DBhelper(getApplicationContext(), "MyVoca.db", null, 1);

        boolean is_random = db.getCheckd()[0];

        Intent intent = getIntent();
        examData = (ArrayList<String[]>) intent.getExtras().get("examData");


        if (is_random) examData = shupple(examData);


        indx = 0;
        length = examData.size()-1;



        textVoca.setText(examData.get(0)[0]);
        textPronounce.setText(examData.get(0)[2]);
        textMean.setText("");
    }

    public void touchLayout(View v) {
        if(textMean.getText().equals("")) textMean.setText(examData.get(indx)[1]);
        else {
            if(indx < length) {
                indx++;
                textMean.setText("");
                textVoca.setText(examData.get(indx)[0]);
                textPronounce.setText(examData.get(indx)[2]);
            }

            //Exit exam
            else {
                Toast.makeText(this, "시험을 모두 마치셨습니다", Toast.LENGTH_LONG).show();
                Intent it = new Intent(this, MyVocaPage.class);
                startActivity(it);
            }

        }
    }

    public void goMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public ArrayList<String[]> shupple(ArrayList<String[]> res) {
        Random r = new Random();
        ArrayList<String[]> result = new ArrayList<>();

        int i = res.size();
        int currentIndx;

        for (; i > 0; i--) {
            currentIndx = r.nextInt(i);
            result.add(res.get(currentIndx));
            res.remove(currentIndx);
            res.trimToSize();
        }

        return result;
    }
}
