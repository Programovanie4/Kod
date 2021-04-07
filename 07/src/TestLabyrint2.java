//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLabyrint2 {
    /*
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
    */

    @Test
    public void testpriechodziNull() {
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, 3},
                            {6, null, 4},
                            {7, 8, 9}});
            assertTrue(p.toString() + " .priechodziNull() = " , p.priechodziNull());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 2, null},                                // Hint: {{ 1,   2,  8},
                            {null, 3, null},                                //        { 6,   3,  7},
                            {null, 4, 9}});                                //        { 5  , 4,  9}});
            assertTrue(p.toString() + " .priechodziNull() = " , p.priechodziNull());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, null, null},                             // Hint: {{ 1,   2,  3},
                            {null, null, null},                             //        { 5,   6,  4},
                            {null, null, 9}});                              //        { 8  , 7,  9}});
            assertTrue(p.toString() + " .priechodziNull() = " , p.priechodziNull());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, 6, 2},                                    // Hint: {{ 1,   6,  2},
                            {null, null, 3},                                    //        { 4,   5,  3},
                            {null, 7, 9}});                                  //        { 8  , 7,  9}});
            assertTrue(p.toString() + " .priechodziNull() = " , p.priechodziNull());  // true
        }
        {
            Labyrint p = new Labyrint(new Integer[][]
                    {{1, null, 5},
                            {null, null, 6},
                            {2, 3, 9}});
            assertFalse(p.toString() + " .priechodziNull() = " , p.priechodziNull());  // true
        }
        //scoring.updateScore("lang:common_list_test_scoring_name", 100);
    }
}