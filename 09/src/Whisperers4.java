import java.io.*;
import java.util.Random;

public class Whisperers4 {
    static class Message implements Serializable {
        Integer id;
        Integer value;

        public Message(Integer id, Integer init) {
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
            //value += (new Random().nextDouble()-0.5) / 10;
            //value += 1;
        }
    }

    static class Whisperer extends Thread {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        int id;
        Integer firstValue;
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
                    if (firstValue == null) {
                        firstValue = msg.value;
                        System.out.println("Whisperer " + id + " received first message: " + msg.value);
                    } else {
                        if (msg.value % firstValue != 0) {
                            oos.writeObject(msg);
                            oos.flush();
                        }
                    }
                    //System.out.println("Whisperer " + id + " received message: " + msg);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    final static int N = 1000; // number of Whisperer
    final static int M = 7920; // number of messages
    public static void main(String[] args) {
        Whisperers4 s = new Whisperers4();
        try {
            PipedOutputStream first = new PipedOutputStream();
            Whisperer[] whisperers = new Whisperer[N];
            for (int i = 0; i < N; i++) {
                whisperers[i] = new Whisperer(i, (i>0)?whisperers[i-1].pos:first);
            }
            var lastPIS = new PipedInputStream(whisperers[N-1].pos);

            ObjectOutputStream firstOOS = new ObjectOutputStream(first);
            for (int i = 0; i < N; i++) {
                whisperers[i].connect();
                whisperers[i].start();
            }

            long start = System.currentTimeMillis();
            for (int m = 2; m < M; m++) {
                firstOOS.writeObject(new Message(m, m));
                firstOOS.flush();
            }
            var lastOIS = new ObjectInputStream(lastPIS);
            while(true) {
                System.out.printf("elapsed time: %d, result: %s\n",
                        System.currentTimeMillis ()-start,
                        (Message)lastOIS.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

