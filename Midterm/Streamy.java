import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.stream.Stream;

public class Streamy {
    public static LongFunction<Long> cifSucet = n ->
            Stream.iterate(n, x->x!=0, x->x/10)
                    .map(x -> x%10)
                    .reduce(0L, Long::sum);
    public static LongFunction<Long> cifRozdiel = n ->
            Stream.iterate(n, x->x!=0, x->x/10)
                  .map(x -> x%10)
                  .reduce(0L, (acc,x) -> x-acc);

    public static LongPredicate moreDigits = n -> n >= 10 || n <= -10;
    public static LongPredicate div3 = n -> n == 0 || n == 3 || n == 6 || n == 9;
    public static LongPredicate div9 = n -> n == 0 || n == 9;
    public static LongPredicate div11 = n -> n == 0;

    public static LongFunction<Long> iterate(LongFunction<Long> f) {
        return n -> f.apply(Stream.iterate(n, moreDigits::test, f::apply)
                     .reduce(n,(first, second) -> second));
    }

    public static void main(String[] args) {
        System.out.println(cifRozdiel.apply(-10));
        System.out.println(cifRozdiel.apply(123));

        System.out.println(cifRozdiel.apply(1234));
        System.out.println(cifRozdiel.apply(12345));
        System.out.println(cifRozdiel.apply(9183979497L));
    }
}
