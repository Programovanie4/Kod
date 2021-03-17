import java.util.Random;

public class Karta implements Comparable<Karta> {
    Farba f;
    Hodnota h;

    @Override
    public String toString() {
        return "(" + f + "," + h + ')';
    }

    public Farba getF() {
        return f;
    }

    public void setF(Farba f) {
        this.f = f;
    }

    public Hodnota getH() {
        return h;
    }

    public void setH(Hodnota h) {
        this.h = h;
    }

    private static Random rnd = new Random();
    public Karta(Farba f, Hodnota h) {
        this.f = f;
        this.h = h;
    }
    public Karta() {
        this(
                Farba.values()[rnd.nextInt(Farba.values().length)],
                Hodnota.values()[rnd.nextInt(Hodnota.values().length)]
        );
    }

    @Override
    public int compareTo(Karta m) {
        int res = f.compareTo(m.f);
        if (res != 0)
            return res;
        return h.compareTo(m.h);
    }
}
