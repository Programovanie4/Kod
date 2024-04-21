import java.io.*;
import java.util.Random;

public class Whisperers3 {
    static class Message implements Serializable {
        Integer id;
        Double value;

        public Message(Integer id, Double init) {
            this.id = id;
            this.value = init;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Message{");
            sb.append("id=").append(id);
            sb.append(", value='").append(value).append('\'');
            sb.append('}');
            return sb.toString();
        }
        public void update() {
            value += (new Random().nextDouble()-0.5) / 10;
            //value += 1;
        }
    }

    static class Whisperer extends Thread {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        int id;
        public Whisperer(int id, PipedOutputStream prevPos) {
            this.id = id;
            try {
                pis = new PipedInputStream(prevPos);
                //System.out.println("Whisperer " + id + " created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void connect() {
            try {
                oos = new ObjectOutputStream(pos);
                ois = new ObjectInputStream(pis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("Whisperer " + id + " connected");
        }
        public void run() {
            while(true) {
                try {
                    var msg = (Message)ois.readObject();
                    //System.out.println("Whisperer " + id + " received message: " + msg);
                    msg.update();
                    oos.writeObject(msg);
                    oos.flush();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    final static int N = 1000; // number of Whisperer
    final static int M = 20000; // number of messages
    public static void main(String[] args) {
        Whisperers3 s = new Whisperers3();
        try {
            PipedOutputStream first = new PipedOutputStream();
            Whisperer[] whisperers = new Whisperer[N];
            for (int i = 0; i < N; i++) {
                whisperers[i] = new Whisperer(i, (i>0)?whisperers[i-1].pos:first);
            }
            var last = new PipedInputStream(whisperers[N-1].pos);

            ObjectOutputStream firstos = new ObjectOutputStream(first);
            for (int i = 0; i < N; i++) {
                whisperers[i].connect();
                whisperers[i].start();
            }

            long start = System.currentTimeMillis();
            for (int m = 0; m < M; m++) {
                firstos.writeObject(new Message(m, 0.0));
                firstos.flush();
            }
            var lastis = new ObjectInputStream(last);
            while(true) {
//                System.out.printf("elapsed time: %d, result: %s\n",
//                        System.currentTimeMillis ()-start,
//                        (Message)lastis.readObject());
                System.out.println(
                        ((Message)lastis.readObject()).value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

