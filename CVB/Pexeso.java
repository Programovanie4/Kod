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

public class Pexeso extends Application {
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
    PexesoState state = new PexesoState();  // stav celej hry
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

            this.setOnMouseClicked(e -> {
                state.get(row, col).image = new ImageView("images/" + (1 + (int) (Math.random() * 30)) + ".gif");
                System.out.println(row + ", " + col);
                paint();
            });
        }

        public void paint() {
            //System.out.println("preslim karticku " + row + "x" + col);
            double w = getWidth();
            double h = getHeight();
            getChildren().clear();
            PexesoState.CardState karticka = state.get(row, col);
            if (karticka.visible) {
                // nechceme citat subor pri kazdom kresleni!!!!!
                ImageView obrazok = karticka.image;
                // TODO vykreslit obrazok
                getChildren().add(obrazok);
            } else {
                // TODO vykreslit sedy obdlznik
                ImageView obrazok = karticka.image;
                // TODO vykreslit obrazok
                getChildren().add(obrazok);
            }
        }
    }



    // vnorena trieda
    public class PlaygroundPane extends GridPane {

        public PlaygroundPane() {
            setWidth(400);
            setHeight(400);
            // inicializacia policok pre karticky
            for (int riadok = 0; riadok < Pexeso.SIZE; riadok++) {
                for (int stlpec = 0; stlpec < Pexeso.SIZE; stlpec++) {
                    CardPane karticka = new CardPane(riadok, stlpec);
                    this.add(karticka, stlpec, riadok);
                    karticka.paint();
                }
            }
        }

        public void paint() {
            // TODO vypisat spravny text do labelov lbPlayer a lbScore, a zavolat .paint na vsetky karticky
        }
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        // Pocitanie casu
        Timeline tikadielko = new Timeline(new KeyFrame(new Duration(1000), e -> {
            state.elapsedTime++;
            lbTime.setText("??as: " + state.elapsedTime);
        }));
        tikadielko.setCycleCount(7);
        tikadielko.play();


        // Odchytavanie tlacitok
        quit.setOnAction(e -> {
            System.out.println("quit button");
            // System.exit(0);  // takto nie
            Platform.exit();
        });
        // TODO spravanie ostatnych tlacitok


        // Odchytavanie klavesnice (mimo zadania, ale robi to to iste ako tlacitka)
        root.setOnKeyPressed(event -> {
            System.out.println("key pressed");
            if (event.getCode() == KeyCode.Q) {
                System.out.println("quit key");
                Platform.exit();
            } else if (event.getCode() == KeyCode.L) {
                System.out.println("load key");
                state = PexesoState.load("savedGame");
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
