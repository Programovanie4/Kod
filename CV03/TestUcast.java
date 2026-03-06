import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TestUcast {

    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }


    @Test
    public void ucast() {
		if (Ucast.whoIAm() == null) {
			fail("don't be a null !");
        }
		if (Ucast.whoIAm().equals("Jozko Mrkvicka")) {
			fail("sorry, it's me, not you !");
        }
		if (!Ucast.whoIAm().contains(" ")) {
			fail("is it your first or last name ?");
        }

        TestUcast.scoring.updateScore("lang:common_list_test_scoring_name",100);
    }
}