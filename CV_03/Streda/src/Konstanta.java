public class Konstanta extends Polynom {
    private final double value;

    public Konstanta(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    Double valueAt(String[] vars, double[] values) {
        return value;
    }

    @Override
    Polynom derive(String var) {
        return new Konstanta(0);
    }
}
