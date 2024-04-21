import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Hadanka {
    public static void main(String[] args) {
        Set<Integer> set = Set.of(1);
        Set<Integer> set2 = Set.of(new Integer[] {2});

        // Nefunguje
//        try {
//            var set33 = (Set<Integer[]>)Set.of(new Integer[] { 4 }); // nezmysel v runtime
//            //incompatible types: java.util.Set<java.lang.Integer> cannot be converted to java.util.Set<java.lang.Integer[]>
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        Set<Integer[]> set3 = Set.of(new Integer[] { 4 }, new Integer[] { 4 });
        set3.forEach(n ->System.out.println(Arrays.deepToString(n)));

        Set<Integer[]> set31 = Set.<Integer[]>of(new Integer[] { 5 });
        set31.forEach(n ->System.out.println(Arrays.deepToString(n)));

        // Ideal, ale asi nie je mozne
        // Set<Integer[]> set4 = Set<Integer[]>.of(new Integer[] {5});

        // Workaround 1
        Set<Integer[]> set5 = Set.of(new Integer[][] {new Integer[] {6}});
        set5.forEach(n ->System.out.println(Arrays.deepToString(n)));

        // Workaround 2
        Integer[] value = {6};
        Set<Integer[]> set6 = Set.of(value, value);
        set6.forEach(n ->System.out.println(Arrays.deepToString(n)));
    }
}
