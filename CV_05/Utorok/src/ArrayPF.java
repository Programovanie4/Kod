/**
 * implementacia prioritneho frontu pomocu pola
 *
 * @param <E> idea, ktoru realizujte: konstruktor naalokuje pole velkosti size.
 *            Nejde to urobit takto E[] pole = new E[size], dovody su v prednaske
 *            Ide to urobit takto E[] pole = (E[])new Object[size]
 *            dequeue musi vratit prvok s najmensou prioritou, preto je najrozumnejsie, aby bole prvkov typu E bolo
 *            utriedene podla priorit, od najmensej po najvacsiu. Kedze prvky pribudaju a ubudaju, zistite z prednasky
 *            ako sa implementuje front v poli efektivne, aby ste ho stale neposuvali v poli. Stacia na to dva indexy, prvy
 *            a posledny, a rozmyslat, ze front moze odkracat cez hranu pola. Modulo size vas zahrani.
 *            Ak mate ploe utriedene, tak dequeue je trivialne, vyberie prvy prvok frontu.
 */
public class ArrayPF<E> implements FrontInterface<E> {

    /**
     * konstruktor
     */
    public ArrayPF(int size) {
        //...
    }

    /**
     * zarad prvok elem s prioritou prio
     */
    @Override
    public void enqueue(E elem, int prio) {
        // ...
    }

    /**
     * vyber prvok s najmensou prioritou
     */
    @Override
    public E dequeue() {
        // ...
        return null;
    }

    /**
     * test, ci je front prazdny
     */
    @Override
    public boolean isEmpty() {
        //...
        return false;
    }

    public static void main(String[] args) {
		FrontInterface<String> f = new ArrayPF<>(100);
        f.enqueue(new String("janka"), 5);
        f.enqueue(new String("danka"), 2);
        f.enqueue(new String("hanka"), 1);
        f.enqueue(new String("anka"), 4);
        f.enqueue(new String("zuzanka"), 3);
        f.enqueue(new String("elenka"), 1);
        f.enqueue(new String("zofka"), 6);
        f.enqueue(new String("evka"), 4);
        System.out.println(f);
        while (!f.isEmpty()) {
            System.out.println(f.dequeue());
        }
    }
}
