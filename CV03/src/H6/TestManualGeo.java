package H6;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestManualGeo {

    @Test
    public void testKruh() {
        assertEquals("obsah kruhu, new Kruh(new Bod2D(0,0),10).obsah()",
                314.1592653589793, new Kruh(new Bod2D(0, 0), 10).obsah(), 0.01);

        assertEquals("obvod kruhu, new Kruh(new Bod2D(10,0),10).obvod()",
                62.83185307179586, new Kruh(new Bod2D(10, 0), 10).obvod(), 0.01);

        assertTrue("obvod kruhu, new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(11,11))",
				new Kruh(new Bod2D(10, 10), 10).jeV(new Bod2D(11, 11)));

        assertFalse("obvod kruhu, new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(111,11))",
				new Kruh(new Bod2D(10, 10), 10).jeV(new Bod2D(111, 11)));
        System.out.println("kruh ok");
    }

    @Test
    public void testObdlznik() {
        assertEquals("obsah obdlznika, new Obdlznik(new Bod2D(0,0),10,7).obsah()",
                70.0, new Obdlznik(new Bod2D(0, 0), 10, 7).obsah(), 0.01);

        assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,0),10,7).obvod()",
                34.0, new Obdlznik(new Bod2D(10, 0), 10, 7).obvod(), 0.01);

        assertTrue("obvod obdlznika, new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(11,11))",
				new Obdlznik(new Bod2D(10, 10), 10, 7).jeV(new Bod2D(11, 11)));

        assertFalse("obvod obdlznika, new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(111,11))",
				new Obdlznik(new Bod2D(10, 10), 10, 7).jeV(new Bod2D(111, 11)));
        System.out.println("obdlznik ok");
    }

    @Test
    public void testKvader() {
        assertEquals("obsah kvadra, new Kvader(new Bod3D(0,0,0),10,7,8).objem()",
                560.0, new Kvader(new Bod3D(0, 0, 0), 10, 7, 8).objem(), 0.01);

        assertEquals("povrch kvadra, new Kvader(new Bod3D(0,0,0),10,7,8).povrch()",
                412.0, new Kvader(new Bod3D(0, 0, 0), 10, 7, 8).povrch(), 0.01);

        assertFalse("new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(11,11,11))",
				new Kvader(new Bod3D(0, 0, 0), 10, 7, 8).jeV(new Bod3D(11, 11, 11)));

        assertTrue("new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(1,1,1))",
				new Kvader(new Bod3D(0, 0, 0), 10, 7, 8).jeV(new Bod3D(1, 1, 1)));
        System.out.println("kvader ok");
    }

    @Test
    public void testGula() {
        assertEquals("objem gule, new Gula(new Bod3D(0,0,0),10).objem()",
                4188.790204786391, new Gula(new Bod3D(0, 0, 0), 10).objem(), 0.01);

        assertEquals("povrch gule, new Gula(new Bod3D(0,0,0),10).povrch()",
                1256.6370614359173, new Gula(new Bod3D(0, 0, 0), 10).povrch(), 0.01);

        assertFalse("new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(11,11,11))",
				new Gula(new Bod3D(0, 0, 0), 10).jeV(new Bod3D(11, 11, 11)));

        assertTrue("new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(1,1,1))",
				new Gula(new Bod3D(0, 0, 0), 10).jeV(new Bod3D(1, 1, 1)));
        System.out.println("gula ok");
    }

    @Test
    public void testObe() {

        boolean k = false, o = false;
        if (DvaD.obe != null) {
            for (DvaD x : DvaD.obe) {
                if (x instanceof Kruh) {
                    k = true;
                }
                if (x instanceof Obdlznik) {
                    o = true;
                }
            }
            assertTrue("obe ok", k && o);
            System.out.println("DvaD.obe ok");
        } else fail("DvaD.obe nemas definovane");

		k = false; o = false;
        if (TriD.obe != null) {
            for (TriD x : TriD.obe) {
                if (x instanceof Gula) {
                    k = true;
                }
                if (x instanceof Kvader) {
                    o = true;
                }
            }
            assertTrue("obe ok", k && o);
            System.out.println("TriD.obe ok");
        } else fail("TriD.obe nemas definovane");
    }
}
