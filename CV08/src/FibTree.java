import java.io.*;

public class FibTree {

    public static <E> E load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (E)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("nieco zle sa stalo pri citani suboru " + fileName);
            e.printStackTrace();
            return null;
        }
    }
    // mozno sa za zide...
    public static <E> void save(String fileName, E t) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(t);
        } catch (IOException e) {
            System.err.println("nieco zle nastalo pri otvarani suboru " + fileName +
                    ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static long sumr(Node t) {
        if (t == null) return 0;
        return t.value +
                ((t.left!=null)?sumr(t.left):0) +
                ((t.right!=null)?sumr(t.right):0);
    }
    public static long sum(String fileName) {
        return sumr((Node)load(fileName));
    }
    public static Node next(String fileName) {
        Node t = (Node)load(fileName);
        int level = isFibTree(t);
        return new Node(level+1);
    }
    public static int isFibTree(Node t) {
        if (t == null) return -1;
        if (t.value == 0 && t.left == null && t.right == null)
            return 0;
        if (t.value == 1 && t.left == null && t.right == null)
            return 1;
        if (t.left != null && t.right != null) {
            int l = isFibTree(t.left);
            int r = isFibTree(t.right);
            return (l >= 0 && r >= 0 && l+1 == r)?r+1:-1;
        }
        return -1;
    }
    public static void main(String[] args) {
        Node t = (Node)load("du8.bin");
        System.out.println(t.value);
        for(int i = 0; i< 30; i++) {
            System.out.println(i + " -> " + new Node(i).value);
        }

    }
}
