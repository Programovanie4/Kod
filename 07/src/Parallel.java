import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Parallel {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++)
            lst.add(i);
        long time1 = System.currentTimeMillis();  System.out.println("create list \t"+(time1-start));
        //-------
        Stream<Integer> stream = lst.stream();
        Stream<Integer> stream1 = lst.stream();
        Stream<Integer> stream2 = lst.parallelStream();
        Stream<Integer> stream4 = lst.parallelStream();
        List<Integer> ls = stream4.toList();
        long time2 = System.currentTimeMillis(); System.out.println("Stream<->List \t"+(time2-time1));
        //-------

        var l1 = stream.map(n -> n + 2).map(n -> 5 * n).filter(n -> n > 0).count();
        long time3 = System.currentTimeMillis();  System.out.println("2xmap+filter \t"+(time3-time2));
        //-------

        var l2 = stream1.map(n -> 5 * (n + 2)).filter(n -> n > 0).count();
        long time4 = System.currentTimeMillis();     System.out.println("map, filter \t"+(time4-time3));
        //-------

        var l3 = stream2.map(n -> 5 * (n + 2)).filter(n -> n > 0).count();
        long time5 = System.currentTimeMillis();   System.out.println("parallel       \t"+(time5-time4));
        //-------

    }
}
