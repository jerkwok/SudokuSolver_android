package com.android.sudokusolver;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {


    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button randomPuzzleButton = (Button) findViewById(R.id.button1);
        final Button resetButton = (Button) findViewById(R.id.test_button);
        final Button solveButton = (Button) findViewById(R.id.solve_button);

        final GridView numberGridView =(GridView) findViewById(R.id.my_grid);
//        final TextView[] numbers = new TextView[81];
//        final Integer[] data = new Integer[81];
        final ArrayList<String> puzzles = new ArrayList<String>();
        final ArrayList<String> visibilities = new ArrayList<String>();

        //our data array. A value of 10 indicates no entry.
//        for (int i = 0; i < data.length;i++){
//            data[i]=10;
//        }

        //Set our adapter and populate our grid view
//        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,data);

        //populate our text view array, set the backgrounds of our text view.
//        for(int i = 0; i < numberGridView.getChildCount();i++){
//            if(numberGridView.getChildAt(i).getClass()==TextView.class){
//                numbers[i] = (TextView) numberGridView.getChildAt(i);
//                numbers[i].setText("-");
//                numbers[i].setBackgroundResource(R.drawable.selector_button);
//            }
//        }

        adapter = new GridAdapter(this);
        adapter.reset();
        numberGridView.setAdapter(adapter);


        //initial refresh the display
        //refreshDisplay(numberGridView, numbers, data);

        //Add random puzzles
        puzzles.add(0,"839741256614523798527986314172654839398172465465839172286315947753498621941267583");
        visibilities.add(0,"011110110101111011101011011011011011011010110110110110110110101110111101011011110");
        puzzles.add(1,"198675432367924815425813967786391254243567198519482673652138749974256381831749526");
        visibilities.add(1,"110011111011110111110110001011111100101101101001111110100011011111011110111110011");
        puzzles.add(2,"354726981861349725972185436743812569619573842528694173297431658185267394436958217");
        visibilities.add(2,"111111001101101011110010111001111111010101010111111100111010011110101101100111111");
        puzzles.add(3,"362847159985321674471659283146538792527496831893712546659183427734265918218974365");
        visibilities.add(3,"111001111110110101110111010101110110011101110011011101010111011101011011111100111");
        puzzles.add(4,"537862419264139857198745632942513786376428195815976324481257963659384271723691548");
        visibilities.add(4,"111100011011111101111011100010001111101111101111100010001110111101111110110001111");

//        dynamically set the buttons to fill the grid layout.
//        currently only does it wide. fix later to fill height.
//        int idealChildWidth = (int) ((numberGridView.getWidth())/numberGridView.getNumColumns());
//        for( int i=0; i< numberGridView.getChildCount();i++){
//            numbers[i] = (TextView) numberGridView.getChildAt(i);
//            numbers[i].setWidth(idealChildWidth);
//        }

        randomPuzzleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //get a random puzzle
                Random random = new Random();
                int puzzleNumber = random.nextInt(5);

                //display the puzzle on the grid
                Integer[] temp = fillDisplay(puzzles.get(puzzleNumber), visibilities.get(puzzleNumber));

                for (int i = 0; i < temp.length; i++) {
                    adapter.setValue(i, temp[i]);
                }

                Toast.makeText(getApplicationContext(),
                        "Puzzle #" + Integer.toString(puzzleNumber), Toast.LENGTH_SHORT).show();
            }
        });

        resetButton.setOnClickListener(new OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //reset data
                                                adapter.reset();
                                            }
                                        }
        );

        numberGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                FragmentManager fm = getFragmentManager();
                final NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
                numberPickerDialog.setValueListener(new NumberPickerDialog.OnValueSetListener() {
                    @Override
                    public void onValueSet(int value) {
                        adapter.setValue(position, value);
                        numberPickerDialog.dismiss();
                    }
                });

                numberPickerDialog.show(fm, "fragment_number_picker");
            }
        });

        solveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //solve things
                Solver solver = new Solver(adapter.getNumbers());
                Integer[] temp = solver.getResult();
                if (temp == null) {
                    Toast.makeText(getApplicationContext(),
                            "Unsolvable", Toast.LENGTH_SHORT).show();
                } else {

                    for (int i = 0; i < temp.length; i++) {
                        adapter.setValue(i, temp[i]);
                    }

                    Toast.makeText(getApplicationContext(),
                            "Solved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void refreshDisplay(GridView numberGridView) {
//        ((GridAdapter) numberGridView.getAdapter()).notifyDataSetChanged();
//    }

    //fills the display with a puzzle
    private Integer[] fillDisplay(String answers, String visible) {
       Integer[] data = new Integer[81];
        for (int i = 0; i < answers.length(); i++) {
            if (visible.charAt(i) == '0'){
                data[i]=Character.getNumericValue(answers.charAt(i));

            }else{
                data[i] = 10;
            }
        }
        return data;
    }
}
