import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPodobne {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
//    @Test
//    public void testPodobne() {
//        assertTrue("test 1", Podobne.podobne(null, null));
//        assertFalse("test 2", Podobne.podobne(new String[]{}, null));
//        assertTrue("test 3", Podobne.podobne(new String[]{"aa", null, ""}, new Integer[] {2,null,0}));
//    }

    @Test
    public void testPodobne2() {
        assertTrue("test 1", Podobne.podobne2(null, null));
        assertFalse("test 2", Podobne.podobne2(new String[][]{}, null));
        assertFalse("test 2", Podobne.podobne2(new String[][]{null}, null));
        assertFalse("test 2", Podobne.podobne2(new String[][]{{null}}, null));
        assertFalse("test 2", Podobne.podobne2(new String[][]{{""}}, null));
        assertFalse("test 2", Podobne.podobne2(new String[][]{{""},null}, null));

        assertFalse("test 2", Podobne.podobne2(new String[][]{null, {""},null}, new Integer[][]{null, null, null}));
        assertFalse("test 2", Podobne.podobne2(new String[][]{null, {""}}, new Integer[][]{null, null, null}));

        assertFalse("test 2", Podobne.podobne2(null, new Integer[][]{}));
        assertFalse("test 2", Podobne.podobne2(null, new Integer[][]{null}));
        assertFalse("test 2", Podobne.podobne2(null, new Integer[][]{{null}}));
        assertFalse("test 2", Podobne.podobne2(null, new Integer[][]{{0}}));
        assertFalse("test 2", Podobne.podobne2(null, new Integer[][]{{0,null}}));
        assertFalse("test 2", Podobne.podobne2(new String[][]{null, null ,null}, new Integer[][]{null, new Integer[]{}, null}));
        assertFalse("test 2", Podobne.podobne2(new String[][]{null, null ,null}, new Integer[][]{null, new Integer[]{}}));


        assertTrue("test 3", Podobne.podobne2(new String[][]{{"aa", null, ""}}, new Integer[][]{{2,null,0}}));
        assertTrue("test 3", Podobne.podobne2(new String[][]{{null, null, ""}}, new Integer[][]{{null,null,0}}));
        assertFalse("test 3", Podobne.podobne2(new String[][]{{"aba", null, ""}}, new Integer[][]{{3,null,0}}));
        assertFalse("test 3", Podobne.podobne2(new String[][]{{"bb", null, ""}}, new Integer[][]{{2,null,0}}));
        assertFalse("test 3", Podobne.podobne2(new String[][]{{"aa", null, ""}, {"a", "", null}}, new Integer[][]{{1,0, null}}));
        assertTrue("test 3", Podobne.podobne2(new String[][]{{"aa", null, ""}, {"a", "", null}}, new Integer[][]{{2,null,0},{1,0, null}}));

        assertFalse("test 3", Podobne.podobne2(new String[][]{{"aa", null, ""}}, new Integer[][]{{-2,null,0}}));
        assertFalse("test 3", Podobne.podobne2(new String[][]{{"ab", null, ""}}, new Integer[][]{{-2,null,0}}));
        assertFalse("test 3", Podobne.podobne2(new String[][]{{ null, ""}}, new Integer[][]{{-7,0}}));
        assertFalse("test 3", Podobne.podobne2(new String[][]{{ ""}}, new Integer[][]{{-7}}));

        scoring.updateScore("lang:common_list_test_scoring_name",100);
    }
}