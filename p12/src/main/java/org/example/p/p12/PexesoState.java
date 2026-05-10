package org.example.p.p12;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        transient ImageView pikaImage;    // transient znamena ze to nechceme serializovat

        public CardState(int id) {
            this.id = id;
            System.out.println( "file:images/" + this.id+".gif");
            pikaImage = new ImageView("file:images/" + this.id+".gif"); // TODO nacitat obrazok

        }

        // obrat karticku
        public void revert() {
            System.out.println("revert" + this.id

            );
            visible = !visible;
        }
    }



    // konstruktor
    public PexesoState() {
        List<Integer> x = zamiesajKarticky();
        Collections.shuffle(x);
        int counter = 0;
        for (int i = 0; i < Pexeso.SIZE; i++) {
            for (int j = 0; j < Pexeso.SIZE; j++) {
                plocha[i][j] = new CardState(x.get(counter));; // TODO
                counter++;
            }
        }
    }
    private List<Integer> zamiesajKarticky(){
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
    public CardState  get(int i, int j) {
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
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // precitaj konfig do suboru
    public static PexesoState load(String filePath) {
        try {
            ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File(filePath)));
            return (PexesoState)oos.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}