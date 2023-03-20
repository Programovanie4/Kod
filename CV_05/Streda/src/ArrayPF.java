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
    E[] prvky;
    int[] priority;
    int pocetPrvkov;
    public ArrayPF(int size) {
        this.prvky = (E[]) new Object[size];
        this.priority = new int[size];
        pocetPrvkov = 0;
    }

    /**
     * zarad prvok elem s prioritou prio
     */
    public int indexPrePrvok(int prio){
        for(int i = pocetPrvkov-1;i>-1;i--){
            if(priority[i]<=prio){
                return i;
            }
        }
        return -1;
    }
    public void posunVpravo(int index){
        for(int i = pocetPrvkov;i>index+1;i--){
            prvky[i] = prvky[i-1];
            priority[i] = priority[i-1];
        }
    }

    public void posunVlavo(){
        for(int i = 0;i<pocetPrvkov-1;i++){
            prvky[i] = prvky[i+1];
            priority[i] = priority[i+1];
        }
    }
    @Override
    public void enqueue(E elem, int prio) {
        if(isFull()){
            return;
        }
        int nasIndex = indexPrePrvok(prio);
        posunVpravo(nasIndex);
        prvky[nasIndex+1]=elem;
        priority[nasIndex+1] = prio;
        pocetPrvkov++;
    }

    /**
     * vyber prvok s najmensou prioritou
     */
    @Override
    public E dequeue() {
        if(isEmpty()){
            return null;
        }
        E elem = prvky[0];
        posunVlavo();
        pocetPrvkov--;
        return elem;
    }

    /**
     * test, ci je front prazdny
     */
    @Override
    public boolean isEmpty() {
        //...
        return (pocetPrvkov==0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<pocetPrvkov;i++){
            sb.append(priority[i]);
            sb.append("-");
            sb.append(prvky[i]);
            sb.append("  ");
        }
        return sb.toString();
    }

    public boolean isFull(){
        return (pocetPrvkov==prvky.length);
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
