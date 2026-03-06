import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestZoo {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testZoo() {
        Pes pes1 = new Pes(), pes2 = new Pes();
        Macka macka1 = new Macka(), macka2 = new Macka();
        Zviera[] zoo = {pes1, macka1, pes2, macka2};
        for (Zviera zver : zoo) {
            String trieda = zver.getClass().getName();
            String zvuk = ("Pes".equals(trieda)) ? "haf" : "mnau";
            assertEquals("Ako robi " + trieda + "?", zvuk, zver.urobZvuk());
        }

        scoring.updateScore("lang:common_list_test_scoring_name", 100);
    }

}
