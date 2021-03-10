abstract class TriD {
	public abstract double objem();
	public abstract double povrch();
	public abstract boolean jeV(Bod3D b);
	public abstract void posun(Bod3D b);
	
	// definujte konstantu obe, ktora obsahuje lubovolny kvader a gulu v lubovolnom poradi
	public static final TriD[] obe = new TriD[] {
			new Kvader(new Bod3D(1,1,1),2,2,2),
			new Gula(new Bod3D(4,4,4),3)};
}
