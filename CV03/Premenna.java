public class Premenna extends Polynom {
    String s;
    public Premenna(String s) { this.s = s;}

    @Override
    Double valueAt(String[] vars, double[] values) {
        for (int i = 0; i < vars.length; i++) {
            if (vars[i].equals(s)) return values[i];
        }
        throw new IllegalArgumentException("Neznamy parameter: " + s);
    };
    @Override
    Polynom derive(String var) { return new Konstanta(s.equals(var)?1:0); };

    @Override
    public String toString() {
        return "Premenna{" +
                "s='" + s + '\'' +
                '}';
    }
}
