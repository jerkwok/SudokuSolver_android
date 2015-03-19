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
import android.widget.Toast;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button1);
        final Button test_button = (Button) findViewById(R.id.test_button);
        final Button solve_button = (Button) findViewById(R.id.solve_button);

        final GridView numberGridView =(GridView) findViewById(R.id.my_grid);
        final TextView[] numbers = new TextView[81];
        final Integer[] data = new Integer[81];

        //our data array. A value of 10 indicates no entry.
        for (int i = 0; i < data.length;i++){
            data[i]=10;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,data);

        numberGridView.setAdapter(adapter);

        for(int i = 0; i < numberGridView.getChildCount();i++){
            if(numberGridView.getChildAt(i).getClass()==TextView.class){
                numbers[i] = (TextView) numberGridView.getChildAt(i);
                numbers[i].setBackgroundResource(R.drawable.selector_button);
            }
        }

        refreshDisplay(numberGridView, numbers, data);

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
//         <----------Old Testing Code used to test picker fragment--------->
//                FragmentManager fm = getFragmentManager();
//                final NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
//                numberPickerDialog.setValueListener(new NumberPickerDialog.OnValueSetListener() {
//                    @Override
//                    public void onValueSet(int value) {
//                        for(int i = 0; i < numberGridView.getChildCount();i++){
//                            if(numberGridView.getChildAt(i).getClass()==TextView.class){
//                                if (data[i] != 10) {
//                                    numbers[i].setText(Integer.toString(data[i]));
//                                }else{
//                                    numbers[i].setText("-");
//                                }
//                            }
//                        }
//
//                        numberPickerDialog.dismiss();
//                    }
//                });
//
//                numberPickerDialog.show(fm, "fragment_number_picker");
//      <-----------End of old testing code-------->
                //Put in a test
                for (int i = 0; i < data.length;i++){
                    data[i]=10;
                }

                data[0] = 8;
                data[5] = 1;
                data[8] = 6;
                data[10] = 1;
                data[15] = 7;
                data[19] = 2;
                data[21] = 9;
                data[24] = 3;
                data[27] = 1;
                data[30] = 6;
                data[33] = 8;
                data[36] = 3;
                data[39] = 1;
                data[41] = 2;
                data[44] = 5;
                data[47] = 5;
                data[50] = 9;
                data[53] = 2;
                data[56] = 6;
                data[59] = 5;
                data[61] = 4;
                data[65] = 3;
                data[70] = 2;
                data[72] = 9;
                data[75] = 2;
                data[80] = 3;

                refreshDisplay(numberGridView, numbers, data);
            }
        });

        test_button.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          //reset data
                                          for (int i = 0; i < data.length;i++){
                                              data[i]=10;
                                          }

                                          refreshDisplay(numberGridView, numbers, data);
                                      }
                                  }
        );

        numberGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//              numbers[position].setText(Integer.toString(position));
                FragmentManager fm = getFragmentManager();
                final NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.setValueListener(new NumberPickerDialog.OnValueSetListener() {
                    @Override
                    public void onValueSet(int value) {
                        if (value < 10) {
                            numbers[position].setText(Integer.toString(value));
                        }else{
                            numbers[position].setText("-");
                        }
                            data[position] = value;
                            numberPickerDialog.dismiss();
                    }
                });

                numberPickerDialog.show(fm, "fragment_number_picker");
            }
        });

        solve_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //solve things
                Solver solver = new Solver(data);
                Integer[] temp = solver.getResult();
                if (temp == null){
                    Toast.makeText(getApplicationContext(),
                        "Unsolvable", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0; i < temp.length; i++) {
                        data[i] = temp[i];
                    }

                    refreshDisplay(numberGridView, numbers, data);

                    Toast.makeText(getApplicationContext(),
                            "Solved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void refreshDisplay(GridView numberGridView, TextView[] numbers, Integer[] data) {
        for(int i = 0; i < numberGridView.getChildCount();i++){
            if(numberGridView.getChildAt(i).getClass()==TextView.class){
                numbers[i] = (TextView) numberGridView.getChildAt(i);
                if (data[i] < 10) {
                    numbers[i].setText(Integer.toString(data[i]));
                }else{
                    numbers[i].setText("-");
                }
            }
        }
    }
}
