import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

// data class
record  Pair<T> (T first, T second) { }

// data class, inteface, generics
sealed interface Tree<T> permits Node, Leaf {
    List<T> preorder();
}

record Node<T>(Tree<T> left, Tree<T> right)  implements Tree<T> {
    public List<T> preorder() {
        var res = new ArrayList<T>();
        if (left != null) res.addAll(left.preorder());
        if (right != null) res.addAll(right.preorder());
        return res;
    }
}

record Leaf<T>(T value) implements Tree<T> {
    public List<T> preorder() { return List.of(value); }
}
//---------------
@SuppressWarnings("unchecked")
public class J17 {

    public static <T,U> Tree<U> map(Tree<T> t, Function<T, U> f) {
        return switch (t) {
            case null -> null;
            case Leaf v ->   // pattern matching
                    new Leaf<>(f.apply((T) v.value()));
            case Node n -> new Node<U>(
                    (n.left() != null) ? map(n.left(), f) : null,
                    (n.right() != null) ? map(n.right(), f) : null);
        };
    }
    static Random rnd = new Random();
    public static Tree<Integer> gener(int n) {
        if (n > 1) {
            var i = rnd.nextInt(n-1)+1;
            return new Node<>(gener(n-i), gener(i));
        } else return new Leaf<>(new Random().nextInt(2022));
    }

    public static Integer kolacz(Integer n) {
        return (n > 1)? (((n & 1) > 0)?kolacz((n<<1) + n+1):kolacz(n>>1)):1;
    }
    public static <T> List<T> postorder(Tree<T> t) {
        switch (t) {
            case null:
                return List.of();
            case Leaf v:   // pattern matching
                return List.of((T) v.value());
            case Node n:
                var res = new ArrayList<T>();
                if (n.right() != null) res.addAll(postorder(n.right()));
                if (n.left() != null) res.addAll(postorder(n.left()));
                return res;
            default:
                throw new RuntimeException("""
                        nieco zle sa stalo
                        """);
        }
    }

    public static <T> int size(Tree<T> t) {
        return switch (t) {
            case null -> 0;
            case Leaf v  ->   // pattern matching
                    0;
            case Node n -> 1 + size(n.left()) + size(n.right());
        };
    }
    public static int uhadniNajvacsie() { return 2021; }

    public static int uhadniSucet() { return 1+2+3+2021+5+8+7+9; }/* TODO */

    public static void main(String[] args) {
        Pair<Integer> p = new Pair<>(1,2);
        System.out.println(p);
        var t1 = new Node<String>(new Leaf<>("Janko"), new Leaf<>("Marienka"));
        var t2 = new Node<String>(new Leaf<>("Othello"), new Leaf<>("Desdemona"));
        var t = new Node<String>(t1,t2);
        System.out.println(t);
        System.out.println(t.preorder());
        System.out.println(postorder(t));
        System.out.println(size(t));
        System.out.println(size(map(gener(2022), J17::kolacz)));
        System.out.println(map(t,String::length).preorder());
    }
}
