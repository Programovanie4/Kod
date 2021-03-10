public class Konstanta extends Polynom {

    double value;

    public Konstanta(double k) {
        this.value = k;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        return value;
    }

    @Override
    Polynom derive(String var) {
        return new Konstanta(0);
    }

    @Override
    public String toString() {
        return value+"";
    }
}
