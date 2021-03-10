public class Premenna extends Polynom {

    private String name;

    public Premenna(String s) {
        name = s;
    }

    @Override
    double valueAt(String[] vars, double[] values) {
        int varIndex = 0;
        for (int i = 0; i < vars.length; i++) {
            if (name.equals(vars[i])) {
                varIndex = i;
            }
        }
        return values[varIndex];
    }

    @Override
    Polynom derive(String var) {
        if (name.equals(var))
            return new Konstanta(1);
        else
            return new Konstanta(0);
    }

    @Override
    public String toString() {
        return name;
    }
}
