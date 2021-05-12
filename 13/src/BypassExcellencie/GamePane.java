package BypassExcellencie;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.*;
import java.util.Timer;

class Srzbl implements Serializable{
    public Integer[][] config;
    public Integer sz;
    public int score = 0, secs = 0;
}

public class GamePane extends BorderPane {
    transient public Canvas cnv;
    transient private HBox infoPane, btnsPane;
    transient public GraphicsContext gc;
    transient private CanvClickHandler canvClickHandler;
    transient public Label scoreVal, timeVal;
    Srzbl srzbl = new Srzbl();

    public void load(){
        try{
            FileInputStream fileIn = new FileInputStream("gp.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Srzbl tmp = (Srzbl) in.readObject();
            srzbl.config = tmp.config;
            srzbl.sz = tmp.sz;
            srzbl.score = tmp.score;
            srzbl.secs = tmp.secs;
            in.close();
            fileIn.close();
            update();
        }
        catch (IOException | ClassNotFoundException i){
            i.printStackTrace();
        }
    }

    public void save(){
        try {
            FileOutputStream fileOut = new FileOutputStream("gp.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(srzbl);
            out.close();
            fileOut.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }

    public GamePane(Integer[][] config) {
        srzbl.config = config;
        Label scoreText = new Label("srzbl.score: ");
        scoreVal = new Label("0");
        HBox stepPane = new HBox(scoreText, scoreVal);
        Label timeText = new Label("Time: ");
        timeVal = new Label("0");
        HBox timePane = new HBox(timeText, timeVal);
        infoPane = new HBox(stepPane, timePane);

        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                save();
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                load();
            }
        });

        btnsPane = new HBox(loadButton, saveButton, exitButton);

        btnsPane.setSpacing(20);
        btnsPane.setAlignment(Pos.BOTTOM_CENTER);
        infoPane.setSpacing(20);
        infoPane.setAlignment(Pos.TOP_CENTER);
        this.setTop(infoPane);
        this.setBottom(btnsPane);

        Timer tmr = new Timer();
        tmr.schedule(new Rdtsc(this), 0, 1000);

        cnv = new Canvas(500, 500);
        gc = cnv.getGraphicsContext2D();
        this.setCenter(cnv);

        canvClickHandler = new CanvClickHandler(this);
        cnv.addEventHandler(MouseEvent.MOUSE_CLICKED, canvClickHandler);
    }

    public void update(){
        Platform.runLater(new Runnable() {
            public void run() {
                cnv.setHeight(prefHeightProperty().doubleValue()-infoPane.prefHeightProperty().doubleValue()-btnsPane.prefHeightProperty().doubleValue()-50);
                cnv.setWidth(prefWidthProperty().doubleValue());

                scoreVal.setText(Integer.toString(srzbl.score));
                timeVal.setText(Integer.toString(srzbl.secs));
                srzbl.sz = (int) Math.min(Math.min((cnv.getHeight()+2)/srzbl.config.length, cnv.getWidth()/srzbl.config[0].length), 85);
                gc.clearRect(0, 0, cnv.getWidth(), cnv.getHeight());

                for (int i = 0; i < srzbl.config.length; i++){
                    for (int j = 0; j < srzbl.config[0].length; j++){
                        Image img = new Image(String.format("Obrazky/%d.png", srzbl.config[i][j]),
                                srzbl.sz, srzbl.sz, false, true);
                        gc.drawImage(img, j*srzbl.sz, i*srzbl.sz);
                    }
                }

            }
        });
    }

}