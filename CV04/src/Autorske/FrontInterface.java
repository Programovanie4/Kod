package Autorske;

interface FrontInterface {
        public void enqueue(String elem);               // pridá na koniec fronty
        public String dequeue();                        // vyberie prvý
        public boolean isEmpty();                       // je prázdna
}