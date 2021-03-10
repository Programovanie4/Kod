package H6;

public class Sucin extends Polynom {
    Polynom a, b;

    public Sucin(Polynom a, Polynom b) {
        this.a = a;
        this.b = b;
    }

    @Override
    Double valueAt(String[] vars, double[] values) {
        return (a.valueAt(vars, values) * b.valueAt(vars, values));
    }

    ;

    @Override
    Polynom derive(String var) {

        return new Sucet(
                new Sucin(a.derive(var), b), new Sucin(b.derive(var), a)
        );
    }

    @Override
    public String toString() {
        return "(" + a + "*" + b + ')';
    }
}
