/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import futoshikitry2.Futoshiki;
import futoshikitry2.Solve;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pau
 */
public class SolveTest {
    
    @Test
    public void testSolveMethodValid(){
        Futoshiki p = new Futoshiki();
        p.setSquare(0, 0, 1);
        p.setSquare(0,1,2);
        p.setSquare(1,1,3);
        p.setSquare(4,4,4);
        
        Solve s = new Solve(p);
        s.solveFunc();
        s.updatePuzzle();
        assertEquals(true,s.checkSquaresFilled());
        
    }
  
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
