package com.example.vocaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class CapActivity extends AppCompatActivity {

    ArrayList<VocaVO> studyList;

    public TextView textVoca;
    public TextView textMean;
    public TextView textPronounce;

    public int indx;
    public int length;
    DBhelper db;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap);

        ArrayList<VocaVO> loadedList;



        textVoca = (TextView)findViewById(R.id.textVoca);
        textMean = (TextView)findViewById(R.id.textMean);
        textPronounce = (TextView)findViewById(R.id.textPronounce);
        Button btnNext = (Button)findViewById(R.id.buttonNext);
        Button btnAdd = (Button)findViewById(R.id.buttonAdd);

        db = new DBhelper(getApplicationContext(), "MyVoca.db", null, 1);
        indx = 0;

        Intent intent = getIntent();
        key = intent.getExtras().getString("head");

        if (db.getCheckd()[1]) {
            loadedList = getJson(key);
            ArrayList<String[]> existingVoca = db.selectVoMe(false, key);

            if(existingVoca.size() != 0) {
                for (String[] s : existingVoca) {
                    for (VocaVO d : loadedList) {
                        if (s[0].equals(d.getVoca())) {
                            loadedList.remove(d);
                            loadedList.trimToSize();
                            break;
                        }
                    }
                }
                studyList = loadedList;
            }
        }
        else {
            studyList = getJson(key);
        }


        length = studyList.size()-1;

        textVoca.setText(studyList.get(0).getVoca());
        textPronounce.setText(studyList.get(0).getPronounce());
        textMean.setText("");



        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
                    public void onClick(View v) {
                Intent intent = new Intent(CapActivity.this, MyVocaPage.class);

                startActivity(intent);
            }

        });

    }

    public void touchLayout(View v) {
        if(textMean.getText().equals("")) textMean.setText(studyList.get(indx).getMean());
        else {
            if(indx < length) {
                indx++;
                textMean.setText("");
                textVoca.setText(studyList.get(indx).getVoca());
                textPronounce.setText(studyList.get(indx).getPronounce());
            }
            else {
                Toast.makeText(this, "해당 어원의 학습이 끝났습니다!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, StudyHomeActivity.class);
                startActivity(intent);
            }

        }

    }

    public void deleteAll(View v) {
       db.deleteAll();
    }

    public void addVoca(View v) {
        VocaVO voca = studyList.get(indx);
        if (db.insert(textVoca.getText().toString(), voca.getMean(), textPronounce.getText().toString(), key))
            Toast.makeText(this, "단어장에 추가 완료", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "이미 단어장에 추가된 단어입니다!", Toast.LENGTH_LONG).show();
    }


    public ArrayList<VocaVO> getJson(String albumName) {
        ArrayList<VocaVO> result = new ArrayList<VocaVO>();
        Gson gson = new Gson();


        try {

            InputStream i = getAssets().open(albumName+"List.json");
            byte[] buffer = new byte[i.available()];
            i.read(buffer);
            i.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonobj = new JSONObject(json);
            JSONArray jsonarr = jsonobj.getJSONArray(albumName);

            int index = 0;
            while (index < jsonarr.length()) {
                VocaVO vocavo = gson.fromJson(jsonarr.get(index).toString(), VocaVO.class);
                result.add(vocavo);
                index++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void goMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
