import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Basics {

    // vyfiltruje prestupne roky
    public static IntStream prestupnyRok(IntStream input) {
        IntPredicate  prestupny = n -> (n % 4 == 0 && n % 100 !=0) || (n % 400 == 0);
        return input.filter(n -> prestupny.test(n));
        //return input.filter(prestupny::test);
    }

    /*
    1
    2 2
    3 3 3
    4 4 4 4
     */
    public static IntStream trojuholnik(int n) {
        return IntStream.range(1,n+1).flatMap(i -> IntStream.range(0,i).map(x -> i));
    }

    /*
    vytvori stream, ktory obsahuje "stvorec"
    n,n,n,n,n
    ...
    2,2,2,2,2
    1,1,1,1,1
     */
    public static IntStream stvorec(int n) {
        return IntStream.range(1,n+1).flatMap(i -> IntStream.range(0,n).map(x -> n-i+1));
    }

    /*
    pocita n-tu mocninu funckie, podobne ako na prednaske
    objavime iterate, limit
     */
    public static <T> Function<T,T> iter(int n, Function<T,T> f) {
        return x -> Stream.iterate(x, y -> f.apply(y)).limit(n+1).toList().get(n);
    }

    // premapuje stream na stream cifernych suctov cisla
    public static IntStream cifernySucet(IntStream input) {
        Function<Integer, Integer> cifSum = n -> Stream.iterate(n, m -> m/10).limit(10).mapToInt(m -> m % 10).sum();
        return input.map(cifSum::apply);
    }

    // vyfiltruje len tie, co obsahuju dve nuly za sebou v dekadickom zapise cisla
    public static IntStream dveNulyVedlaSeba(IntStream input) {
        IntPredicate dveNuly = n -> Stream.iterate(n, m->m/10).takeWhile(x->x>0).map(m -> m % 100).anyMatch(x -> x==0);
        return input.filter(dveNuly::test);
    }

    // spocita rozdiel parne a neparne stojacich cifier
    public static IntStream rozdielParnychNeparnych(IntStream input) {
        Function<Integer, Integer> cifRozdiel = n -> Stream.iterate(n, x -> x>0, m -> -m/10).mapToInt(m -> m % 10).sum();
        return input.map(cifRozdiel::apply);
    }

    public static Integer sucetDelitelov(int n) {
        return IntStream.range(1,n).filter(d -> n % d == 0).sum();
    }



    public static List<Integer> delitele(int n) {
        return IntStream.range(2,n).filter(d -> n % d == 0).boxed().toList();
        //return IntStream.range(1,n).boxed().filter(d -> n % d == 0).toList();
    }

    public static Map<Integer, Integer> pocetDelitelov(List<Integer> vstup) {
        return vstup.stream().filter(x -> delitele(x).size() > 0)
                .collect(Collectors.toMap(x->x, x -> delitele(x).size()));
    }

    public static Map<Integer, List<Integer>> zoznamDelitelov(List<Integer> vstup) {
        return vstup.stream().filter(x -> delitele(x).size() > 0)
                .collect(Collectors.toMap(x->x, x -> delitele(x)));
    }

    public static void main(String[] args) {
        //prestupnyRok(IntStream.range(1900,2100)).forEach(System.out::println);
        //cifernySucet(IntStream.range(0,30)).forEach(System.out::println);
        //rozdielParnychNeparnych(IntStream.range(11,30)).forEach(System.out::println);
        //System.out.println(stvorec(4).boxed().toList());
        //System.out.println(trojuholnik(4).boxed().toList());
        Function<Integer,Integer> f = x -> x*2;
        System.out.println(iter(5, f).apply(10));
        System.out.println(
                dveNulyVedlaSeba(Stream.of(123, 3001, 201).mapToInt(e -> e)).boxed().toList()
        );
        System.out.println(delitele(24));
        System.out.println(pocetDelitelov(IntStream.range(0,100).boxed().toList() ));
        System.out.println(zoznamDelitelov(IntStream.range(0,100).boxed().toList() ));

        System.out.println(IntStream.range(0,10).boxed().collect(
                Collectors.toMap(x->x, y->List.of(y-1, y, y+1))));

        System.out.println(IntStream.range(0,10).boxed().collect(
                Collectors.toMap(x->x, y->IntStream.range(0,y).boxed().toList())));
        IntPredicate dokonale = n -> sucetDelitelov(n) == n;
        System.out.println(IntStream.range(1,10000).filter(dokonale::test).boxed().toList());
        //https://en.wikipedia.org/wiki/Amicable_numbers
        IntPredicate spriatelene = n -> sucetDelitelov(sucetDelitelov(n)) == n && sucetDelitelov(n) != n;
        System.out.println(IntStream.range(1,10000).filter(spriatelene::test).boxed().toList());
    }
}
