package com.android.sudokusolver;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button1);

        GridLayout numberGridLayout =(GridLayout) findViewById(R.id.my_grid);
        final TextView text = (TextView) findViewById(R.id.text_field);

        TextView[] numbers = new TextView[81];

        for(int i = 0; i < numberGridLayout.getChildCount();i++){
            if(numberGridLayout.getChildAt(i).getClass()==TextView.class){
                numbers[i] = (TextView) numberGridLayout.getChildAt(i);
                numbers[i].setBackgroundResource(R.drawable.selector_button);
                numbers[i].setText(Integer.toString(i));
            }
        }

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                final NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.setValueListener(new NumberPickerDialog.OnValueSetListener() {
                    @Override
                    public void onValueSet(int value) {
                        text.setText(Integer.toString(value));
                        numberPickerDialog.dismiss();
                    }
                });
                numberPickerDialog.show(fm, "fragment_number_picker");
            }
        });
    }
}
