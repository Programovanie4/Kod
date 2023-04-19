import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public record Node<E>(E value, List<Node<E>> sons) {

    @Override
    public String toString() {
        return (sons.size() == 0)?value.toString():
                "(" + value +  ("," +sons.stream().map(Node::toString).toList()) + ")";
    }
    public Node<E> removeNulls() {
        return new Node<>(value, (sons==null)?List.of():sons.stream().map(Node::removeNulls).toList());
    }

    public int width() {
        Map<Integer, Integer> hm = new HashMap<>();
        width(0, hm);
        return hm.values().stream().max(Comparator.naturalOrder()).orElse(0);
    }
    private <E> void width(int d, Map<Integer, Integer> hm) {
        hm.computeIfAbsent(d, x->0);
        hm.computeIfPresent(d, (c,x) -> x+1);
        sons.forEach( s -> s.width(d+1, hm) );
    }

    public <T> Node<T> map(Function<E,T> f) {
        return
                new Node<>(f.apply(value), sons.stream().map(s -> s.map(f)).toList());
    }
    public Node<E> filter(Predicate<E> p) {
        if (p.test(value))
            return new Node<>(value, sons.stream().map(s -> s.filter(p))
                    .filter(Objects::nonNull)
                    .toList());
        else
            return null;
    }


    public static void main(String[] args) {
        Node<Integer> root =
                new Node<>(5, List.of(
                    new Node<>(4, List.of(
                        new Node<>(1, List.of(
                            new Node<>(4, List.of()),
                            new Node<>(5, null),
                            new Node<>(6, List.of()))))),
                    new Node<>(7, List.of(
                        new Node<>(11, null)))));

        System.out.println(root.removeNulls());
        System.out.println(root.removeNulls().map("*"::repeat));
        System.out.println(root.removeNulls().map(x -> 2*x));
        System.out.println(root.removeNulls().filter(x -> x < 5));

        System.out.println(root.removeNulls().filter(x -> x % 2 > 0));
        System.out.println(root.removeNulls().filter(x -> x % 2 == 0));

        System.out.println(root.removeNulls().width());
    }
}
