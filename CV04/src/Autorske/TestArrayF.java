package Autorske;

import static org.junit.Assert.*;

import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestArrayF {

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
    private static LISTTestScoring scoring = null;

    @Test
    public void test() {
        ArrayF f = new ArrayF(100);
        StringBuffer s = new StringBuffer("a");
        assertTrue("isEmpty1", f.isEmpty());
        for (int i = 0; i < 20; i++) {
            f.enqueue(s.toString());
            assertFalse("isEmpty2", f.isEmpty());
            s.append(s);
        }
        s = new StringBuffer("a");
        while (!f.isEmpty()) {
            String w = f.dequeue();
            assertEquals("dequeue", s.toString(), w);
            s.append(s);
        }
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
}