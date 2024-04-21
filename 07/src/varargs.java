import java.util.Arrays;
import java.util.Set;

public class varargs {
    public static void main(String[] args) {
        {
            var set = Set.of(new Integer[]{4}, new Integer[]{5});
            set.forEach(x -> System.out.println(Arrays.deepToString(x)));
        }
        {
            var set = Set.of(new Integer[]{4});
            set.forEach(System.out::println);
        }
        {
            Set<Integer> set = Set.of(new Integer[]{4});
            set.forEach(System.out::println);
        }
        {
            Set<Integer[]> set = Set.<Integer[]>of(new Integer[]{4});
            set.forEach(x -> System.out.println(Arrays.deepToString(x)));
        }
    }
}
