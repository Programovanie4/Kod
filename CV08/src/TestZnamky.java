import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestZnamky {

    private static LISTTestScoring scoring = null;

    private static Znamky studentskeZnamky;
    private static ZnamkyAutorske autorskeZnamky;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);

        try {
            String fileName = "data-test.csv";
            studentskeZnamky = new Znamky(fileName);
            autorskeZnamky = new ZnamkyAutorske(fileName);
        } catch (IOException e) {
            System.err.println("Problem s citanim suboru 'data.csv'.");
            e.printStackTrace();
        }
    }

    @Test
    public void testPriemerZnamokPreTriedy() {
        assertEquals("Test metody priemerZnamokPreTriedy", autorskeZnamky.priemerZnamokPreTriedy(), studentskeZnamky.priemerZnamokPreTriedy());
        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void testModusZnamokPreTriedy() {
        assertEquals("Test metody modusZnamokPreTriedy", autorskeZnamky.modusZnamokPreTriedy(), studentskeZnamky.modusZnamokPreTriedy());
        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void testMedianZnamokPreTriedy() {
        assertEquals("Test metody medianZnamokPreTriedy", autorskeZnamky.medianZnamokPreTriedy(), studentskeZnamky.medianZnamokPreTriedy());
        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void testPocetnostZnamokPreTriedy() {
        assertEquals("Test metody pocetnostZnamokPreTriedy", autorskeZnamky.pocetnostZnamokPreTriedy(), studentskeZnamky.pocetnostZnamokPreTriedy());
        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void testNadpriemerniZiaciVTriedach() {
        assertEquals("Test metody pocetnostZnamokPreTriedy", autorskeZnamky.nadpriemerniZiaciVTriedach(), studentskeZnamky.nadpriemerniZiaciVTriedach());
        scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

}