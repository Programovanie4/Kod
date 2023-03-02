public class Sucin extends Polynom {
    private final Polynom a;
    private final Polynom b;

    public Sucin(Polynom a, Polynom b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + " * " + b + ")";
    }

    @Override
    Double valueAt(String[] vars, double[] values) {
        Double valA = a.valueAt(vars, values);
        Double valB = b.valueAt(vars, values);

        if (valA == null || valB == null) {
            return null;
        }

        return valA * valB;
    }

    @Override
    Polynom derive(String var) {
        Polynom dA = a.derive(var);
        Polynom dB = b.derive(var);

        return new Sucet(
                new Sucin(dB, a),
                new Sucin(dA, b)
        );
    }
}
