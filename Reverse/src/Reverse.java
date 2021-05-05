import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.Level;

public class Reverse extends Application {
    final static int SIZE = 8;
    final static int CELLSIZE = 40;
    BorderPane root;                // pane: plocha celej aplikacie
    Image x_img, o_img;
    Button load = new Button("Load");
    Button save = new Button("Save");
    Button quit = new Button("Quit");
    Label lbPlayer = new Label("X");
    Label lbTime = new Label("Cas: ");
    Label lbScore = new Label("Score: ");
    ReverseState state = new ReverseState();

    @Override
    public void start(Stage primaryStage) throws Exception {
        x_img = new Image("images/x.gif", CELLSIZE, CELLSIZE, false, false);
        o_img = new Image("images/o.gif", CELLSIZE, CELLSIZE, false, false);
        Timeline tikadielko = new Timeline(new KeyFrame(new Duration(1000), e-> {
            state.elapsedTime++;
            lbTime.setText("Cas: " + state.elapsedTime);
        }));
        tikadielko.setCycleCount(Timeline.INDEFINITE);
        tikadielko.play();

        quit.setOnAction(e -> {
            Platform.exit();
        });
        save.setOnAction(e -> {
            state.save("config");
        });
        load.setOnAction(e -> {
            state = state.load("config");
            // repaint
        });
        root = new BorderPane();
        HBox topPane = new HBox(lbPlayer, lbTime, lbScore);
        topPane.setAlignment(Pos.CENTER);
        topPane.setSpacing(40);
        root.setTop(topPane);
        HBox buttonPane = new HBox(load, save, quit);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(40);
        root.setBottom(buttonPane);
        Playground pg = new Playground();
        root.setCenter(pg);
        primaryStage.setTitle("Reverse");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public class Playground extends GridPane {
        public Playground() {
            for (int i=0; i < SIZE; i++) {
                for (int j=0; j < SIZE; j++) {
                    add(new CardPane(i,j),i,j);
                }
            }
        }
        public void paint() {

        }
    }
    public class CardPane extends Pane {
        int row, col;
        public CardPane(int row, int col) {
            this.col = col; this.row = row;
            setPrefHeight(CELLSIZE);
            setPrefWidth(CELLSIZE);
            if ((row + col) % 2 == 0)
                setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
            else
                setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

            getChildren().clear();
            if ((row + col) % 2 == 0)
                getChildren().add(new ImageView(o_img));
            else
                getChildren().add(new ImageView(x_img));

            setOnMouseClicked(e -> {
                System.out.println("klikol si na: " + row + ":" + col);
            });
        }
    }
}
