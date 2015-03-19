package com.android.sudokusolver;

import android.widget.Toast;

/**
 * Created by 100341977 on 19/03/2015.
 */
public class Solver {

    static int[][] Grid = new int[9][9];

    public Solver(Integer[] incoming){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j <9; j++){
                Grid[i][j]=incoming[i*9+j];
            }
        }
        solve();
    }

    public Integer[] getResult(){
        if (this.solve()) {
            Integer[] data = new Integer[81];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    data[i * 9 + j] = Grid[i][j];
                }
            }
            return data;
        }else{
            return null;
        }
    }

    private static Boolean solve(){
        return recurSolve(0,0);
    }

    private static Boolean recurSolve(int row, int col) {
        int i;
        //skip to empty spaces
        while (row < 9 && Grid[row][col] != 10){
            col++;
            if(col == 9){
                row++;
                col=0;
            }
        }

        //base case
        if (row == 9){
            return true;
        }

        for (i = 1; i <= 9; i++){
            Grid[row][col] = i;
            if (rowValid(row) && colValid(col) &&
                    boxValid(row-(row%3), col-(col%3)) &&
                    recurSolve(row,col)){
                return true;
            }
        }

        Grid[row][col] = 10;
        return false;
    }

    private static boolean rowValid(int row) {
        //temporary vector tracks numbers encountered in row
        int temp[] = new int[10];

        for (int i = 0; i < 9; i++){
            temp[i] = 0;
        }

        //if digit has been seen already then return false
        //else flag digit as seen
        for (int col = 0; col < 9; col++){
            if (Grid[row][col] != 10){
                int t = Grid[row][col];
                if (temp[t] == 1){
                    return false;
                }else{
                    temp[t] = 1;
                }
            }
        }

        //if we've gotten this far then return true
        return true;
    }

    private static boolean boxValid(int startrow, int startcol) {
        //temporary vector tracks numbers encountered in box
        int temp[] = new int[10];

        for (int i = 0; i < 9; i++){
            temp[i] = 0;
        }

        //if digit has been seen already then return false
        //else flag digit as seen
        for (int row = startrow; row < startrow + 3; row++){
            for (int col = startcol; col <startcol + 3; col++){
                if (Grid[row][col] != 10){
                    int t = Grid[row][col];
                    if (temp[t] == 1){
                        return false;
                    }else{
                        temp[t] = 1;
                    }
                }
            }
        }

        //if we've gotten this far then return true
        return true;
    }

    private static boolean colValid(int col) {
        //temporary vector tracks numbers encountered in column
        int temp[] = new int[10];

        for (int i = 0; i < 9; i++){
            temp[i] = 0;
        }

        //if digit has been seen already then return false
        //else flag digit as seen
        for (int row = 0; row < 9; row++){
            if (Grid[row][col] != 10){
                int t = Grid[row][col];
                if (temp[t] == 1){
                    return false;
                }else{
                    temp[t] = 1;
                }
            }
        }

        //if we've gotten this far then return true
        return true;
    }
}
