package H6;

abstract class TriD {
	public abstract double objem();
	public abstract double povrch();
	public abstract boolean jeV(Bod3D b);
	public abstract void posun(Bod3D b);
	
	// definujte konstantu obe, ktora obsahuje lubovolny kavder a gulu v lubovolnom poradi
	public static final TriD[] obe = null;
}
