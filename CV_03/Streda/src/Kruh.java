public class Kruh extends DvaD {
	public Bod2D stred;
	public double polomer;
	
	public Kruh(Bod2D stred, double polomer) {
		super();
		this.stred = stred;
		this.polomer = polomer;
	}
	/**
	 * defunjte metodu, co pocita obsah kruhu
	 */
	@Override
	public double obsah() {
		return 0;
	}
	/**
	 * defunjte metodu, co pocita obvod kruhu
	 */
	@Override
	public double obvod() {
		return 0;
	}
	/**
	 * defunjte metodu, co zisti, ci bod b je v kruhu/na kruznici
	 */	
	@Override
	public boolean jeV(Bod2D b) {
		return false;
	}
	/**
	 * definujte metodu, co posunie kruh o vektor b
	 */
	@Override
	public void posun(Bod2D b) {
		return;
	}
}
