package com.example.homebudgetusingroomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homebudgetusingroomdatabase.Room.expense;

import java.util.ArrayList;

public class ExpenseListAdapter extends ArrayAdapter<expense> {

    private Context mcontext;
    int mresouce;

    public ExpenseListAdapter( Context context, int resource, ArrayList<expense> objects) {
        super(context, resource, objects);
        mcontext  = context;
        mresouce = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String desc = getItem(position).getDescription();
        String val = getItem(position).getValue();

        expense exp  =  new expense(desc,val);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresouce,parent,false);

        TextView tv1 = (TextView) convertView.findViewById(R.id.desc_income);
        TextView tv2 = (TextView) convertView.findViewById(R.id.val_income);

        tv1.setText(desc);
        tv2.setText(val);
        return convertView;
    }

}
