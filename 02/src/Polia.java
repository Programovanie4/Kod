import java.util.Arrays;

public class Polia {
    public static void main(String[] args) {
        String[] p = new String[] {"Jana", "Anna", "Mama"};
        System.out.println(Arrays.binarySearch(p,"Jana"));
        Arrays.sort(p);
        System.out.println(Arrays.binarySearch(p,"Jana"));
        for(String s : p)
            System.out.println(s);
        System.out.println(Arrays.binarySearch(p, "baa"));
        System.out.println(Arrays.toString(p));
        Arrays.fill(p,"cc");
        String[] r = Arrays.copyOf(p,3);
        p[0] = "zmena";
        System.out.println(r[0]);
        System.out.println(p[0]);
    }
}
