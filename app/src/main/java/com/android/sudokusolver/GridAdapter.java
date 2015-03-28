package com.android.sudokusolver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

public class GridAdapter extends BaseAdapter{

    //ArrayList<Integer> numbers = new ArrayList<>();
    ArrayList<Integer> visibilities = new ArrayList<>();
    private Integer[] numbers = new Integer[81];

    public GridAdapter(Context context) {
        super();
    }

    @Override
    public int getCount() {
        return numbers.length;
    }

    @Override
    public Integer getItem(int position) {
        return numbers[position];
    }

    @Override
    public long getItemId(int position) {
        return numbers[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.grid_item_number, parent, false);
        }
        String text;
        Integer item = getItem(position);
        if (item == 10) {
            text = "-";
        } else {
            text = Integer.toString(item);
        }
        ((TextView) convertView.findViewById(R.id.number)).setText(text);

        return convertView;
    }

    public void reset() {
        for (int i = 0; i < 81; i++) {
            numbers[i]=10;
        }
        notifyDataSetChanged();
    }

    public void setValue(int position, int value) {
        numbers[position]=  value;
        notifyDataSetChanged();
    }

    public Integer[] getNumbers() {
       return numbers;
    }
}
