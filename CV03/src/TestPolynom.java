import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class TestPolynom {

    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

//    @Test
//    public void valueAt() {
//        Polynom p1 = new Sucet(new Sucin(new Premenna("x"), new Premenna("x")), new Konstanta(1) );
//        assertEquals(p1.toString(), 26, p1.valueAt(new String[]{"x"}, new double[]{5}).doubleValue(),0.001);
//        Polynom p2 = new Sucet(new Sucin(new Premenna("x"), new Premenna("x")), new Sucin(new Premenna("x"), new Premenna("x")) );
//        assertEquals(p2.toString(), 50, p2.valueAt(new String[]{"x"}, new double[]{5}).doubleValue(),0.001);
//        assertEquals(p2.toString(), 2, p2.valueAt(new String[]{"x"}, new double[]{-1}).doubleValue(),0.001);
//        Polynom p3 = new Sucet(new Sucin(new Premenna("x"), new Premenna("x")), new Konstanta(1) );
//        assertEquals(p3.toString(), 26, p3.valueAt(new String[]{"y","x"}, new double[]{1,5}).doubleValue(),0.001);
//        TestPolynom.scoring.updateScore("lang:common_list_test_scoring_name",50);
//    }

//    @Test
//    public void derive() {
//        {
//            Polynom p0 = new Konstanta(1);
//            assertEquals(p0.toString()  + " dx at {x/5}", 0, p0.derive("x").valueAt(new String[]{"x"}, new double[]{5}).doubleValue(), 0.001);
//        }
//        {
//            Polynom p0 = new Premenna("x");
//            assertEquals(p0.toString()  + " dx at {x/5}", 1, p0.derive("x").valueAt(new String[]{"x"}, new double[]{5}).doubleValue(), 0.001);
//        }
//        {
//            Polynom p0 = new Premenna("z");
//            assertEquals(p0.toString() + " dx at {x/5}", 0, p0.derive("x").valueAt(new String[]{"x"}, new double[]{5}).doubleValue(), 0.001);
//        }
//        {
//            Polynom p0 = new Sucet(new Premenna("z"), new Premenna("x"));
//            assertEquals(p0.toString() + " dx at {z/5}", 1, p0.derive("x").valueAt(new String[]{"z"}, new double[]{5}).doubleValue(), 0.001);
//        }
//        {
//            Polynom p0 = new Sucet(new Premenna("z"), new Premenna("x"));
//            assertEquals(p0.toString() + " dz at {z/5}", 1, p0.derive("z").valueAt(new String[]{"z"}, new double[]{5}).doubleValue(), 0.001);
//        }
//        {
//            Polynom p0 = new Sucin(new Premenna("z"), new Premenna("x"));
//            assertEquals(p0.toString(), 5, p0.derive("x").valueAt(new String[]{"x","z"}, new double[]{11,5}).doubleValue(), 0.001);
//        }
//        Polynom p1 = new Sucet(new Sucin(new Premenna("x"), new Premenna("x")), new Konstanta(1) );
//        assertEquals(p1.toString()  + " dx at {x/5}", 26, p1.valueAt(new String[]{"x"}, new double[]{5}).doubleValue(),0.001);
//        Polynom p2 = p1.derive("x"); // 2*x
//        assertEquals(p2.toString()  + " dx at {x/5}", 10, p2.valueAt(new String[]{"x"}, new double[]{5}).doubleValue(),0.001);
//        Polynom p3 = p1.derive("y"); // 2*x
//        assertEquals(p3.toString() + " dx at {x/5}", 0, p3.valueAt(new String[]{"x"}, new double[]{5}).doubleValue(),0.001);
//        TestPolynom.scoring.updateScore("lang:common_list_test_scoring_name",50);
//    }
}