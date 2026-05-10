package org.example.p.p12;

import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PexesoStateX implements Serializable {
    private static final long serialVersionUID = 918972645L;
    int elapsedTime = 0;            // cas od zaciatku hry
    boolean player1turn = true;     // ktory hrac je na tahu
    int scoreFirstPlayer = 0;       // skore
    int scoreSecondPlayer = 0;      // skore
    CardState[][] plocha = new CardState[PexesoX.SIZE][PexesoX.SIZE]; // rozlozenie karticiek na hracej ploche
    Random rand = new Random();



    // vnorena trieda
    public static class CardState implements Serializable {
        private static final long serialVersionUID = 911775039L;
        int id;
        boolean visible = false;
        transient ImageView pikaImage;    // transient znamena ze to nechceme serializovat

        public CardState(int id) {
            this.id = id;
            //pikaImage = new ImageView("file:"+this.id+".gif"); // TODO nacitat obrazok
            pikaImage = new ImageView("file:images/" + this.id+".gif"); // TODO nacitat obrazok
            //pikaImage = new ImageView(new File("1.gif").toURI().toString());
            System.out.println("id="+this.id + pikaImage);
        }

        // obrat karticku
        public void revert() {
            visible = !visible;
        }
    }



    // konstruktor
    public PexesoStateX() {
        // TODO Potrebujeme vygenerovat dvojice rovnakych kraticiek, a umistnint ich nahodne na hraciu plochu
        List<Integer> x = zamiesajKarticky();
        Collections.shuffle(x);
        int counter = 0;
        for (int i = 0; i < PexesoX.SIZE; i++) {
            for (int j = 0; j < PexesoX.SIZE; j++) {
                plocha[i][j] = new CardState(x.get(counter));; // TODO
                counter++;
            }
        }
    }
    public List<Integer> zamiesajKarticky(){
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        int pocetKariet = (PexesoX.SIZE * PexesoX.SIZE) / 2;
        int prvy = rnd.nextInt(39)+1;
        list.add(prvy);
        list.add(prvy);
        int counter = 1;
        while(counter < pocetKariet){
            int x = rnd.nextInt(39)+1;
            if(!list.contains(x)) {
                list.add(x);
                list.add(x);
                counter++;
            }
        }
        return list;
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
        for (int i = 0; i < PexesoX.SIZE; i++) {
            for (int j = 0; j < PexesoX.SIZE; j++) {
                plocha[i][j].revert();
            }
        }
    }

    // uloz konfig do suboru
    public void save(String filePath) {
        // TODO
    }

    // precitaj konfig do suboru
    public static PexesoStateX load(String filePath) {
        // TODO
        return null;
    }

}
