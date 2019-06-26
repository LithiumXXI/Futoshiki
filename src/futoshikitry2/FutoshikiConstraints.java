/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;


import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 *
 * @author pd266
 */
public abstract class FutoshikiConstraints { 
    int x,y;
    boolean row;
    Futoshiki puzzle;
    StackPane s;

    
    public FutoshikiConstraints(int x, int y, boolean row, Futoshiki puzzle){
        this.x = x;
        this.y = y;
        this.row = row;
        this.puzzle = puzzle;
        
    }
 
    public abstract Label setStackPane();



    public abstract String getContent();

    public abstract boolean compare(Square[][] squares);
   

    
   
}
