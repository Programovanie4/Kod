import java.util.ArrayList;
import java.util.List;

public class QueueUsingCompositionZLE<E> {
    public List<E> q = new ArrayList<>();
    //         List<E> q = new ArrayList<>();
    //protected List<E> q = new ArrayList<>();
    //private List<E> q = new ArrayList<>();
    public void enqueue(E e) {}
    public E dequeue() { return null; }
}

class Zloduch<E> extends QueueUsingCompositionZLE<E> {
    public static void main(String[] args) {
        QueueUsingCompositionZLE queue = new QueueUsingCompositionZLE<String>();
        queue.enqueue("Jano");
        queue.q.remove(17);
    }
}