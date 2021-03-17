package MariasZadanie;

import java.util.Arrays;

public class Marias implements Hra<Karta> {
    Karta[] karty;
    public Marias() { /* implementuj */ }
    public void miesaj() { /* implementuj */ }
    public void utried() { /* implementuj */ }
    public static Karta berie(Karta[] cards) { return null; /* implementuj */ }
    public Karta[] deck() { return null; /* implementuj */ }

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
