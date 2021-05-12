package BypassExcellencie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scanner sc = new Scanner(new FileInputStream("level1"));
        Integer w = sc.nextInt(), h = sc.nextInt();
        Integer[][] pos = new Integer [h][w];
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                pos[i][j] = sc.nextInt();
                System.out.printf(String.format("%d ", pos[i][j]));
            }
            System.out.println("");
        }
        primaryStage.setTitle("Game World");

        GamePane gamePa = new GamePane(pos);
        Scene scene = new Scene(gamePa, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        gamePa.prefHeightProperty().bind(scene.heightProperty());
        gamePa.prefWidthProperty().bind(scene.widthProperty());
        scene.widthProperty().addListener(event -> gamePa.update());
        scene.heightProperty().addListener(event -> gamePa.update());

        gamePa.update();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
