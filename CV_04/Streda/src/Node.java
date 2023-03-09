import java.util.Objects;
import java.util.function.BiFunction;

public class Node extends Tree {
    Tree left, right;

    /**
     * konstruktor
     */
    public Node(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    /**
     * je maximálna hodnota stromu, null ak je strom prazdny
     */
    @Override
    Integer max() {

        Integer leftMax = left == null ? null : left.max();
        Integer rightMax = right == null ? null : right.max();

        return Integers.maxOf(leftMax, rightMax);
    }

    Integer min() {

        Integer leftMin = left == null ? null : left.min();
        Integer rightMin = right == null ? null : right.min();

        return Integers.minOf(leftMin, rightMin);
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        int depthLeft = left == null ? 0 : left.depth();
        int depthRight = right == null ? 0 : right.depth();

        return Math.max(depthLeft, depthRight) + 1;
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        if (left == null && right == null) {
            return true;
        }

        if (left == null) {
            return right.isUnique();
        }

        if (right == null) {
            return left.isUnique();
        }

        return left.isUnique() && right.isUnique() &&
                Objects.equals(left.max(), right.max());
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        if (left == null && right == null) {
            return true;
        }

        if (left == null) {
            return right.isOrdered();
        }

        if (right == null) {
            return left.isOrdered();
        }

        return left.isOrdered() && right.isOrdered()
                && isLessEqual(left.max(), right.min());
    }

    boolean isLessEqual(Integer first, Integer second) {
        if (first == null && right == null) {
            return true;
        }

        if (first == null) {
            return true;
        }

        if (second == null) {
            return true;
        }

        return first <= second;
    }

    @Override
    public String toString() {
        return "(" + left + " " + right + ")";
    }
}
