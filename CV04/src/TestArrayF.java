import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayF {

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
    private static LISTTestScoring scoring = null;

    @Test
    public void test1() {
        ArrayF f = new ArrayF(100);
        StringBuffer s = new StringBuffer("a");
        assertTrue("isEmpty1", f.isEmpty());
        for (int i = 0; i < 20; i++) {
            f.enqueue(s.toString());
            assertFalse("isEmpty2", f.isEmpty());
            s.append(s);
        }
        scoring.updateScore("lang:common_list_test_scoring_name",10);
        s = new StringBuffer("a");
        while (!f.isEmpty()) {
            String w = f.dequeue();
            assertEquals("dequeue", s.toString(), w);
            s.append(s);
        }
        scoring.updateScore("lang:common_list_test_scoring_name",10);
    }

    @Test
    public void test2() {
        ArrayF f = new ArrayF(10);
        for(int n = 1; n < 9; n++) {  // 1,2,3,4,5, ...8 * 10b
            StringBuffer s = new StringBuffer("a");
            assertTrue("isEmpty1", f.isEmpty());
            for (int i = 0; i < 20*n; i++) {
                f.enqueue(s.toString());
                assertFalse("isEmpty2", f.isEmpty());
                s.append("a");
            }
            scoring.updateScore("lang:common_list_test_scoring_name", 5);
            s = new StringBuffer("a");
            while (!f.isEmpty()) {
                String w = f.dequeue();
                assertEquals("dequeue", s.toString(), w);
                s.append("a");
            }
            scoring.updateScore("lang:common_list_test_scoring_name", 5);
        }
    }
}