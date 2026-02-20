public class Gauss {

    public static long sucet(int a, int b) {
        return ((long)a+b)*((long)b-a+1)/2;
    }

    public static long sucet(int a, int b, int delta) {
        long count = 1+(b-a)/delta;
        //System.out.println(count);
        return (a+a+(count-1)*delta)*count/2;

    }
}
