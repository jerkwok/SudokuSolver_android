package com.android.sudokusolver;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by 100341977 on 24/01/2015.
        */
public class NumberPickerDialog extends DialogFragment  {

    private NumberPicker mNumberDialog;
    private OnValueSetListener listener;

    public NumberPickerDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.numberpicker,container,false);
        String[] display = {"0","1","2","3","4","5","6","7","8","9","-"};

        mNumberDialog = (NumberPicker) view.findViewById(R.id.number_picker);
        mNumberDialog.setMinValue(0);
        mNumberDialog.setMaxValue(10);
        mNumberDialog.setDisplayedValues(display);
        mNumberDialog.setWrapSelectorWheel(true);
        getDialog().setTitle("Picker");

        TextView confirm_button = (TextView) view.findViewById(R.id.confirm_button);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onValueSet(mNumberDialog.getValue());
            }
        });
        return view;
    }

    public void setValueListener(OnValueSetListener listener) {
        this.listener = listener;
    }

    public interface OnValueSetListener {
        void onValueSet(int value);
    }
}
