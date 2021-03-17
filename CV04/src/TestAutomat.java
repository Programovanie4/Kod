import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestAutomat {

    private static LISTTestScoring scoring = null;
    private static Random randomGenerator = new Random();

    private static AutoNodeSol nodeASol = new AutoNodeSol('a',randomGenerator);
    private static AutoNodeSol nodeBSol = new AutoNodeSol('b', randomGenerator);
    private static AutoNodeSol nodeCSol = new AutoNodeSol('c', randomGenerator);
    private static AutoNodeSol nodeXSol = new AutoNodeSol('x', randomGenerator);
    private static AutoNodeSol nodeYSol = new AutoNodeSol('y', randomGenerator);
    private static AutoNodeSol nodeZSol = new AutoNodeSol('z', randomGenerator);
    private static AutomatSol myAutomat1Sol = new AutomatSol(nodeASol);
    private static AutomatSol myAutomat3Sol = new AutomatSol(nodeYSol);

    private static AutoNode nodeA = new AutoNode('a',randomGenerator);
    private static AutoNode nodeB = new AutoNode('b', randomGenerator);
    private static AutoNode nodeC = new AutoNode('c', randomGenerator);
    private static AutoNode nodeX = new AutoNode('x', randomGenerator);
    private static AutoNode nodeY = new AutoNode('y', randomGenerator);
    private static AutoNode nodeZ = new AutoNode('z', randomGenerator);
    private static Automat myAutomat1 = new Automat(nodeA);
    private static Automat myAutomat2 = new Automat(nodeX);
    private static Automat myAutomat3 = new Automat(nodeY);

    private int length1 = 10;
    private int length2 = 100;

    private String dryExampleGood = "aaaabcccbab";
    private String dryExampleBad = "xyz";
    private String dryExampleGenerator2 = "xxxxxxxxxx";

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @BeforeClass
    public static void initAutomata() {
        nodeA.setConnections(new AutoNode[]{nodeA, nodeB});
        nodeB.setConnections(new AutoNode[]{nodeA, nodeC});
        nodeC.setConnections(new AutoNode[]{nodeB, nodeC});
        nodeX.setConnections(new AutoNode[]{nodeX});
        nodeY.setConnections(new AutoNode[]{nodeY, nodeZ});
        nodeZ.setConnections(new AutoNode[]{nodeY, nodeX});
        nodeASol.setConnections(new AutoNodeSol[]{nodeASol, nodeBSol});
        nodeBSol.setConnections(new AutoNodeSol[]{nodeASol, nodeCSol});
        nodeCSol.setConnections(new AutoNodeSol[]{nodeBSol, nodeCSol});
        nodeXSol.setConnections(new AutoNodeSol[]{nodeXSol});
        nodeYSol.setConnections(new AutoNodeSol[]{nodeYSol, nodeZSol});
        nodeZSol.setConnections(new AutoNodeSol[]{nodeYSol, nodeXSol});
    }

    @Test
    public void testAutomat1() {
        String word2 = myAutomat2.generate(length1);
        assertEquals("Slovo zo samych x", word2, dryExampleGenerator2);
        assertEquals("Slovo zo samych x dlzky "+length1, length1, word2.length());
        assertTrue("Slovo " + dryExampleGood + " je validne", myAutomat1.accepts(dryExampleGood));
        assertFalse("Slovo " + dryExampleBad + " nie je validne", myAutomat1.accepts(dryExampleBad));
        TestAutomat.scoring.updateScore("lang:common_list_test_scoring_name",50);
    }

    @Test
    public void testAutomat2() {
        for (int i = 0; i < 4; i++) {
            String word1 = myAutomat1.generate(length2);
            assertEquals("Slovo " + word1 + " zacina pismenom " + myAutomat1.first().getValue(), myAutomat1.first().getValue(), word1.charAt(0));
            assertEquals("Slovo " + word1 + " ma dlzku " + length2, length2, word1.length());
            assertTrue("Slovo " + word1 + " je validne", myAutomat1Sol.accepts(word1));
            String word2 = myAutomat3.generate(length1);
            assertEquals("Slovo " + word2 + " zacina pismenom " + myAutomat1.first().getValue(), word2.charAt(0), myAutomat3.first().getValue());
            assertEquals("Slovo " + word2 + " ma dlzku " + length1, length1, word2.length());
            assertTrue("Slovo " + word2 + " je validne", myAutomat3Sol.accepts(word2));
            String word3 = myAutomat1Sol.generate(length2);
            assertTrue("Slovo " + word3 + " je validne", myAutomat1.accepts(word3));
            String word4 = myAutomat3Sol.generate(length2);
            assertTrue("Slovo " + word4 + " je validne", myAutomat3.accepts(word4));
        }
        TestAutomat.scoring.updateScore("lang:common_list_test_scoring_name",50);
    }
}