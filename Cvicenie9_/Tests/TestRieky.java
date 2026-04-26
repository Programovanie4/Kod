//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestRieky {

//    private static LISTTestScoring scoring = null;
//    @BeforeClass
//    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
//    }

    @Test
    public void testSimulation0() {
        int width = 10;
        int rivers = 1;
        int simulationTime = 20;
        test(width, rivers, simulationTime);
//        scoring.updateScore("lang:common_list_test_scoring_name", 10.0D);
    }
    @Test
    public void testSimulation() {
        int width = 10;
        int rivers = 0;
        int simulationTime = 20;
        test(width, rivers, simulationTime);
  //      scoring.updateScore("lang:common_list_test_scoring_name", 10.0D);
    }

    @Test
    public void testSimulation1() {
        int width = 30;
        int rivers = 10;
        int simulationTime = 20;
        test(width, rivers, simulationTime);
    //    scoring.updateScore("lang:common_list_test_scoring_name", 40.0D);
    }
    @Test
    public void testSimulation2() {
        int width = 10;
        int rivers = 10;
        int simulationTime = 30;
        test(width, rivers, simulationTime);
      //  scoring.updateScore("lang:common_list_test_scoring_name", 40.0D);
    }

    private static void test(int width, int rivers, int simulationTime) {
        String s = Rieky.simulation(width, rivers, simulationTime);
        assertNotNull("vysledny retazec je null", s);
        assertTrue("vysledny retazec ma nulovu dlzku", s.length() > 0);
        String[] lines = s.split("\n");
        assertTrue("vysledok by mal mat viacej riadkov", lines.length > 7);
        for (String line : lines) {
            assertTrue("riadok by mal mat viacej znakov " + line, line.length() > 0);
            assertTrue("riadok by mal mat menej znakov" + line, line.length() <= width);
            int r = 0;
            for (char c : line.toCharArray()) {
                assertTrue("riadok by mal obsahovat len znaky o znamky 'o' a ' '"  + line, c == 'o' || c == ' ');
                if (c == 'o') r++;
            }
            assertTrue("riadok obsahuje vela riek" +  line, r <= rivers);
        }
        var lst = Arrays.stream(lines).map(line -> Arrays.stream(line.chars().toArray())
                .filter(c -> (c == 'o')).count()).toList();

        var lst1 = new ArrayList<>(lst).stream().sorted(Comparator.reverseOrder()).toList();
        assertEquals("rieky by nemali pribudat " + lst1, lst1, lst);
    }
}