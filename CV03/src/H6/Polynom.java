package H6;

abstract class Polynom {
    abstract Double valueAt(String[] vars, double[] values);

    abstract Polynom derive(String var);

    public static void main(String[] args) {
        String[] vars = {"x", "y"};
        double[] values = {8, -1};

        Polynom k = new Konstanta(42);
        System.out.println("Konstanta = " + k);
        System.out.println("Hodnota konstanty pri {x=8, y=-1} = " + k.valueAt(vars, values));
        System.out.println("Derivacia konstanty = " + k.derive("x"));

        Polynom x = new Premenna("x");
        Polynom y = new Premenna("y");
        System.out.println("\nPremenna x = " + x);
        System.out.println("Hodnota premennej x pri {x=8, y=-1} = " + x.valueAt(vars, values));
        System.out.println("Derivacia premennej x podla x = " + x.derive("x"));
        System.out.println("Derivacia premennej x podla y = " + x.derive("y"));

        Polynom sucet = new Sucet(x, y);
        System.out.println("\nSucet x+y = " + sucet);
        System.out.println("Hodnota suctu pri {x=8, y=-1} = " + sucet.valueAt(vars, values));
        System.out.println("Derivacia suctu podla x = " + sucet.derive("x"));
        System.out.println("Derivacia suctu podla y = " + sucet.derive("y"));

        Polynom sucin = new Sucin(x, x);
        System.out.println("\nSucin x*x = " + sucin);
        System.out.println("Hodnota sucinu pri {x=8, y=-1} = " + sucin.valueAt(vars, values));
        System.out.println("Derivacia sucinu podla x = " + sucin.derive("x"));
        System.out.println("Derivacia sucinu podla y = " + sucin.derive("y"));


        Polynom p1 = new Sucet(new Sucin(x, x), new Konstanta(1));
        System.out.println("\nPolynom p1 = " + p1);
        System.out.println("Hodnota p1 pri {x=5} = " + p1.valueAt(new String[]{"x"}, new double[]{5})); // 26
        Polynom p2 = p1.derive("x"); // 2*x
        System.out.println("Polynom p2 = derivacia p1 podla x = " + p2);
        System.out.println("Hodnota p2 pri {x=3} = " + p2.valueAt(new String[]{"x"}, new double[]{3})); // 6

        Polynom p3 = new Sucet(new Sucin(y, y), new Sucin(new Konstanta(2), x));
        System.out.println("\nPolynom p3 = " + p3);
        System.out.println("Hodnota p3 pri {x=-2, y=3} = " + p1.valueAt(new String[]{"x","y"}, new double[]{-2,3})); // 5
        Polynom p4 = p3.derive("y"); // 2*x
        System.out.println("Polynom p4 = derivacia p3 podla y = " + p4);
        System.out.println("Hodnota p4 pri {x=-2, y=3} = " + p4.valueAt(new String[]{"x","y"}, new double[]{-2,3})); // 6
    }
}
