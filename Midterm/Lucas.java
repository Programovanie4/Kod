import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.math.BigInteger;

public class Lucas extends Application {
    int i = 0;
    @Override
    public void start(Stage primaryStage) {
        var x = new LucasCanvas();
        Pane pane = new Pane(x); // do panelu vlozime Canvas
        Scene scene = new Scene(pane);				// vytvor scenu
        primaryStage.setTitle("Lucas"); 	// pomenuj okno aplikacie,
        // javisko
        primaryStage.setScene(scene); 	// vloz scenu do hlavneho okna, na javisko
        primaryStage.show(); 			// zobraz javisko
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent e)->x.paintCanvas(i++)));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class LucasCanvas extends Canvas {
    int sizeX = 800;
    int sizeY = sizeX;
    int MAX = 255;
    int scale = 2;
    int[] primes = {2,3,5,7,11};
    Color pallet[] = {Color.BEIGE, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK,
            Color.ORANGE, Color.BLACK, Color.WHITE, Color.GRAY, Color.AZURE, Color.CHARTREUSE, Color.ALICEBLUE};
    public LucasCanvas() {
        this.setWidth(sizeX);
        this.setHeight(sizeY);
    }
    public void paintCanvas(int p1) {
        GraphicsContext gc = getGraphicsContext2D();	// kreslenie do canvasu
        var p = primes[p1%primes.length];
            for (var i = 0; i * scale < sizeX; i++)
                for (var j = 0; j * scale < sizeY; j++) {
                    gc.setFill(pallet[lucas(i + j, i, p) % p]);
                    gc.fillRect(i * scale, j * scale, scale, scale);
                }
    }
    public static int lucas(int n, int r, int p) {
        int res = 1;
        for(; n > 0 && r > 0; n /= p, r /= p)
            res = (res * komb(n%p, r%p, p)) % p;
        return res;
    }
    public static int komb(int n, int k, int p) {
        BigInteger citatel = BigInteger.ONE;
        for (int i = n - k + 1; i < n + 1; i++) citatel = citatel.multiply(BigInteger.valueOf(i));
        BigInteger menovatel = BigInteger.ONE;
        for (int i = 1; i < k + 1; i++) menovatel = menovatel.multiply(BigInteger.valueOf(i));
        return citatel.divide(menovatel).mod(BigInteger.valueOf(p)).intValue();
    }
}
