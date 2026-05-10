package org.example.p.p12;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PexesoX extends Application {
    static final int SIZE = 8;      // pocet riadkov/stlpcov pexesa

    // GUI komponenty:
    BorderPane root;                // pane: plocha celej aplikacie
    PlaygroundPane playground;      // pane: plocha piskvoriek
    Label lbPlayer = new Label();   // label: ktory hrac je na tahu
    Label lbTime = new Label();     // label: uplynuly cas
    Label lbScore = new Label();    // label: ake je skore
    Button load = new Button("Load");
    Button save = new Button("Save");
    Button quit = new Button("Quit");
    Button show = new Button("Show");

    // Stav a ine pomocne premenne
    PexesoStateX state = new PexesoStateX();  // stav celej hry
    Integer first_card_row = null;          // ktora karticka bola otocena ako prva (v ramci tahu)
    Integer first_card_col = null;          // -||-
    boolean showhide_button_text = true;    // ci ma tlacitko text "Show" (true) alebo "Hide" (false)



    // vnorena trieda
    public class CardPane extends Pane {
        int row, col;

        public CardPane(int row, int col) {
            this.row = row;
            this.col = col;
            this.setWidth(40);
            this.setHeight(40);

            // TODO odchytit kliknutie mysou
                // TODO obratit karticku
                // TODO rozpoznat ci je to prva alebo druha karticka v ramci tahu, spravit prislusnu akciu
            //final PexesoState.CardState karticka;
            //root.setOnMouseClicked((ev)-> {karticka = state.get((int)ev.getX(), (int)ev.getY()); karticka.revert();}
            //);
        }

        public void paint() {
            //System.out.println("preslim karticku " + row + "x" + col);
            double w = getWidth();
            double h = getHeight();
            getChildren().clear();
            PexesoStateX.CardState karticka = state.get(row, col);
            if (karticka.visible) {
                // nechceme citat subor pri kazdom kresleni!!!!!
                ImageView obrazok = karticka.pikaImage;
                // TODO vykreslit obrazok
//                obrazok.setY(row); !!!!!!!!!!!!!! toto nie je dobre
//                obrazok.setY(col);
                obrazok.setFitWidth(w);
                obrazok.setFitHeight(h);
                getChildren().add(obrazok);

                System.out.println("pokemon");
            } else {
                // TODO vykreslit sedy obdlznik
                /*Rectangle x = new Rectangle(row,col,w,h);
                x.setFill(Color.GRAY);
                getChildren().add(x);*/
                ImageView obrazok = karticka.pikaImage;
//                obrazok.setY(row); !!!!!!!!!!! toto nie je dobre
//                obrazok.setY(col);
                obrazok.setFitWidth(w);
                obrazok.setFitHeight(h);
                getChildren().add(obrazok);
                System.out.println("obdlzik");
                /**to len aby som videla ci su dobre tie obrazky, kedze mi nejde tlacitko "show" */
            }
        }
    }



    // vnorena trieda
    public class PlaygroundPane extends GridPane {
        public PlaygroundPane() {
            setWidth(400);
            setHeight(400);
            // inicializacia policok pre karticky
            for (int riadok = 0; riadok < PexesoX.SIZE; riadok++) {
                for (int stlpec = 0; stlpec < PexesoX.SIZE; stlpec++) {
                    CardPane karticka = new CardPane(riadok, stlpec);
                    this.add(karticka, stlpec, riadok);
                    karticka.paint();
                }
            }
        }

        public void paint() {
            // TODO vypisat spravny text do labelov lbPlayer a lbScore, a zavolat .paint na vsetky
            lbPlayer.setText("Čas: " + state.elapsedTime);
            lbScore.setText("Čas: " + state.elapsedTime);
            System.out.println("SKUSKA");
            //!!!! tu sa mi zda, ze tento paint by mal prekreslit karticky, ci kto to robi ?
        }
    }


    public void otoc(){
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                state.plocha[i][j].visible = true;
            }
        }
    }
    public void otoc2(){
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                state.plocha[i][j].visible = false;
            }
        }
    }
    public void kresli(){
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                CardPane karticka = new CardPane(i, j);
                            // !!! toto vobec nechapem, ked prekreslujete
                            // !!! karticky, tak ich znovu vytvorite ?
                karticka.paint();
            }
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        // Pocitanie casu
        Timeline tikadielko = new Timeline(new KeyFrame(new Duration(1000), e -> {
            state.elapsedTime++;
            lbTime.setText("Čas: " + state.elapsedTime);
        }));
        tikadielko.setCycleCount(Timeline.INDEFINITE);
        tikadielko.play();


        // Odchytavanie tlacitok
        quit.setOnAction(e -> {
            System.out.println("quit button");
            // System.exit(0);  // takto nie
            Platform.exit();
        });
        // TODO spravanie ostatnych tlacitok
        load.setOnAction(e -> {
            System.out.println("load button");
            state = PexesoStateX.load("savedGame"); //TODO
            playground.paint();
        });
        save.setOnAction(e -> {
            System.out.println("save button");
            state.save("savedGame"); //TODO
        });
        show.setOnAction(e -> {
            if(showhide_button_text) {
                System.out.println("show button");
                otoc();
                kresli();
                showhide_button_text = false;
                show.setText("Hide");
            }
            else if(!showhide_button_text) {
                System.out.println("show button");
                otoc2();
                kresli();
                showhide_button_text = true;
                show.setText("Show");
            }
            playground.paint();
        });


        // Odchytavanie klavesnice (mimo zadania, ale robi to to iste ako tlacitka)
        root.setOnKeyPressed(event -> {
            System.out.println("key pressed");
            if (event.getCode() == KeyCode.Q) {
                System.out.println("quit key");
                Platform.exit();
            } else if (event.getCode() == KeyCode.L) {
                System.out.println("load key");
                state = PexesoStateX.load("savedGame");
                playground.paint();
            } else if (event.getCode() == KeyCode.S) {
                System.out.println("save key");
                state.save("savedGame");
            }
        });


        // Poskladame GUI:
        // top panel
        HBox topPanel = new HBox(lbPlayer, lbTime, lbScore);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setSpacing(40);
        root.setTop(topPanel);
        // bottom panel
        HBox bottomPanel = new HBox(load, save, show, quit);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.setSpacing(20);
        root.setBottom(bottomPanel);
        // playground
        root.setCenter(playground = new PlaygroundPane());

        // Doladime okno:
        primaryStage.setTitle("Pexeso");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        playground.paint();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
