/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

/**
 *
 * @author 181433
 */
public class Square {
    private int row, col;
    private int value = 0;
   private StringProperty  number = new SimpleStringProperty();
    private StackPane s; 
    private Boolean editable;       
    private Boolean correct;
    private ArrayList<Integer> triedNums;

    /**
     * Constructor
     * @param row position x;
     * @param col position y;
     */
    public Square(int row, int col) {
        this.triedNums = new ArrayList<>();
        this.s = new StackPane();
        s.setPrefSize(50,50);
        this.row = row;
        this.col = col;
        this.editable = true;
        this.correct = true;
    }
    public Square(int row, int col,int value) {
        this.triedNums = new ArrayList<>();
        this.s = new StackPane();
        s.setPrefSize(50,50);
        this.value = value;
        this.row = row;
        this.col = col;
        this.editable = true;
    }
    public void setNumber(){
        if (value!=0){
            number.set(""+value);
        }
        else{
            number.set("");
        }
       
    }
    public void addTriedNum(Integer triedNum){
        triedNums.add(triedNum);
               
    }
    public ArrayList<Integer> getTriedNum(){
        return triedNums;
    }
        
    public StringProperty getNumber() {
     
        return number;
    }
    public void  setEditable(boolean editable){
        this.editable = editable;
    }

    public boolean getEditable(){
        
        return editable;
    }

    /**
     * Checks if there is a number in the square
     * @return boolean if square is empty or not
     */
    public boolean isEmpty(){
        if (getValue() == 0){
                return true;
        }
        else{
            return false;
        }        
    }
    
    /**
     *
       @return value;
     * @return Stackpane that represents a square.
     */
    
    public void setCorrect(Boolean correct){
    
        this.correct =  correct;
    }
    public int getValue() {
        return value;
    }
    
    public String getValueString(){
      
         
            return  ""+getValue();
        
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    } 
    
    
    public StackPane getSquareFX(){
       return s;
    }

    
}
