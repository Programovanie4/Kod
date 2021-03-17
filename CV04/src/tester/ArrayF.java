package tester;

import java.util.Arrays;

interface FrontInterface {
    public void enqueue(String elem); // pridá na koniec fronty
    public String dequeue();          // vyberie prvý
    public boolean isEmpty();         // je prázdna
}

public class ArrayF implements FrontInterface {

    String[] elems;
    int tip;

    public ArrayF(int max) {
        elems = new String[max];
    }

    @Override
    public void enqueue(String elem) {
        if (isEmpty()) {
            elems[0] = elem;
        } else if (tip < elems.length){
            tip++;
            elems[tip] = elem;
        }
    }

    @Override
    public String dequeue() {
        String out = elems[0];
        if (isEmpty()) {
            return "Autorske.ArrayF is empty";
        }
        for (int i = 0; i < tip+1; i++) {
            elems[i] = elems[i+1];
        }
        if (tip > 0)
            tip--;
        return out;
    }

    @Override
    public boolean isEmpty() {
        return tip == 0 && elems[0] == null;
    }

    public static void main(String[] args) {
        ArrayF f = new ArrayF(100);
        f.enqueue(new String("janka"));
        f.enqueue(new String("danka"));
        f.enqueue(new String("hanka"));
        f.enqueue(new String("anka"));
        f.enqueue(new String("zuzanka"));
        f.enqueue(new String("elenka"));
        f.enqueue(new String("zofka"));
        f.enqueue(new String("evka"));
        System.out.println(Arrays.toString(f.elems));
        while (!f.isEmpty()) System.out.print(f.dequeue()+", ");
    }
}
