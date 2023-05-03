import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.text.EditorKit;

public class SingleStageDemo extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button btn = new Button("Press me !");
			Pane root = new Pane(btn);
			//root.getChildren().add
			//Rectangle r = new Rectangle(0,0,100,100);
			// Button b = new Button();
			// new TextField()
			//new TextArea()
			//new TextField
			// 1.moznost
			root.setStyle("-fx-background-color: #" + "FFFF00");
			// 2.moznost
//			Color c = Color.YELLOW;
//			root.setStyle("-fx-background-color: #" +
//					Integer.toHexString((int)(((255<<8)<<8)*c.getRed() +
//												   (255<<8)*c.getGreen() +
//												   (1<<8-1)*c.getBlue()))
//					);
			// 3.moznost
//			root.setBackground(
//					new Background(
//							new BackgroundFill(
//										Color.YELLOW, null, null)
//							)
//					);

            // 4.moznost
//            root.setBackground(
//                    new Background(
//                            new BackgroundFill(
//                                    new Color(1,0.60,0.90, 1), null, null)
//                    )
//            );

            Scene scene = new Scene(root,400,400, Color.ORANGE);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Primary stage");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
