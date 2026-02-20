public class Zaujimave {

    private static int s(long n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static int pocetZaujimavychInt(int n) {
        return (n == Integer.MAX_VALUE)?(n/10):(n+1) / 10;
    }
    public static long pocetZaujimavychLong(long n) {
        return (n == Long.MAX_VALUE)?(n/10):(n+1) / 10;
    }
    public static long pocetBinarneZaujimavychLong(long n) {
        return (n == Long.MAX_VALUE)?(n/4+1):(n+1) / 4;
        // lebo Long.MAX_VALUE % 4 == 3, tak by sa mu pocitalo o 1 menej, ak by sme pouzili n/4
    }
    public static void main(String[] args) {
        for (int i = 1; i <= 2021; i++) {
            if (s(i+1) < s(i)) {
                System.out.println(i);
            }
        }
    }
}
