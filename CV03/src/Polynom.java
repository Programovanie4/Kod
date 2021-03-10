abstract class Polynom {

    abstract double valueAt(String[] vars, double[] values);

    abstract Polynom derive(String var);

    public static void main(String[] args) {
        Polynom p1 = new Sucet(new Sucin(new Premenna("x"), new Premenna("x")), new Konstanta(1) );
        System.out.println(p1.toString() + "..." + p1.valueAt(new String[]{"x"}, new double[]{5})); // 26
        Polynom p2 = p1.derive("x"); // 2*x
        System.out.println(p2.toString() + "..." +p2.valueAt(new String[]{"x"}, new double[]{3})); // 6
    }
}
