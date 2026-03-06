import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGramatika {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testGramatikaChangeEasy() {
        assertEquals("GramatikaChange: ", "AC", Gramatika.change("ABC"));
        assertEquals("GramatikaChange: ", "BBAA", Gramatika.change("CCA"));
        assertEquals("GramatikaChange: ", "AC", Gramatika.change("CBA"));
        assertEquals("GramatikaChange: ", "AAC", Gramatika.change("CBBA"));
        assertEquals("GramatikaChange: ", "BBB", Gramatika.change("AAAA"));
        assertEquals("GramatikaChange: ", "CAAA", Gramatika.change("BCAB"));
        assertEquals("GramatikaChange: ", "CBBAC", Gramatika.change("BCCBC"));
        assertEquals("GramatikaChange: ", "CAAAAA", Gramatika.change("BCACA"));
        assertEquals("GramatikaChange: ", "AACA", Gramatika.change("ACBAC"));
        scoring.updateScore("lang:common_list_test_scoring_name", 15);
    }

    @Test
    public void testGramatikaChangeHard() {
        assertEquals("GramatikaChange: ", "ACAAACBBACAACAAAAAAA", Gramatika.change("BBCABCCBACBACACAB"));
        assertEquals("GramatikaChange: ", "BBACABBAAAACACACBBAAAACAAAA", Gramatika.change("CCBACCABBCBABCCACBACAC"));
        assertEquals("GramatikaChange: ", "ACABBACACACACAACBBAACAA", Gramatika.change("CBACCBCBABCBACBAAACBACB"));
        assertEquals("GramatikaChange: ", "BAAACACACACAACAAAACAAAACAC", Gramatika.change("AACBBABCBABCBBCACBCABBCBA"));
        assertEquals("GramatikaChange: ", "AACACACACACACAACAAACACAAAACACA", Gramatika.change("ACBABCBABCBABCBBACBBCBACABCBAB"));
        assertEquals("GramatikaChange: ", "BAACACACBAACACACACACACACACAC", Gramatika.change("AACBABCBAACBCBABCBABCBABCBABC"));
        assertEquals("GramatikaChange: ", "BBAACAAAAACAAACACACACACAAACACBAACACACA", Gramatika.change("AAACBACBBBBCABCBABCBABCABCBAACBABCBAB"));
        assertEquals("GramatikaChange: ", "AACACACBBAACAACAAACAAAACACAACACAAC", Gramatika.change("BBBCBABCCBBACBABBBACABCBABBCBABBC"));
        assertEquals("GramatikaChange: ", "ACACAACACABBBBAAABBACACACAACAAACAACAACACACAAACAAACA", Gramatika.change("BBCBABBCBACCCACCBABCBABBCBBBACBABBCBABCBBBACBBAC"));
        scoring.updateScore("lang:common_list_test_scoring_name", 20);
    }


    @Test
    public void testGramatikaNumberOfIterEasy() {
        assertEquals("GramatikaNumberOfIter: ", 2, Gramatika.numberOfIter("ABC"));
        assertEquals("GramatikaNumberOfIter: ", 4, Gramatika.numberOfIter("CCA"));
        assertEquals("GramatikaNumberOfIter: ", 2, Gramatika.numberOfIter("CBA"));
        assertEquals("GramatikaNumberOfIter: ", 3, Gramatika.numberOfIter("CBBA"));
        assertEquals("GramatikaNumberOfIter: ", 3, Gramatika.numberOfIter("AAAA"));
        assertEquals("GramatikaNumberOfIter: ", 5, Gramatika.numberOfIter("BCAB"));
        assertEquals("GramatikaNumberOfIter: ", 6, Gramatika.numberOfIter("BCCBC"));
        assertEquals("GramatikaNumberOfIter: ", 7, Gramatika.numberOfIter("BCACA"));
        assertEquals("GramatikaNumberOfIter: ", 5, Gramatika.numberOfIter("ACBAC"));
        scoring.updateScore("lang:common_list_test_scoring_name", 15);
    }

    @Test
    public void testGramatikaNumberOfIterHard() {
        assertEquals("GramatikaNumberOfIter: ", 25, Gramatika.numberOfIter("BBCABCCBACBACACAB"));
        assertEquals("GramatikaNumberOfIter: ", 32, Gramatika.numberOfIter("CCBACCABBCBABCCACBACAC"));
        assertEquals("GramatikaNumberOfIter: ", 31, Gramatika.numberOfIter("CBACCBCBABCBACBAAACBACB"));
        assertEquals("GramatikaNumberOfIter: ", 34, Gramatika.numberOfIter("AACBBABCBABCBBCACBCABBCBA"));
        assertEquals("GramatikaNumberOfIter: ", 42, Gramatika.numberOfIter("ACBABCBABCBABCBBACBBCBACABCBAB"));
        assertEquals("GramatikaNumberOfIter: ", 38, Gramatika.numberOfIter("AACBABCBAACBCBABCBABCBABCBABC"));
        assertEquals("GramatikaNumberOfIter: ", 49, Gramatika.numberOfIter("AAACBACBBBBCABCBABCBABCABCBAACBABCBAB"));
        assertEquals("GramatikaNumberOfIter: ", 46, Gramatika.numberOfIter("BBBCBABCCBBACBABBBACABCBABBCBABBC"));
        assertEquals("GramatikaNumberOfIter: ", 71, Gramatika.numberOfIter("BBCBABBCBACCCACCBABCBABBCBBBACBABBCBABCBBBACBBAC"));
        scoring.updateScore("lang:common_list_test_scoring_name", 15);
    }

    @Test
    public void testGramatikaIsCorrectOutputEasy() {
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("ABC", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("CCA", 'A'));
        assertEquals("GramatikaIsCorrectOutput: ", true, Gramatika.isCorrectOutput("CBA", 'A'));
        assertEquals("GramatikaIsCorrectOutput: ", true, Gramatika.isCorrectOutput("CBBA", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("AAAA", 'A'));
        assertEquals("GramatikaIsCorrectOutput: ", true, Gramatika.isCorrectOutput("BCAB", 'A'));
        assertEquals("GramatikaIsCorrectOutput: ", true, Gramatika.isCorrectOutput("BCCBC", 'B'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("BCACA", 'B'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("ACBAC", 'C'));
        scoring.updateScore("lang:common_list_test_scoring_name", 15);
    }

    @Test
    public void testGramatikaIsCorrectOutputHard() {
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("BBCABCCBACBACACAB", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("CCBACCABBCBABCCACBACAC", 'A'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("CBACCBCBABCBACBAAACBACB", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("AACBBABCBABCBBCACBCABBCBA", 'B'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("ACBABCBABCBABCBBACBBCBACABCBAB", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("AACBABCBAACBCBABCBABCBABCBABC", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("AAACBACBBBBCABCBABCBABCABCBAACBABCBAB", 'C'));
        assertEquals("GramatikaIsCorrectOutput: ", true, Gramatika.isCorrectOutput("BBBCBABCCBBACBABBBACABCBABBCBABBC", 'B'));
        assertEquals("GramatikaIsCorrectOutput: ", false, Gramatika.isCorrectOutput("BBCBABBCBACCCACCBABCBABBCBBBACBABBCBABCBBBACBBAC", 'C'));
        scoring.updateScore("lang:common_list_test_scoring_name", 20);
    }
}
