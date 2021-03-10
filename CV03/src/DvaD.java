abstract class DvaD implements DvaDInterface {
	// definujte konstantu obe, ktora obsahuje lubovolny obdlznik a kruh v lubovolnom poradi
	public static final DvaD[] obe = new DvaD[] {
			new Obdlznik(new Bod2D(1,1),1,1),
			new Kruh(new Bod2D(0,0),1)};

}
