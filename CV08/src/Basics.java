import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Basics {

    public static IntStream delitele23456789(IntStream input) {
        //return input.filter(i -> i % 8 == 0 && i % 9 == 0 && i % 6 == 0 && i % 7 == 0 && i % 5 == 0);
        //return input.filter(n -> IntStream.range(2, 10).anyMatch(d -> n%d == 0));
        return input.filter(n -> IntStream.range(2, 10).allMatch(d -> n%d == 0));
    }

    public static IntStream dokonale(IntStream input) {
//        IntPredicate dokonale = n -> IntStream.range(1,n).filter(i -> n % i == 0).sum() == n;
//        return input.filter(dokonale);

        IntPredicate dokonale = cislo -> IntStream.range(1,cislo)
                .filter(delitel -> cislo % delitel == 0)
                .reduce(0, Integer::sum) == cislo;
        return input.filter(dokonale);

    }

    public static IntStream prepona(IntStream input) {
        IntPredicate jeStvorec = n -> Math.sqrt(n) - Math.floor(Math.sqrt(n)) == 0;
        IntPredicate prepona = c -> IntStream.range(1,c).anyMatch(a -> jeStvorec.test(c*c-a*a));
        return input.filter(prepona);
    }

    public static Stream<String> dobreUzatvorkovane(Stream<String> input) {
        Predicate<String> jeOK = stri ->
                stri.chars().boxed().reduce(0, (a, b) ->
                        (((((char)(int)b == '(')?(a+1):(a-1)) < 0)?Integer.MIN_VALUE:
                                (((char)(int)b == '(')?a+1:a-1))) == 0;
        return input.filter(jeOK);
    }

    public static Stream<Stream<String>> abecedaStream(List<String> words){
        Stream<String> abeceda = Stream.of(
                "a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z");
        return abeceda.map(pismeno -> words.stream().filter(x -> x.startsWith(pismeno)));
    }
    public static Map<String, Stream<String>> abecedaMap(List<String> words){
        List<String> abeceda = List.of(
                "a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u",
                "v", "w", "x", "y", "z");
        Map<String, Stream<String>> res = new TreeMap<String, Stream<String>>();
        for (String key : abeceda)  /// kapitulacia :)
            res.put(key, words.stream().filter(s -> s.startsWith(key)));
        return res;
    }
    public static void main(String[] args) {
        for (int m = 0; m <Integer.MAX_VALUE; m++) {
            IntPredicate jeStvorec1 = n -> Math.sqrt(n) - Math.floor(Math.sqrt(n)) == 0;
            IntPredicate jeStvorec = n -> Math.abs(Math.floor(Math.sqrt(n)) - Math.sqrt(n)) < 0.000001;
            if (jeStvorec.test(m) != jeStvorec1.test(m))
                System.out.println("kontrepriklad " + m);
        }
        System.out.println(
                delitele23456789(IntStream.range(0,10000)).boxed().collect(Collectors.toList())
        );
        System.out.println(
                delitele23456789(
                        Stream
                                .iterate(0, i->i+4)
                                .limit(10000)
                                .mapToInt(e -> (int)e)
                ).boxed().collect(Collectors.toList())
        );
        System.out.println(
                dokonale(IntStream.range(0,10000)).boxed().collect(Collectors.toList())
        );
        System.out.println(
                prepona(IntStream.range(0,10000)).boxed().collect(Collectors.toList())
        );
        System.out.println(
                dobreUzatvorkovane(Stream.of("()", ")(", "(()")).collect(Collectors.toList())
        );

    }
}
