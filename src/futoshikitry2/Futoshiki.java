package futoshikitry2;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * This class is the "model answer" to the first part of the Futoshiki
 * assignment. There will be other equally good (or even better) solutions, as
 * well as lots of worse ones!
 *
 * @author Ian Wakeman
 * @version 1.0
 */
public class Futoshiki {

    /**
     * **************************************************************
     * constant to specify DEFAULT size of puzzle
     * **************************************************************
     */
    public static final int DEFAULT_GRIDSIZE = 5;
    public GridPane gridFutoshiki = new GridPane();
    public int gridsize;
    private Square[][] squares;
    private FutoshikiConstraints[][] rowConstraints;
    private FutoshikiConstraints[][] columnConstraints;
    private int[][] latinSquares;

    /**
     * **************************************************************
     * Creates a completely blank puzzle of default size
     *
     * @param gridsize
     * **************************************************************
     */
    public Futoshiki(int gridsize) {

        this.gridsize = gridsize;
        squares = new Square[gridsize][gridsize];
        latinSquares = new int[gridsize][gridsize];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j] = new Square(i, j);
                latinSquares[i][j] = 0;
            }
        }
        rowConstraints = new FutoshikiConstraints[gridsize][gridsize - 1];
        columnConstraints = new FutoshikiConstraints[gridsize][gridsize - 1];

        //set up initial row constraints (no constraints)
        for (int row = 0; row < gridsize; row++) {
            for (int column = 0; column < gridsize - 1; column++) {
                rowConstraints[row][column] = null;
              
            }
        }

        //set up initial column constraints (no constraints)
        for (int column = 0; column < gridsize; column++) {
            for (int row = 0; row < gridsize - 1; row++) {
                columnConstraints[column][row] = null;
            }
        }
        latinSquareArray();
        latinSquareShuffle();

  

    }
    public boolean checkSquaresFull(){
        for(int row= 0; row<gridsize;row++){
            int i = 1;
        }
        return false;
    }

    public Boolean checkDuplicateSquare(int row, int col) {
        int counterR = 0;
        int counterC = 0;
        int value = squares[row][col].getValue();
        for(int i = 0; i<gridsize; i ++){
            //checks if theres a duplicate in row
            if(value == squares[row][i].getValue()){
                counterR += 1;
            }
            
            if(value ==squares[i][col].getValue()){
            counterC +=1;
            }
        }
        return(counterC>1|| counterR>1);
       
    }

    public Futoshiki() {
        this(DEFAULT_GRIDSIZE);
    }

    private void latinSquareShuffle(){
        
         // Shuffle the rows
        for (int i = gridsize- 1; i >= 0; i--) {
            int j = (int) (Math.random() * i);
            
            int[] temp = latinSquares[i];
            latinSquares[i] = latinSquares[j];
            latinSquares[j] = temp;
        }
        
        // Shuffle the columns
        for (int i = gridsize - 1; i >= 0; i--) {
            int j = (int) (Math.random() * i);
            
            for (int k = 0; k < gridsize; k++) {
                int temp = latinSquares[k][i];
                latinSquares[k][i] = latinSquares[k][j];
                latinSquares[k][j] = temp;
            }
        }

    }
    private void latinSquareArray() {
        int n = gridsize;
        // A variable to control the  
        // rotation point. 

        // Loop to print rows 
        for (int i = 0; i < n; i++) { 
           
            for (int j = 0; j < n; j++) {
                int value = (i + j) % n + 1;
                
                latinSquares[i][j] = value;
            }

        }
    }

    // Convenience check method
    private boolean checkCoordinates(int row, int column) {
        return row >= 0 && row < gridsize && column >= 0 && column < gridsize;
    }

    /**
     * ***********************************************************
     * sets cell at specified row and column to the value specified
     *
     * @param row the row number
     * @param column the column number
     * @param val the value to be stored
     * ***********************************************************
     */
    public void setSquare(int row, int column, int val) {

        if (val >= 1 && val <= gridsize && checkCoordinates(row, column)) {
            squares[row][column].setValue(val);
        }
    }

    public int getSquare(int row, int column) {
        if (checkCoordinates(row, column)) {
            return squares[row][column].getValue();
        }
        return 0;
    }

    public int getSize() {
        return gridsize;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public FutoshikiConstraints[][] getRowConstraints() {
        return rowConstraints;
    }

    public FutoshikiConstraints[][] getColumnConstraints() {
        return columnConstraints;
    }

    /**
     * ***********************************************************
     * sets a constraint in a specified row between the cell at the specified
     * column and the next one
     *
     * @param row the row number
     * @param col the column number
     * @param relation the constraint (">" or "<")
     * ***********************************************************
     */
    public void setRowConstraint(int row, int col, String relation) {
        if (relation.equals("<")) {
            rowConstraints[row][col] = new ConstraintSmaller(col, row, true, this);
        } else if (relation.equals(">")) {
            rowConstraints[row][col] = new ConstraintGreater(col, row, true, this);
        }
    }

    /**
     * ***********************************************************
     * sets a constraint in a specified column between the cell at the specified
     * row and the next one
     *
     * @param col the column number
     * @param row the row number
     * @param relation the constraint ("<" or ">")
     * ***********************************************************
     */
    public void setColumnConstraint(int col, int row, String relation) {
        if (relation.equals("<")) {
            columnConstraints[col][row] = new ConstraintSmaller(col, row, false, this);
        } else if (relation.equals(">")) {
            columnConstraints[col][row] = new ConstraintGreater(col, row, false, this);
        }
    }

    /**
     * ******************************************************************
     * Fills a Futoshiki puzzle with the randomized values and constraints. This
     * is not guaranteed to be legal
     *
     * @param numValues How many values to enter
     * @param numHorizontal How many horizontal constraints
     * @param numVertical How many vertical constraints
     * ******************************************************************
     */
    public void fillPuzzle(int numValues, int numHorizontal, int numVertical) {
        Random rand = new Random();
        int countValues = 0;

        while (countValues < numValues) {
            int x = rand.nextInt(gridsize);
            int y = rand.nextInt(gridsize);
            if (squares[x][y].getValue() == 0) {
                squares[x][y].setValue(latinSquares[x][y]);
                squares[x][y].setEditable(false);
                countValues++;
            }
        }

        int countHorizontal = 0;
        while (countHorizontal < numHorizontal) {
            int x = rand.nextInt(gridsize-1);
            int y = rand.nextInt(gridsize - 1);
            boolean constraintType = true;
            if (rowConstraints[x][y] == null) {
                if (latinSquares[x][y] > latinSquares[x+1][y]) {
                    rowConstraints[x][y] = new ConstraintGreater(x, y, true, this);

                } else {
                    rowConstraints[x][y] = new ConstraintSmaller(x, y, true, this);

                }        
            }
            countHorizontal++;
        }

        int countVertical = 0;
        while (countVertical < numVertical) {
            int x = rand.nextInt(gridsize - 1);
            int y = rand.nextInt(gridsize - 1);
            int constraintType = rand.nextInt(2);

            if (columnConstraints[x][y] == null) {
                if (latinSquares[x][y] < latinSquares[x][y+1]) {
                    columnConstraints[x][y] = new ConstraintGreater(x, y, false, this);
                } 
                else {
                    columnConstraints[x][y] = new ConstraintSmaller(x, y, false, this);
                }
            }
            countVertical++;
        }
    }

    

    

    /**
     * Checks if numbers are duplicate;
     *
     * @return boolean if constraints are valid
     */
    public boolean isLegalDuplicate() {

        int numberRow;
        int numberColumn;
        boolean valid = true;
        int[] counterRow = new int[gridsize];
        int[] counterColumn = new int[gridsize];
        for (int i = 0; i < squares.length; i++) {

            for (int j = 0; j < squares.length; j++) {

                numberRow = squares[i][j].getValue();
                numberColumn = squares[j][i].getValue();

                if (numberRow != 0) {
                    counterRow[numberRow - 1] += 1;
                }

                if (numberColumn != 0) {
                    counterColumn[numberColumn - 1] += 1;
                }
            }
            //Checks the counterRow to see if there are duplicates
            for (int k = 0; k < counterRow.length; k++) {

                if (counterRow[k] > 1) {
                    valid = false;
                    return valid;
                } else {
                    counterRow[k] = 0;

                }
                //Checks if column has duplicate
                if (counterColumn[k] > 1) {
                    valid = true;
                    return valid;
                } else {
                    counterColumn[k] = 0;
                }
            }
        }

        return valid;
    }

    

    public int returnNumber(int x, int y) {

        return squares[y][x].getValue();

    }



}
