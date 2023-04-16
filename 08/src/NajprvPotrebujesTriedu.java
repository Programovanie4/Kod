import java.util.*;
import java.util.stream.IntStream;

public class NajprvPotrebujesTriedu {
    static int x = 0;
    public static int jednotkovaFunkcia(int n) {
        System.out.println("1. " + n);
        int c = 0;
        while(n > 0) {
            if (n%2 == 1) c++;
            n = n/2;
        }
        return c;
    }
    public static int vynimocnaFunkcia(int n) {
        if (n < 0)
            throw new RuntimeException();
        return n;
    }
    public static List<Integer> inaFunkcia() {
        List<Integer> r = new ArrayList<>();
        r.add(x++);
        return r;
    }
    public static int rozdelenaFunkcia(int a, int b) {
        if (b == 0) return a;
        return rozdelenaFunkcia(b, a%b);
    }
    public static int vzdialenaFunkcia(String a, String b) {
        //System.out.println("5. " + a + " " + b);
        if (a.isEmpty()) return b.length();
        if (b.isEmpty()) return a.length();
        if (a.charAt(0) == b.charAt(0)) return vzdialenaFunkcia(a.substring(1), b.substring(1));
        return 1 + IntStream.of(vzdialenaFunkcia(a.substring(1), b), vzdialenaFunkcia(a, b.substring(1)), vzdialenaFunkcia(a.substring(1), b.substring(1))).min().orElse(0);
    }
    public static String prvyApril() {
        System.out.println("6. ");
        return "prvy april";
    }

    public static void main(String[] args) {
        System.out.println(vzdialenaFunkcia("r", "radsej aj trochu pogoogli :)..."));
    }
}
