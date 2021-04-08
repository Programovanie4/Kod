import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Basics {

    public static void main(String[] args) {
        //System.out.println(IntStream.range(0,100).boxed().collect(Collectors.toList()));
        //IntStream.range(0,100).forEach(System.out::print);
        //IntStream.range(0,100).forEach(n -> System.out.print(n));
//        IntStream.range(0,100).map(x -> 2*x).forEach(n -> System.out.print(n));

        Predicate<Integer> jeStvorec = n -> Math.pow(Math.floor(Math.sqrt(n)),2) == n;
        //IntPredicate jeStvorec1 = n -> Math.pow(Math.floor(Math.sqrt(n)),2) == n;

        //IntStream.range(0,100).filter(x -> jeStvorec1.test(x)).forEach(n -> System.out.println(n));

        //IntStream.range(0,100).filter(n -> List.of(2,3,4,5,6,7,8,9).contains(n%10)).forEach(System.out::println);

        //IntStream.range(0,100).filter(n -> IntStream.range(2,10).anyMatch(d -> n%10 == d) )
        //        .forEach(System.out::println);

        //Predicate<Integer> jePrvocislo = n -> n > 1 && IntStream.range(2, n).allMatch(d -> n%d > 0);
        //Predicate<Integer> jePrvocislo = n -> n > 1 && IntStream.range(2, 1+(n/2)).allMatch(d -> n%d > 0);
       // Predicate<Integer> jePrvocislo = n -> n > 1 && IntStream.range(2, 1+(int)Math.floor(Math.sqrt(n))).allMatch(d -> n%d > 0);
       // IntStream.range(0,100).filter(n -> jePrvocislo.test(n) ).forEach(System.out::println);

        //System.out.println(IntStream.range(0,100+1).sum());

        //System.out.println(IntStream.range(0,100+1).reduce(0, Integer::sum));
        //System.out.println(IntStream.range(0,100+1).reduce(0, (a,b) -> a+b));

        //System.out.println(IntStream.range(1,10+1).reduce(1, (a,b) -> a*b));

        IntPredicate jePrepona = c -> IntStream.range(0,c).anyMatch(a -> IntStream.range(0,c).anyMatch(b-> a*a+b*b==c*c));
        //https://www.zborovna.sk/kniznica.php?action=show_version&id=283798
        IntStream.range(0,100+1).filter(jePrepona::test).forEach(System.out::println);

        // w rovnako 0 a 1
        Predicate<String> rovnako = str ->
                str.chars().reduce(0, (a,b) -> (b == (int)'0')?a+1:a-1) == 0;

        System.out.println(rovnako.test("1010"));
        System.out.println(rovnako.test("1100"));
        System.out.println(rovnako.test("11001"));
        System.out.println(rovnako.test("100"));

//        System.out.println(" Delitelne");
//        delitele23456789(IntStream.range(1, 10000))  // vyrobim si stream cisel 1..9999, poslem ho do funkcie delitelne
//                .forEach(System.out::println);       // s kazdym prvkom vo vysledom streame spravim System.out.println(prvok)
//        // prve je okolo 2500, potom okolo 5000
//
//        System.out.println("\n Dokonale");
//        dokonale(IntStream.range(1, 1000))
//                .forEach(System.out::println);
//
//        System.out.println("\n Prepona");
//        prepona(IntStream.range(1, 30))
//                .forEach(System.out::println);
//        // 5, 10, 13, 15, 17, ...
//
//        System.out.println("\n Dobre uzatvorkovane");
//        dobreUzatvorkovane(Stream.of("()()()", "", "((()))()", "())(()", "()(()", "()())"))
//                .forEach(System.out::println);  // maju sa vypisat iba dobre uzatvorkovane vyrazy, teda prve tri
//
//        System.out.println("\n Abeceda stream");
//        List<String> words = List.of("ahoj", "hello", "aloha", "bye bye", "bon jour", "zdrastvuj", "dobry den", "nazdar", "guten tag", "good morning");
//        abecedaStream(words).forEach(s -> System.out.println(s.collect(Collectors.toList())));
//
//        System.out.println("\n Abeceda mapa");
//        abecedaMap(words).forEach((k, v) -> System.out.println(k + ": " + v.collect(Collectors.toList())));
    }


    public static IntStream delitele23456789(IntStream input) {
        return null; // TODO
    }

    public static IntStream dokonale(IntStream input) {
        IntPredicate dokonale = null; // TODO
        return input.filter(dokonale);
    }

    public static IntStream prepona(IntStream input) {
        IntPredicate jeStvorec = null; // TODO
        // System.out.println("Je 144 stovrec? Malo by byt true, je " + jeStvorec.test(144));
        // System.out.println("Je 97 stovrec? Malo by byt false, je " + jeStvorec.test(97));

        IntPredicate prepona = null; // TODO
        // System.out.println("Je 13 prepona? Malo by byt true, je " + prepona.test(13));
        // System.out.println("Je 9 prepona? Malo by byt false, je " + prepona.test(9));

        return input.filter(prepona);
    }

    public static Stream<String> dobreUzatvorkovane(Stream<String> input) {
        Predicate<String> jeOK = str -> str.chars().boxed()
                .reduce(0, null /* TODO */  ) == 0;
        return input.filter(jeOK);
    }

    public static Stream<Stream<String>> abecedaStream(List<String> words) {
        Stream<String> abeceda = null; // TODO
        // Ak je nasledujuci riadok aktivny, tak pri dalsej praci s 'abeceda' vam to padne.
        // Ak ste teda dobre vygenerovali abecedu, zakomentujte/zmazte tento vypis.
        // System.out.println(abeceda.collect(Collectors.toList()));

        return abeceda.map(null /* TODO */);
    }

    public static Map<String, Stream<String>> abecedaMap(List<String> words) {
        Stream<String> abeceda = null; // TODO
        return abeceda.collect(Collectors.toMap(null, null /* TODO */));
    }
}
