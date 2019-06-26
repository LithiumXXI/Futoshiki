/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Pau
 */
public class Solve {

    private Square[][] squares;
    private FutoshikiConstraints[][] rowConstraints;
    private FutoshikiConstraints[][] columnConstraints;
    private Futoshiki p;

    public Solve(Futoshiki p) {
        this.p = p;
        squares = new Square[p.getSize()][p.getSize()];
        for (int row = 0; row < p.getSize(); row++) {
            for (int col = 0; col < p.getSize(); col++) {
                squares[row][col] = new Square(row, col);
                squares[row][col].setValue(p.getSquares()[row][col].getValue());
                squares[row][col].setEditable(p.getSquares()[row][col].getEditable());
            }
        }
        rowConstraints = p.getRowConstraints();
        columnConstraints = p.getColumnConstraints();
    }

    
    public Boolean checkSquaresFilled() {
        System.out.println("j");
        for (int i = 0; i < p.getSize(); i++) {
            for (int j = 0; j < p.getSize(); j++) {
                if (squares[i][j].getValue() == 0) {
                    return false;
                }

            }
        }
        return true;
    }

    public void solveFunc() {
        Integer value = 0;
        Queue<Integer> possibleNumbers;
        Stack<Square> squaresVisited;
        squaresVisited = new Stack();
        int row = 0;
        int col = 0;
        boolean cont = true;

        while (cont == true) {
            if (col == 5 ||row ==5) {
                break;
            }
            System.out.print("col"+col +"row"+row);
            //If square is editable it changes it
            if (squares[row][col].getEditable() == true) {
                System.out.println("trying" + row + col);
                possibleNumbers = possibleNumbers(row, col);
                squaresVisited.add(squares[row][col]);
                //if it has possible numbers
                if (possibleNumbers.isEmpty() == false) {

                    value = possibleNumbers.remove();

                  
                    squares[row][col].setValue(value);
                    squares[row][col].addTriedNum(value);
                    System.out.println("number" + value);

                    p.getSquares()[row][col].setValue(value);
                    p.getSquares()[row][col].setNumber();

                    //next square
                    if (col < p.getSize() - 1) {
                        col = col + 1;
                    } else {
                        row = row + 1;
                        col = 0;
                    }
                    //no possible numbers then go back
                } else {
                    if (col < p.getSize() - 1) {
                        col = col + 1;
                    } else {
                        row = row + 1;
                        col = 0;
                    }
                }

                //lo ultimo vale para col pero no para row 
            }
            else{
            if (col < p.getSize() - 1) {
                        col = col + 1;
                    } else {
                        row = row + 1;
                        col = 0;
                    }
        }
           

        }

        updatePuzzle();
        for (int i = 0; i < p.getSize(); i++) {
            for (int j = 0; j < p.getSize(); j++) {
                System.out.print(squares[i][j].getValue());
            }
            System.out.println("");
        }

    }

    public void updatePuzzle() {
        for (int row = 0; row < p.getSize(); row++) {
            for (int col = 0; col < p.getSize(); col++) {
                p.getSquares()[row][col].setValue(squares[row][col].getValue());
                p.getSquares()[row][col].setNumber();
            }
        }
    }

    public Queue<Integer> possibleNumbers(int x, int y) {

        Queue<Integer> possibleNumbers = new LinkedList<>();
        
        for (Integer value = 1; value < p.getSize() + 1; value++) {
            if(!squares[x][y].getTriedNum().contains(value)){
               possibleNumbers.add(value);
            }

        }

        for (int value = 1; value < p.getSize() + 1; value++) {

            for (int i = 0; i < p.getSize(); i++) {

                if (squares[x][i].getValue() == value || squares[i][y].getValue() == value) {
                    possibleNumbers.remove(value);
                   

                }

            }

        }
        return possibleNumbers;
    }

}
