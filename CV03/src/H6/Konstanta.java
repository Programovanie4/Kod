package H6;

public class Konstanta extends Polynom {

    double konst;

    public Konstanta(double k) {
        konst = k;
    }


    @Override
    Double valueAt(String[] vars, double[] values) {
        return konst;
    }

    ;

    @Override
    Polynom derive(String var) {
        return new Konstanta(0);
    }

    ;

    @Override
    public String toString() {
        return "" + konst;
    }
}
