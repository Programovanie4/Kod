package Verejne;

public class Triedy {
    int a = 0;
    static int sa = 1;

    class Vnorena {
        int b = 0;
        //static int sb = 1;

    }
    class SVnorena {
        int c = 0;
    }

    public static void main(String[] args) {
        Triedy t = new Triedy();
        int x = t.sa + Triedy.sa;
        Vnorena vn = new Triedy().new Vnorena();
        int y = vn.b;
        SVnorena svn = new Triedy().new SVnorena();
        int z = svn.c;

    }
}
class Pomocna {

}
final class DruhaPomocna {

}
