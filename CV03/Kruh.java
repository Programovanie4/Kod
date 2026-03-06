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
		return Math.PI*polomer*polomer;
	}
	/**
	 * defunjte metodu, co pocita obvod kruhu
	 */
	@Override
	public double obvod() {
		return 2*Math.PI*polomer;
	}
	/**
	 * defunjte metodu, co zisti, ci bod b je v kruhu/na kruznici
	 */	
	@Override
	public boolean jeV(Bod2D b) {
		return Math.sqrt(Math.pow(stred.getX() - b.getX(),2) + Math.pow(stred.getY() - b.getY(),2)) <= polomer;
	}
	/**
	 * definujte metodu, co posunie kruh o vektor b
	 */
	@Override
	public void posun(Bod2D b) {
		stred = new Bod2D(stred.getX() + b.getX(), stred.getY()+b.getY());
	}
}
