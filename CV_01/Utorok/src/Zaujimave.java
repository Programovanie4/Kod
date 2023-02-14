public class Zaujimave {

    public static int pocetZaujimavychInt(int n) {
        return
                (n == Integer.MAX_VALUE)?(n/10):
                (n+1) / 10;
    }

    public static long pocetZaujimavychLong(long n) {
        return -1;
    }

    public static long pocetBinarneZaujimavychLong(long n) {
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE % 4);
//        System.out.println(pocetZaujimavychInt(19));
//        System.out.println(pocetZaujimavychInt(Integer.MAX_VALUE));
    }
}