import java.io.*;

public class Whisperers1 {
    static class Whisperer extends Thread {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis;
        int id;
        public Whisperer(int id, PipedOutputStream prevPos) {
            this.id = id;
            try {
                pis = new PipedInputStream(prevPos);
                //System.out.println("Whisperer " + id + " created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            start();
        }
        public void run() {
            while(true) {
                try {
                    char msg = (char)pis.read();
                    //System.out.println("Whisperer " + id + " received message: " + msg);
                    pos.write(msg+1);
                    pos.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    final static int N = 100000; // number of Whisperer
    final static int M = 20; // number of messages
    public static void main(String[] args) {
        Whisperers1 s = new Whisperers1();
        try {
            PipedOutputStream first = new PipedOutputStream();
            Whisperer sepkar = new Whisperer(0, first);
            for (int i = 1; i < N; i++)
                sepkar = new Whisperer(i, sepkar.pos);
            long start = System.currentTimeMillis();
            for (char m = 'a'; m < 'a' + M; m++) {
                first.write(m);
                first.flush();
            }
            var last = new PipedInputStream(sepkar.pos);
            while(true) {
                System.out.printf("elapsed time: %d, result: %c\n",
                        System.currentTimeMillis ()-start,
                        (char)last.read());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

