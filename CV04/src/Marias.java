import java.util.Arrays;
import java.util.Random;






//class Karta implements Comparable<Karta> {
//    Farba f;
//    Hodnota h;
//
//    @Override
//    public String toString() {
//        return "(" + f + "," + h + ')';
//    }
//
//    public Farba getF() {
//        return f;
//    }
//
//    public void setF(Farba f) {
//        this.f = f;
//    }
//
//    public Hodnota getH() {
//        return h;
//    }
//
//    public void setH(Hodnota h) {
//        this.h = h;
//    }
//
//    private static Random rnd = new Random();
//    public Karta(Farba f, Hodnota h) {
//        this.f = f;
//        this.h = h;
//    }
//    public Karta() {
//        this(
//            Farba.values()[rnd.nextInt(Farba.values().length)],
//            Hodnota.values()[rnd.nextInt(Hodnota.values().length)]
//        );
//    }
//
//    @Override
//    public int compareTo(Karta m) {
//        int res = f.compareTo(m.f);
//        if (res != 0)
//            return res;
//        return h.compareTo(m.h);
//    }
//}

public class Marias implements Hra<Karta> {
    Karta[] karty;
    public Marias() {
        karty = new Karta[Farba.values().length * Hodnota.values().length];
        int i = 0;
        for(Farba f : Farba.values())
            for(Hodnota h : Hodnota.values()) {
                karty[i++] = new Karta(f, h);
            }
    }
    @Override
    public void miesaj() {
//        karty = new Karta[Farba.values().length * Hodnota.values().length];
//        int i = 0;
//        for(Farba f : Farba.values())
//            for(Hodnota h : Hodnota.values()) {
//                karty[i++] = new Karta();
//            }
        Random rnd = new Random();
        for(int n = 0; n < 1000; n++) {
            int i = rnd.nextInt(karty.length);
            int j = rnd.nextInt(karty.length);
            Karta tmp = karty[i];
            karty[i] = karty[j];
            karty[j] = tmp;
        }
    }

    @Override
    public void utried() {
        int n = karty.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (karty[j].compareTo(karty[j + 1]) > 0) {
                    Karta temp = karty[j];
                    karty[j] = karty[j + 1];
                    karty[j + 1] = temp;
                }
    }

    public static Karta berie(Karta[] cards) {
        if (cards != null) {
            Karta res = cards[0];
            for (Karta k : cards)
                if (res.compareTo(k) < 0)
                    res = k;
            return res;
        } else
            return null;
    }

    public Karta[] deck() {
        return karty;
    }
    public static void main(String[] args) {
        System.out.println(
                new Karta(Farba.ZELEN, Hodnota.SEDMA).compareTo(
                new Karta(Farba.CERVEN, Hodnota.ESO))
        );
        Marias m = new Marias();
        System.out.println(Arrays.asList(m.deck()));
        m.miesaj();
        System.out.println(Arrays.asList(m.deck()));
        m.utried();
        System.out.println(Arrays.asList(m.deck()));
    }

}
