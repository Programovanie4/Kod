package H6;

public class Gula extends TriD {
	public Bod3D stred;
	public double polomer;
	
	public Gula(Bod3D stred, double polomer) {
		super();
		this.stred = stred;
		this.polomer = polomer;
	}
	/**
	 * definujte metodu, co pocita objem gule
	 */
	@Override
	public double objem() {	
		return 0;
	}
	/**
	 * definujte metodu, co pocita povrch gule
	 */
	@Override
	public double povrch() {
		return 0;
	}
	/**
	 * definujte metodu, co zisti, ci bo b je v/na guli
	 */
	@Override
	public boolean jeV(Bod3D b) {
		return false; 
	}
	/**
	 * definujte metodu, co posunie gulu o vektor b
	 */
	@Override
	public void posun(Bod3D b) {
		return;
	}
}
