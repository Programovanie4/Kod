import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestManualGeo {

	@Test
	public void testKruh() {
		assertEquals("obsah kruhu, new Kruh(new Bod2D(0,0),10).obsah()", 
				new solution.Kruh(new solution.Bod2D(0,0),10).obsah(),
				new Kruh(new Bod2D(0,0),10).obsah(),
				0.01);

		assertEquals("obvod kruhu, new Kruh(new Bod2D(10,0),10).obvod()", 
				new solution.Kruh(new solution.Bod2D(10,0),10).obvod(),
				new Kruh(new Bod2D(10,0),10).obvod(),
				0.01);

		assertEquals("obvod kruhu, new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(11,11))", 
				new solution.Kruh(new solution.Bod2D(10,10),10).jeV(new solution.Bod2D(11,11)),
				new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(11,11))
				);

		assertEquals("obvod kruhu, new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(111,11))", 
				new solution.Kruh(new solution.Bod2D(10,10),10).jeV(new solution.Bod2D(111,11)),
				new Kruh(new Bod2D(10,10),10).jeV(new Bod2D(111,11))
				);
    System.out.println("kruh ok");
  }

	@Test
	public void testObdlznik() {
		assertEquals("obsah obdlznika, new Obdlznik(new Bod2D(0,0),10,7).obsah()", 
				new solution.Obdlznik(new solution.Bod2D(0,0),10,7).obsah(),
				new Obdlznik(new Bod2D(0,0),10,7).obsah(),
				0.01);

		assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,0),10,7).obvod()", 
				new solution.Obdlznik(new solution.Bod2D(10,0),10,7).obvod(),
				new Obdlznik(new Bod2D(10,0),10,7).obvod(),
				0.01);

		assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(11,11))", 
				new solution.Obdlznik(new solution.Bod2D(10,10),10,7).jeV(new solution.Bod2D(11,11)),
				new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(11,11))
				);

		assertEquals("obvod obdlznika, new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(111,11))", 
				new solution.Obdlznik(new solution.Bod2D(10,10),10,7).jeV(new solution.Bod2D(111,11)),
				new Obdlznik(new Bod2D(10,10),10,7).jeV(new Bod2D(111,11))
				);
    System.out.println("obdlznik ok");
}

	
	@Test
	public void testKvader() {
		assertEquals("obsah kvadra, new Kvader(new Bod3D(0,0,0),10,7,8).objem()", 
				new solution.Kvader(new solution.Bod3D(0,0,0),10,7,8).objem(),
				new Kvader(new Bod3D(0,0,0),10,7,8).objem(),
				0.01);

		assertEquals("povrch kvadra, new Kvader(new Bod3D(0,0,0),10,7,8).povrch()", 
				new solution.Kvader(new solution.Bod3D(0,0,0),10,7,8).povrch(),
				new Kvader(new Bod3D(0,0,0),10,7,8).povrch(),
				0.01);

		assertEquals("new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(11,11,11))", 
				new solution.Kvader(new solution.Bod3D(0,0,0),10,7,8).jeV(new solution.Bod3D(11,11,11)),
				new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(11,11,11))
				);
		
		assertEquals("new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(1,1,1))", 
				new solution.Kvader(new solution.Bod3D(0,0,0),10,7,8).jeV(new solution.Bod3D(1,1,1)),
				new Kvader(new Bod3D(0,0,0),10,7,8).jeV(new Bod3D(1,1,1))
				);
    System.out.println("kvader ok");
}
	

	
	@Test
	public void testGula() {
		assertEquals("objem gule, new Gula(new Bod3D(0,0,0),10).objem()", 
				new solution.Gula(new solution.Bod3D(0,0,0),10).objem(),
				new Gula(new Bod3D(0,0,0),10).objem(),
				0.01);

		assertEquals("povrch gule, new Gula(new Bod3D(0,0,0),10).povrch()", 
				new solution.Gula(new solution.Bod3D(0,0,0),10).povrch(),
				new Gula(new Bod3D(0,0,0),10).povrch(),
				0.01);

		assertEquals("new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(11,11,11))", 
				new solution.Gula(new solution.Bod3D(0,0,0),10).jeV(new solution.Bod3D(11,11,11)),
				new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(11,11,11))
				);
		
		assertEquals("new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(1,1,1))", 
				new solution.Gula(new solution.Bod3D(0,0,0),10).jeV(new solution.Bod3D(1,1,1)),
				new Gula(new Bod3D(0,0,0),10).jeV(new Bod3D(1,1,1))
				);
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
			assertEquals("obe ok",
					true,
					k && o
			);
			System.out.println("DvaD.obe ok");
		} else fail("DvaD.obe nemas definovane");
		if (TriD.obe != null) {
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
			System.out.println("TriD.obe ok");
		} else fail("TriD.obe nemas definovane");
  }
}
