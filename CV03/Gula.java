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
		return 4*Math.PI*Math.pow(polomer, 3)/3;	}
	/**
	 * definujte metodu, co pocita povrch gule
	 */
	@Override
	public double povrch() {
		return 4*Math.PI*Math.pow(polomer, 2);
	}
	/**
	 * definujte metodu, co zisti, ci bo b je v/na guli
	 */
	@Override
	public boolean jeV(Bod3D  b) {
		return Math.sqrt(
				Math.pow(stred.getX() - b.getX(),2) +
						Math.pow(stred.getY() - b.getY(),2) +
						Math.pow(stred.getZ() - b.getZ(),2)) <= polomer;
	}
	/**
	 * definujte metodu, co posunie gulu o vektor b
	 */
	@Override
	public void posun(Bod3D b) {
		stred = new Bod3D(stred.getX() + b.getX(), stred.getY()+b.getY(), stred.getZ()+b.getZ());
	}
}
