import java.io.*;

public class Whisperers2 {
    static class Whisperer extends Thread {
        PipedWriter pos = new PipedWriter();
        PipedReader pis;
        int id;
        public Whisperer(int id, PipedWriter prevPos) {
            this.id = id;
            try {
                pis = new PipedReader(prevPos);
                //System.out.println("Whisperer " + id + " created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            start();
        }
        public void run() {
            while(true) {
                try {
                    var msg = pis.read();
                    //System.out.println("Whisperer " + id + " received message: " + msg);
                    pos.write(msg+1);
                    pos.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    final static int N = 1000; // number of Whisperer
    final static int M = 20; // number of messages
    public static void main(String[] args) {
        Whisperers2 s = new Whisperers2();
        try {
            PipedWriter first = new PipedWriter();
            Whisperer sepkar = new Whisperer(0, first);
            for (int i = 1; i < N; i++)
                sepkar = new Whisperer(i, sepkar.pos);
            long start = System.currentTimeMillis();
            for (int m = 0; m < M; m++) {
                first.write(m);
                first.flush();
            }
            var last = new PipedReader(sepkar.pos);
            while(true) {
                System.out.printf("elapsed time: %d, result: %d\n",
                        System.currentTimeMillis ()-start,
                        last.read());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

