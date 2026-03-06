public class Sucet extends Polynom {
    public Sucet(Polynom a, Polynom b) { this.a = a; this.b = b;}
    Polynom a, b;
    @Override
    Double valueAt(String[] vars, double[] values) { return a.valueAt(vars, values) + b.valueAt(vars, values); };
    @Override
    Polynom derive(String var) { return new Sucet(a.derive(var), b.derive(var)); };

    @Override
    public String toString() {
        return "Sucet{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
