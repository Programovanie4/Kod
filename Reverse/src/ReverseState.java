import javafx.scene.image.Image;

import java.io.Serializable;

public class ReverseState implements Serializable {

   int elapsedTime = 0;
   int playerOnTurn = 0;
   int scorePlayer1 = 0;
   int scorePlayer2 = 0;
   int[][] plocha = new int[Reverse.SIZE][Reverse.SIZE];  // -1 = x; 1 = o; 0 = nic
   transient Image img = null;


    // uloz konfig do suboru
    public void save(String filePath) {
        // TODO
    }

    // precitaj konfig do suboru
    public static ReverseState load(String filePath) {
        // TODO
        return null;
    }
}
