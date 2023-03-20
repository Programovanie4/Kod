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

    /**
     * zarad prvok elem s prioritou prio
     */
    @Override
    public void enqueue(E elem, int prio) {
        ListPfElem<E> prva = null;
        ListPfElem<E> druha = first;
        while(druha != null && druha.getPrior() < prio)
        {
            prva = druha;
            druha = druha.getNext();
        }
        ListPfElem<E> vklad = new ListPfElem<E>(prio,elem,druha,prva);
        if(prva != null){
            prva.setNext(vklad);
        }
        else{
            first = vklad;
        }

        if(druha != null)
            druha.setPrev(vklad);

    }

    /**
     * vyber prvok s najmensou prioritou
     */
    @Override
    public E dequeue() {
        ListPfElem<E> prvy = first;

        if(isEmpty())
            return null;

        first = first.getNext();

        if(first != null)
            first.setPrev(null);

        return prvy.getElement();
    }

    /**
     * test, ci je front prazdny
     */
    @Override
    public boolean isEmpty() {
        if(first == null)
            return true;
        else
            return false;
        //return first == null
    }

    public String toString(){
        StringBuilder stringbuilder = new StringBuilder();
        for(ListPfElem<E> pointer = first; pointer!= null; pointer = pointer.getNext()){
            stringbuilder.append(pointer.getPrior()).append("-").append(pointer.getElement()).append(" ");
        }
        return stringbuilder.toString();
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
