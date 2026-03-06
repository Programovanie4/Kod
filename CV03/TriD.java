abstract class TriD {
	public abstract double objem();
	public abstract double povrch();
	public abstract boolean jeV(Bod3D b);
	public abstract void posun(Bod3D b);
	
	// definujte konstantu obe, ktora obsahuje lubovolny kavder a gulu v lubovolnom poradi
	public static final TriD[] obe = {
			new Kvader(new Bod3D(0,0,0), 1, 2, 3),
			new Gula(new Bod3D(0,0,0), 10)
	};
}
