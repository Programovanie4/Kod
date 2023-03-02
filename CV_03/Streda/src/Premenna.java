public class Premenna extends Polynom {
    private final String name;

    public Premenna(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    // ["x", "y", "z"] , [5, 12, 3.3]

    @Override
    Double valueAt(String[] vars, double[] values) {
        for (int idx = 0; idx < vars.length; idx++) {
            if (vars[idx].equals(name)) {
                return values[idx];
            }
        }

        return null;
    }

    @Override
    Polynom derive(String var) {
        if (var.equals(name)) {
            return new Konstanta(1);
        }

        return new Konstanta(0);
    }

}
