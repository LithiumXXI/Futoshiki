/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;
import java.util.Arrays;
/**
 *
 * @author Pau
 */
public class FutoshikiSolver {
    Futoshiki puzzle;
    int[][] solvedSquares = new int[puzzle.getSize()][puzzle.getSize()];
    
    /**
     *Constructor assigns a puzzle
     * This is the puzzle that will be solved.
     * @param puzzle
     */
    public FutoshikiSolver(Futoshiki puzzle){
        this.puzzle = puzzle;
        
    }
    public int[][] solved(){
        
        return null;
}
    
    
    //Looks to see which numbers are available
    public int[]numbersAvailable(int x, int y){
      
       int possibilities [] = new int[puzzle.getSize()];
       //fills array possibilities
       for(int n = 0; n < puzzle.getSize(); n++){
          possibilities[n]= n+1;
       }
       //Goes through columns
            for(int j = 0; j < puzzle.getSize();j++){
                //Goes through numbers 
                for(int i = 1; i<6;i++){
                    if (puzzle.getSquare(x, j) == i){
                        //removes it from possibilities
                        for( int n= 0; n<puzzle.getSize();n++){
                           if(possibilities[n]==i){
                               possibilities[n]=0;
                        }
                    }     
                }
            }    
          
        }
              return possibilities;
    }
}
    