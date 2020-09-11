package com.example.vocaking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table MyVoca(id integer primary key autoincrement,voca text, mean text, dic text, date text, head text);");
        db.execSQL("create table settings(id integer primary key autoincrement, name text, checked integer);");
        db.execSQL("insert into settings values(null, 'switchRandom', 0);");
        db.execSQL("insert into settings values(null, 'switchDistinct', 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean insert(String voca, String mean, String dic, String head) {
        boolean result;
        SQLiteDatabase db = getWritableDatabase();
        Date today = new Date(System.currentTimeMillis());
        Cursor cursor = db.rawQuery("select count(*) from MyVoca where voca = '"+voca+"' and head = '"+head+"';", null);
        cursor.moveToNext();
        if (cursor.getInt(0) == 0) {
            ContentValues c = new ContentValues();
            c.put("voca", voca);
            c.put("mean", mean);
            c.put("dic", dic);
            c.put("date", today.toString());
            c.put("head", head);
            db.insert("MyVoca", null, c);
            result = true;
        }
        else result = false;

        db.close();
        cursor.close();
        return result;
    }

    public String select() {
        String result = "";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from MyVoca", null);

        while(cursor.moveToNext()) {


            result += (cursor.getString(1) + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4));
            result += "\n";

        }

        cursor.close();
        db.close();
        return result;
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("MyVoca", null, null);
        db.close();
    }

    public ArrayList<String[]> selectVoMe(boolean phase, String input) {
        ArrayList<String[]> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        String[] s;

        if (phase)
            cursor = db.rawQuery("select voca, mean, dic from MyVoca where date = '"+ input +"';", null);
        else
            cursor = db.rawQuery("select voca, mean, dic from MyVoca where head = '"+ input +"';", null);

        while(cursor.moveToNext()) {
            s = new String[3];
            s[0] = cursor.getString(0);
            s[1] = cursor.getString(1);
            s[2] = cursor.getString(2);

            result.add(s);
        }

        cursor.close();
        db.close();
        return result;
    }



    public ArrayList<String> getGroup(boolean phase) {
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;

        if (phase)
            cursor = db.rawQuery("select date from MyVoca group by date;", null);
        else
            cursor = db.rawQuery("select head from MyVoca group by head;", null);

        while(cursor.moveToNext()) {
            result.add(cursor.getString(0));
        }

        db.close();
        cursor.close();
        return result;
    }

    public void saveSettings(boolean[] checked) {
        SQLiteDatabase db = getWritableDatabase();

        if (checked[0]) {
            db.execSQL("update settings set checked = 1 where name='switchRandom';");
        }
        else {
            db.execSQL("update settings set checked = 0 where name='switchRandom';");
        }

        if (checked[1]) {
            db.execSQL("update settings set checked = 1 where name='switchDistinct';");
        }
        else {
            db.execSQL("update settings set checked = 0 where name='switchDistinct';");
        }

        db.close();

    }

    public boolean[] getCheckd() {
        boolean[] result = new boolean[2];
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select checked from settings;", null);
        int idx = 0;

        while(c.moveToNext()) {
            if (c.getInt(0) == 0) result[idx] = false;
            else result[idx] = true;
            idx ++;
        }

        db.close();
        c.close();
        return result;
    }

}

