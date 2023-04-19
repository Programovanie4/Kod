import java.util.ArrayList;
import java.util.Comparator;

public class OpravMa4 {
    public static void main(String[] args) {
        final int MAX = 1_000_000;
        var l = new ArrayList<Integer>();

        var start = System.currentTimeMillis();
        for(var i = 0; i < MAX; i++) l.add(i);
        System.out.println(System.currentTimeMillis()-start);

        var start7 = System.currentTimeMillis();
        var a1 = l.stream().toList();
        System.out.println(System.currentTimeMillis()-start7);

        var start1 = System.currentTimeMillis();
        var a2 = l.stream().map(x -> x+1).toList();
        System.out.println(System.currentTimeMillis()-start1);

        var start3 = System.currentTimeMillis();
        var a3 = l.stream().map(x -> x+1).filter(x -> x < MAX/2).toList();
        System.out.println(System.currentTimeMillis()-start3);

        var start4 = System.currentTimeMillis();
        var a4  =l.stream().filter(x -> x < MAX/2).map(x -> x+1).toList();
        System.out.println(System.currentTimeMillis()-start4);

        var start2 = System.currentTimeMillis();
        l.sort(Comparator.reverseOrder());
        System.out.println(System.currentTimeMillis()-start2);
    }
}
