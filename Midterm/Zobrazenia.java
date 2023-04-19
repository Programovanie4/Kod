import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Zobrazenia<K,V> {

    public static <K,V> boolean jeInjektivne(Map<K,V> z) {
       var x = z.keySet().stream().map(z::get).toList();
       return x.size() == new HashSet<>(x).size();
    }
    public static <K,V> boolean jeSurjektivne(Map<K,V> z) {
        return true;
    }

    public static <K,V,W> Map<K,W> kompozicia(Map<K,V> z1, Map<V,W> z2) {
        Map<K,W> res = new HashMap<>();
        z1.forEach((k,v)->res.put(k,z2.get(v))
        );
        return res;
    }
    public static <K,V> Map<V,K> inverzne(Map<K,V> z) {
        if (jeInjektivne(z)) {
            Map<V, K> res = new HashMap<>();
            z.forEach((k, v) -> res.put(v, k));
            return res;
        } else return null;
    }
    public static void main(String[] args) {
        var z1 = Map.of(1,2,  2,3,   3,4,  4,1);
        var z2 = Map.of(1,"a",  2,"b",   3,"c",  4,"d", 5 , "a");
        System.out.println(jeInjektivne(z1));
        System.out.println(jeInjektivne(z2));
        System.out.println(jeSurjektivne(z2));
        System.out.println(kompozicia(z1,z2));
        System.out.println(inverzne(kompozicia(z1,z2)));
        System.out.println(inverzne(z1));
        System.out.println(inverzne(z2));
    }
}
