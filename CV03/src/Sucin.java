public class Sucin extends Polynom  {

    Polynom a, b;

    public Sucin(Polynom a, Polynom b) {
        this.a = a;
        this.b = b;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        return a.valueAt(vars, values) * b.valueAt(vars, values);
    }

    @Override
    Polynom derive(String var) {
        Polynom part1 = new Sucin(a.derive(var), b);
        Polynom part2 = new Sucin(b.derive(var), a);
        return new Sucet(part1, part2);
    }

    @Override
    public String toString() {
        return a.toString()+" * "+b.toString();
    }
}
