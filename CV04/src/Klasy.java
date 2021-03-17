public class Klasy {
    int a = 0;
    static int sa = 0;

    class Vnorena {
        int b = 0;

    }
    public static void main(String[] args) {
        Klasy ksy = new Klasy();
        int x = ksy.a + ksy.sa + Klasy.sa;
        Vnorena v = new Klasy().new Vnorena();
        int z = v.b + ksy.a;
    }
}
class Druha {

}
final class Trieda {

}
