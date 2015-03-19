package com.android.sudokusolver;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button1);
        final Button test_button = (Button) findViewById(R.id.test_button);

        final GridView numberGridView =(GridView) findViewById(R.id.my_grid);
        final TextView[] numbers = new TextView[81];
        final Integer[] data = new Integer[81];

        for (int i = 0; i < data.length;i++){
            data[i]=i;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,data);

        numberGridView.setAdapter(adapter);

        for(int i = 0; i < numberGridView.getChildCount();i++){
            if(numberGridView.getChildAt(i).getClass()==TextView.class){
                numbers[i] = (TextView) numberGridView.getChildAt(i);
                numbers[i].setBackgroundResource(R.drawable.selector_button);
                numbers[i].setText(Integer.toString(i));
            }
        }

//        dynamically set the buttons to fill the grid layout.
//        currently only does it wide. fix later to fill height.
//        int idealChildWidth = (int) ((numberGridView.getWidth())/numberGridView.getNumColumns());
//        for( int i=0; i< numberGridView.getChildCount();i++){
//            numbers[i] = (TextView) numberGridView.getChildAt(i);
//            numbers[i].setWidth(idealChildWidth);
//        }

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                final NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.setValueListener(new NumberPickerDialog.OnValueSetListener() {
                    @Override
                    public void onValueSet(int value) {
                        if (value < 9) {
                            numbers[value].setText(Integer.toString(value));
                            numbers[value*value].setText(Integer.toString(value*value));
                            numbers[5].setText(Integer.toString(Integer.parseInt(numbers[0].getText().toString()) + 5));
                            numberPickerDialog.dismiss();
                        }
                    }
                });

                numberPickerDialog.show(fm, "fragment_number_picker");
            }
        });

        test_button.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          for(int i = 0; i < numberGridView.getChildCount();i++){
                                              if(numberGridView.getChildAt(i).getClass()==TextView.class){
                                                  numbers[i] = (TextView) numberGridView.getChildAt(i);
                                                  numbers[i].setText("-");
                                              }
                                          }
                                      }
                                  }
        );

        numberGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
