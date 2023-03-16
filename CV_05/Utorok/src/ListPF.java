/**
 * implementacia prioritneho frontu spajaneho zoznamu s hodntami typu E
 * v tomto pripade je spajany zoznam prvkov typu E, pricom je utriedeny opat podla rastucich priorit
 * najnizsia priorita je na zaciatku
 * dequeue je lahle, pri enqueue treba sikovne najst miesto, kam treba prvok vsunut
 */

public class ListPF<E> implements FrontInterface<E> {
    ListPfElem<E> first;

    /**
     * konstruktor
     */
    public ListPF() {
    }
/*
    private ListPfElem<E> createElement(int priority, E element, ListPfElem<E> next, ListPfElem<E> prev) {
        ListPfElem<E> node = new ListPfElem<>(priority, element, next, prev);
        if (prev == null) {
            first = node;
        } else {
            prev.next = node;
        }
        return node;
    }*/

    @Override
    public void enqueue(E elem, int prio) {
        if (isEmpty()){
            first = new ListPfElem<E>(prio, elem, null, null);
            return;
        }

        ListPfElem<E> last = null;
        ListPfElem<E> current = first;
        while (current != null && current.prior < prio){
            last = current;
            current = current.next;
        }

        if (current == null) {
            ListPfElem<E> node = new ListPfElem<>(prio, elem, null, last);
            if (last == null) {
                first = node;
            } else {
                last.next = node;
            }

        } else {
            ListPfElem<E> node = new ListPfElem<>(prio, elem, current, last);
            current.prev = node;
            if (last == null)  {
                first = node;
            } else {
                last.next = node;
            }
        }


    }

    /**
     * vyber prvok s najmensou prioritou
     */
    @Override
    public E dequeue() {
        if(first == null) return  null;
        ListPfElem<E> minimum = first;
        first = minimum.next;
        if(first != null) first.prev = null;
        return minimum.element;
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        ListPfElem<E> current = first;
        while (current != null){
            s.append("(" + current.element + ";" + current.prior + ")");
            current = current.next;
        }
        return s.toString();
    }


    /**
     * test, ci je front prazdny
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        FrontInterface<String> f = new ListPF<>();
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

    /**
     * toto je trieda, ktoru mozete pouzit na spajany zoznam
     *
     * @param <E>
     */
    public static class ListPfElem<E> {
        private int prior;        // priorita prvku
        private E element;        // hodnota prvku
        private ListPfElem<E> next;     // link na nasledujuci prvok
        private ListPfElem<E> prev;     // link na predchadzajuci prvok

        /**
         * konstruktor krabice
         */
        public ListPfElem(int prior, E element, ListPfElem<E> next, ListPfElem<E> prev) {
            this.element = element;
            this.prior = prior;
            this.next = next;
            this.prev = prev;
        }

        /**
         * gettery
         */
        public E getElement() {
            return element;
        }

        public int getPrior() {
            return prior;
        }

        public ListPfElem<E> getNext() {
            return next;
        }

        public ListPfElem<E> getPrev() {
            return prev;
        }

        /**
         * settery
         */
        public void setNext(ListPfElem<E> new_next) {
            next = new_next;
        }

        public void setPrev(ListPfElem<E> new_prev) {
            prev = new_prev;
        }
    }
}
