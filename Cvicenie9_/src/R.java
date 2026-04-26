import java.util.Random;

public class R extends Thread {
    char[] riadok;
    int w;
    int pozicia;
    Random rnd = new Random();
    public R(int w, char[] riadok) {
        this.w = w;
        this.riadok = riadok;
        synchronized (riadok) {
            do {
                pozicia = rnd.nextInt(w);
            } while (riadok[pozicia] != ' ');
            riadok[pozicia] = 'o';
        }
    }


    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int delta = rnd.nextInt(3) - 1;
            if (delta < 0) {
                pozicia = (pozicia > 0) ?pozicia - 1 : pozicia;
            } else if (delta > 0) {
                pozicia = (pozicia < w - 1) ? pozicia + 1 : pozicia;
            }
            synchronized (riadok) {
                if (riadok[pozicia] == ' ') {
                    riadok[pozicia] = 'o';
                } else {
                    return;
                }
            }
        }
    }
}
