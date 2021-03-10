import java.util.ArrayList;

public class QueueUsingInheritance<E>
        extends ArrayList<E> {
    public void enqueue(E e) {}
    public E dequeue() { return null; }
}


class Zloduch1<E> extends QueueUsingInheritance<E> {
    public static void main(String[] args) {
        QueueUsingInheritance queue = new QueueUsingInheritance<String>();
        queue.enqueue("Jano");
        queue.remove(17);
    }
}