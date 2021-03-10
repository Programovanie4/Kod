package solution;

import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGeo {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testKruh() {
        {
            Kruh k = new Kruh(new Bod2D(0, 0), 10);
            k.posun(new Bod2D(1, 2));
            assertEquals("posun kruhu, new Kruh(new Bod2D(0,0),10).posun(new Bod2D(1,2))",
                    1, k.stred.getX(), 0.01);
        }
        {
            Kruh k = new Kruh(new Bod2D(0, 0), 10);
            k.posun(new Bod2D(1, 2));
            assertEquals("posun kruhu, new Kruh(new Bod2D(0,0),10).posun(new Bod2D(1,2))",
                    2, k.stred.getY(), 0.01);
        }

        assertEquals("obsah kruhu, new Kruh(new Bod2D(0,0),10).obsah()",
                new Kruh(new Bod2D(0,0),10).obsah(),
                new Kruh(new Bod2D(0,0),10).obsah(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("obvod kruhu, new Kruh(new Bod2D(10,0),10).obvod()",
                new Kruh(new Bod2D(10,0),10).obvod(),
                new Kruh(new Bod2D(10,0),10).obvod(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("obvod kruhu, new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(11,11))",
                new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(11,11)),
                new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(11,11))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("obvod kruhu, new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(111,11))",
                new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(111,11)),
                new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(111,11))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        boolean k = false, o = false;
        for (DvaD x : DvaD.obe) {
            if (x instanceof Kruh) {
                k = true;
            }
            if (x instanceof Obdlznik) {
                o = true;
            }
        }
        assertEquals("obe ok",
                true,
                k && o
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);
    }

    @Test
    public void testObdlznik() {
        {
            Obdlznik k = new Obdlznik(new Bod2D(0,0),10,7);
            k.posun(new Bod2D(1, 2));
            assertEquals("posun obdlznika, new Obdlznik(new Bod2D(0,0),10,7).posun(new Bod2D(1,2))",
                    1, k.lavyDolny.getX(), 0.01);
        }
        {
            Obdlznik k = new Obdlznik(new Bod2D(0,0),10,7);
            k.posun(new Bod2D(1, 2));
            assertEquals("posun obdlznika, new Obdlznik(new Bod2D(0,0),10,7).posun(new Bod2D(1,2))",
                    2, k.lavyDolny.getY(), 0.01);
        }


        assertEquals("obsah obdlznika, new Obdlznik(new Bod2D(0,0),10,7).obsah()",
                new Obdlznik(new Bod2D(0,0),10,7).obsah(),
                new Obdlznik(new Bod2D(0,0),10,7).obsah(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,0),10,7).obvod()",
                new Obdlznik(new Bod2D(10,0),10,7).obvod(),
                new Obdlznik(new Bod2D(10,0),10,7).obvod(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(11,11))",
                new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(11,11)),
                new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(11,11))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(111,11))",
                new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(111,11)),
                new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(111,11))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        boolean k = false, o = false;
        for (DvaD x : DvaD.obe) {
            if (x instanceof Kruh) {
                k = true;
            }
            if (x instanceof Obdlznik) {
                o = true;
            }
        }
        assertEquals("obe ok",
                true,
                k && o
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);
    }


    @Test
    public void testKvader() {
        {
            Kvader k = new Kvader(new Bod3D(0,0,0),10,7, 14);
            k.posun(new Bod3D(1, 2, 3));
            assertEquals("posun kvadra, new Kvader(new Bod3D(0,0,0),10,7, 14).posun(new Bod3D(1, 2, 3))",
                    1, k.lavyDolny.getX(), 0.01);
        }
        {
            Kvader k = new Kvader(new Bod3D(0,0,0),10,7, 14);
            k.posun(new Bod3D(1, 2, 3));
            assertEquals("posun kvadra, new Kvader(new Bod3D(0,0,0),10,7, 14).posun(new Bod3D(1, 2, 3))",
                    2, k.lavyDolny.getY(), 0.01);
        }
        {
            Kvader k = new Kvader(new Bod3D(0,0,0),10,7, 14);
            k.posun(new Bod3D(1, 2, 3));
            assertEquals("posun kvadra, new Kvader(new Bod3D(0,0,0),10,7, 14).posun(new Bod3D(1, 2, 3))",
                    3, k.lavyDolny.getZ(), 0.01);
        }


        assertEquals("obsah kvadra, new Kvader(new Bod3D(0,0,0),10,7,8).objem()",
                new Kvader(new Bod3D(0,0,0),10,7,8).objem(),
                new Kvader(new Bod3D(0,0,0),10,7,8).objem(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("povrch kvadra, new Kvader(new Bod3D(0,0,0),10,7,8).povrch()",
                new Kvader(new Bod3D(0,0,0),10,7,8).povrch(),
                new Kvader(new Bod3D(0,0,0),10,7,8).povrch(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(11,11,11))",
                new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(11,11,11)),
                new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(11,11,11))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(1,1,1))",
                new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(1,1,1)),
                new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(1,1,1))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        boolean k = false, o = false;
        for (TriD x : TriD.obe) {
            if (x instanceof Gula) {
                k = true;
            }
            if (x instanceof Kvader) {
                o = true;
            }
        }
        assertEquals("obe ok",
                true,
                k && o
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);
    }



    @Test
    public void testGula() {
        {
            Gula k = new Gula(new Bod3D(0,0,0),10);
            k.posun(new Bod3D(1, 2, 3));
            assertEquals("posun gule, nnew Gula(new Bod3D(0,0,0),10).posun(new Bod3D(1, 2, 3))",
                    1, k.stred.getX(), 0.01);
        }
        {
            Gula k = new Gula(new Bod3D(0,0,0),10);
            k.posun(new Bod3D(1, 2, 3));
            assertEquals("posun gule, nnew Gula(new Bod3D(0,0,0),10).posun(new Bod3D(1, 2, 3))",
                    2, k.stred.getY(), 0.01);
        }
        {
            Gula k = new Gula(new Bod3D(0,0,0),10);
            k.posun(new Bod3D(1, 2, 3));
            assertEquals("posun gule, nnew Gula(new Bod3D(0,0,0),10).posun(new Bod3D(1, 2, 3))",
                    3, k.stred.getZ(), 0.01);
        }


        assertEquals("objem gule, new Gula(new Bod3D(0,0,0),10).objem()",
                new Gula(new Bod3D(0,0,0),10).objem(),
                new Gula(new Bod3D(0,0,0),10).objem(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("povrch gule, new Gula(new Bod3D(0,0,0),10).povrch()",
                new Gula(new Bod3D(0,0,0),10).povrch(),
                new Gula(new Bod3D(0,0,0),10).povrch(),
                0.01);
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(11,11,11))",
                new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(11,11,11)),
                new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(11,11,11))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        assertEquals("new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(1,1,1))",
                new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(1,1,1)),
                new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(1,1,1))
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);

        boolean k = false, o = false;
        for (TriD x : TriD.obe) {
            if (x instanceof Gula) {
                k = true;
            }
            if (x instanceof Kvader) {
                o = true;
            }
        }
        assertEquals("obe ok",
                true,
                k && o
        );
        TestGeo.scoring.updateScore("lang:common_list_test_scoring_name",5);
    }

}
