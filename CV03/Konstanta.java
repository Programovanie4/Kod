public class Konstanta extends Polynom {
    double k;
    public Konstanta(double k) { this.k = k;}
    @Override
    Double valueAt(String[] vars, double[] values) { return k; };
    @Override
    Polynom derive(String var) { return new Konstanta(0); };

    @Override
    public String toString() {
        return "Konstanta{" +
                "k=" + k +
                '}';
    }
}
