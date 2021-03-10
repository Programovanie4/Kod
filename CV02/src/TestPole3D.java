import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPole3D {

    @Test
    public void equalsIgnoreCase() {
        {
            String pole1[][][] = {{{"ahoj"}}};
            String pole2[][][] = {{{"aHoj"}}};
            assertTrue("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {{{"ahoj"}, {null}}};
            String pole2[][][] = {{{"aHoj"}, {    }}};
            assertFalse("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = null;
            String pole2[][][] = null;
            assertTrue("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = null;
            String pole2[][][] = {null};
            assertFalse("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null};
            String pole2[][][] = {null};
            assertTrue("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null};
            String pole2[][][] = {null,null};
            assertFalse("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null,{null, {}}};
            String pole2[][][] = {null,{null, {}}};
            assertTrue("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null,{null, {"aBBa"}}};
            String pole2[][][] = {null,{null, {"abba"}}};
            assertTrue("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null,{null, {null,"aBBa"}}};
            String pole2[][][] = {null,{null, {null,"abba"}}};
            assertTrue("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null,{null, {null,"aBBa"}}};
            String pole2[][][] = {null,{null, {"","abba"}}};
            assertFalse("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
        {
            String pole1[][][] = {null,{null}};
            String pole2[][][] = {null,{null, {"","abba"}}};
            assertFalse("pole1: " + toString(pole1) + " s polom " + toString(pole2),
                    Pole3D.equalsIgnoreCase(pole1, pole2));
        }
    }

    public String toString(String[] a) {
        return Arrays.toString(a);
    }
    public String toString(String[][] a) {
        if (a == null) return "null";
        String res = "[";
        for(String[] r : a)  res += toString(r) + ",";
        return res+"]";
    }
    public String toString(String[][][] a) {
        if (a == null) return "null";
        String res = "[";
        for(String[][] r : a)  res += toString(r) + ",";
        return res+"]";
    }
}