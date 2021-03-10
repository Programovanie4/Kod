package H6;

public class Kvader extends TriD {
	public Bod3D lavyDolny;
	public double dx, dy, dz;

	public Kvader(Bod3D lavyDolny, double dx, double dy, double dz) {
		super();
		this.lavyDolny = lavyDolny;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}
	/**
	 * definujte objem kvadra 
	 */
	@Override
	public double objem() {
		return 0;
	}
	/**
	 * definujte povrch kvadra 
	 */
	@Override
	public double povrch() {
		return 0;
	}
	/**
	 * definujte test, ci bod b je v kvadri 
	 */
	@Override
	public boolean jeV(Bod3D b) {
		return false;
	}
	/**
	 * definujte posunutie kvadra o vektor b 
	 */
	@Override
	public void posun(Bod3D b) {
		return;
	}
}
