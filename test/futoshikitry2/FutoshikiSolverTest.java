/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikitry2;

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
public class FutoshikiSolverTest {
    


    /**
     * Test of solved method, of class FutoshikiSolver.
     */
    @Test
    public void testSolved() {
        System.out.println("solved");
        FutoshikiSolver instance = null;
        int[][] expResult = null;
        int[][] result = instance.solved();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numbersAvailable method, of class FutoshikiSolver.
     */
    @Test
    public void testNumbersAvailable() {
        System.out.println("numbersAvailable");
        int x = 0;
        int y = 0;
        FutoshikiSolver instance = null;
        int[] expResult = null;
        int[] result = instance.numbersAvailable(x, y);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
