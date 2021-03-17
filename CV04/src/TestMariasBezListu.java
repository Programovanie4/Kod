import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class TestMariasBezListu {

    @Test
    public void testKarta() {
        {
            Karta k1 = new Karta(Farba.CERVEN, Hodnota.ESO);
            Karta k2 = new Karta(Farba.CERVEN, Hodnota.ESO);
            if (k1.compareTo(k2) != 0)
                fail("zle porovnava rovnake karty");
        }
        {
            Karta k1 = new Karta(Farba.ZELEN, Hodnota.ESO);
            Karta k2 = new Karta(Farba.CERVEN, Hodnota.ESO);
            if (k1.compareTo(k2) >= 0)
                fail("cervene eso je predsa najvyssia karta");
        }
        {
            Karta k1 = new Karta(Farba.ZELEN, Hodnota.SEDMA);
            Karta k2 = new Karta(Farba.ZELEN, Hodnota.KRAL);
            if (k1.compareTo(k2) >= 0)
                fail("zelena sedna je predsa najnizsia karta");
        }

    }

    @Test
    public void testDeck() {
        Karta[] deck = new Marias().deck();
        if (deck == null)
            fail("new Marias().deck() vratil null");
        if (deck.length != 32)
            fail("new Marias().deck() nevratil 32 kariet");
        for(int i = 0; i < 32; i++)
            for(int j = i+1; j < 32; j++)
                if (deck[i].compareTo(deck[j]) == 0)
                    fail("new Marias().deck() nevratil rozne karty");
    }
    @Test
    public void testMiesaj() {
        for(int loops = 0; loops<50; loops++) {
            Marias m = new Marias();
            Karta[] deck1 = m.deck();
            Karta[] deck1clone = Arrays.copyOf(deck1, deck1.length);
            m.miesaj();
            Karta[] deck2 = m.deck();
            if (deck2 == null)
                fail("new Marias().deck() vratil null");
            if (deck2.length != 32)
                fail("new Marias().deck() nevratil 32 kariet");
            for (int i = 0; i < 32; i++)
                for (int j = i + 1; j < 32; j++)
                    if (deck2[i].compareTo(deck2[j]) == 0)
                        fail("new Marias().deck() nevratil rozne karty");
            boolean allSame = true;
            for (int i = 0; i < 32; i++) {
                if (deck1clone[i].compareTo(deck2[i]) != 0)
                    allSame = false;
            }
            if (allSame)
                fail("new Marias().miesaj() to nepomiesal " + Arrays.asList(deck2));
        }
    }

    @Test
    public void testUtried() {
        for(int loops = 0; loops<50; loops++) {
            Marias m = new Marias();
            m.miesaj();
            m.utried();
            Karta[] deck2 = m.deck();
            if (deck2 == null)
                fail("new Marias().deck() vratil null");
            if (deck2.length != 32)
                fail("new Marias().deck() nevratil 32 kariet");
            for (int i = 0; i < 32; i++)
                for (int j = i + 1; j < 32; j++)
                    if (deck2[i].compareTo(deck2[j]) == 0)
                        fail("new Marias().deck() nevratil rozne karty");
            boolean alless = true;
            for (int i = 0; i < 31; i++) {
                if (deck2[i].compareTo(deck2[i+1]) < 0)
                    alless = false;
            }
            if (alless)
                fail("new Marias().utried() to neutriedil " + Arrays.asList(deck2));
        }
    }

    @Test
    public void testBerie() {
        for(int loops = 0; loops<50; loops++) {
            Marias m = new Marias();
            Karta[] deck1 = m.deck();
            Karta[] deck1clone = Arrays.copyOf(deck1, deck1.length);
            m.miesaj();
            Karta[] deck2 = m.deck();
            if (deck2 == null)
                fail("new Marias().deck() vratil null");
            if (deck2.length != 32)
                fail("new Marias().deck() nevratil 32 kariet");
            for (int i = 0; i < 32; i++)
                for (int j = i + 1; j < 32; j++)
                    if (deck2[i].compareTo(deck2[j]) == 0)
                        fail("new Marias().deck() nevratil rozne karty");
            boolean allSame = true;
            for (int i = 0; i < 32; i++) {
                if (deck1clone[i].compareTo(deck2[i]) != 0)
                    allSame = false;
            }
            Karta cerveneEso = Marias.berie(deck2);
            if (cerveneEso == null)
                fail("new Marias().berie() nratilo null " + Arrays.asList(deck2));
            if (new Karta(Farba.CERVEN, Hodnota.ESO).compareTo(cerveneEso) != 0)
                fail("new Marias().berie() malo vratit cervene eso " + Arrays.asList(deck2));
        }
    }
}