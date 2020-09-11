package com.example.vocaking.Adapter;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vocaking.ExamActivity;
import com.example.vocaking.MyVocaPage;
import com.example.vocaking.R;

import java.util.ArrayList;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> parentList;
    public ArrayList<ArrayList<String[]>> childList;

    public ExpandableListAdapter(ArrayList<String> parentData, ArrayList<ArrayList<String[]>> childData) {
        parentList = parentData;
        childList = childData;
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public String getGroup(int i) {
        return parentList.get(i);
    }

    @Override
    public String[] getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final int idx = i;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_list, parent,false);
        }

        TextView t = (TextView) convertView.findViewById(R.id.textDate);
        ImageView exam = (ImageView) convertView.findViewById(R.id.viewExam);
        t.setText(getGroup(i));


        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String[]> intentData = childList.get(idx);

                Intent intent = new Intent(context, ExamActivity.class);
                intent.putExtra("examData", intentData);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_list, parent, false);
        }

        TextView textVoca = (TextView) convertView.findViewById(R.id.textVoca);
        TextView textMean = (TextView) convertView.findViewById(R.id.textMean);

        String[] s = getChild(groupPosition, childPosition);

        switch(s[1]) {
            case "best_head":
                s[1] = "중요 접두사";
                break;
            case "head":
                s[1] = "접두사";
                break;
        }

        textVoca.setText(s[0]);
        textMean.setText(s[1]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
