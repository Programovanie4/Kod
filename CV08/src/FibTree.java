import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FibTree {

    public static <E> E load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (E) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public static void main(String[] args) {
        Node n = load("du8.bin");
        System.out.println(n.value);
        for(int i = 0; i <30; i++)
            System.out.println(i + " -> " + new Node(i).value);
    }

}
