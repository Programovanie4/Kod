import javafx.scene.image.ImageView;

import java.io.*;
import java.util.Random;

public class PexesoState implements Serializable {
    private static final long serialVersionUID = 918972645L;
    int elapsedTime = 0;            // cas od zaciatku hry
    boolean player1turn = true;     // ktory hrac je na tahu
    int scoreFirstPlayer = 0;       // skore
    int scoreSecondPlayer = 0;      // skore
    CardState[][] plocha = new CardState[Pexeso.SIZE][Pexeso.SIZE]; // rozlozenie karticiek na hracej ploche
    Random rand = new Random();



    // vnorena trieda
    public static class CardState implements Serializable {
        private static final long serialVersionUID = 911775039L;
        int id;
        boolean visible = false;
        transient ImageView image;    // transient znamena ze to nechceme serializovat

        public CardState(int id) {
            this.id = id;
            image = new ImageView("images/34.gif"); // TODO nacitat obrazok
        }

        // obrat karticku
        public void revert() {
            visible = !visible;
        }
    }



    // konstruktor
    public PexesoState() {
        // TODO Potrebujeme vygenerovat dvojice rovnakych kraticiek, a umistnint ich nahodne na hraciu plochu
        for (int i = 0; i < Pexeso.SIZE; i++) {
            for (int j = 0; j < Pexeso.SIZE; j++) {
                plocha[i][j] = new CardState(9); // TODO
            }
        }
    }

    // daj stav karty
    public CardState get(int i, int j) {
        if (0 <= i && i < plocha.length && 0 <= j && j < plocha[i].length)
            return plocha[i][j];
        else
            return null;
    }

    // obrat vsetky karticky
    public void showhide() {
        for (int i = 0; i < Pexeso.SIZE; i++) {
            for (int j = 0; j < Pexeso.SIZE; j++) {
                plocha[i][j].revert();
            }
        }
    }

    // uloz konfig do suboru
    public void save(String filePath) {
        // TODO
    }

    // precitaj konfig do suboru
    public static PexesoState load(String filePath) {
        // TODO
        return null;
    }

}
