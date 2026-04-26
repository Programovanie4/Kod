import java.util.List;
import java.util.Random;

public class Rieka extends Thread {
    private final int width;
    private final List<Character> row;  // zdielane riadok riek
    private final Random random = new Random();
    private int position;

    public Rieka (int width, List<Character> row) {
        this.width = width;
        this.row = row;
        synchronized (row) {
            do {
                position = random.nextInt(width);
            } while (row.get(position) != ' ');
            row.set(position,'o');
        }
       // start();
    }

    @Override
    public void run() {
        for (;;) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ee) {  }
            int r = random.nextInt(3);
            if (r < 1) position = (position > 0) ? position - 1 : position;
            else if (r > 1) position = (position < width - 1) ? position + 1 : position;
            synchronized (row) {
                if (row.get(position) == ' ') row.set(position, 'o');
                else return;  // rieka sa zastavila, lebo sa stretla s inou riekou
            }
        }
    }
}