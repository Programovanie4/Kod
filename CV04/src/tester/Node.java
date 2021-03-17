package tester;

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
     *  je maximálna hodnota stromu, null ak je strom prazdny
     */
    @Override
    Integer max() {
        if (left == null && right == null) return null;
        else if (left == null) return right.max();
        else if (right == null) return left.max();
        if (isOrdered()) return right.max();
        else return left.max();
    }

    /**
     *  je minimalna hodnota stromu, null ak je strom prazdny
     */
    @Override
    Integer min() {
        if (left == null && right == null) return null;
        else if (left == null) return right.min();
        else if (right == null) return left.min();
        if (isOrdered()) return left.min();
        else return left.min();
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        if (left == null && right == null) return 1;
        else if (left == null) return right.depth()+1;
        else if (right == null) return left.depth()+1;
        return (left.depth() >= right.depth()) ? left.depth()+1 : right.depth()+1;
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        if (left == null && right == null) return true;
        else if (left == null) return right.isUnique();
        else if (right == null) return left.isUnique();
        if (left.depth() == 0 || right.depth() == 0) {
            return left.max() == right.max();
        }
        return left.isUnique() && right.isUnique();
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        if (left == null && right == null) return true;
        else if (left == null) return right.isOrdered();
        else if (right == null) return left.isOrdered();
        if (left.depth() == 0 || right.depth() == 0) {
            return left.max() <= right.min();
        }
        return left.isOrdered() && right.isOrdered() && left.max() <= right.min();
    }

    @Override
    public String toString() {
        String out = "";
        out += "(";
        if (left == null) out += "null";
        else if (left.depth() == 0) out += left.toString();
        else out += left.toString();
        out += ",";
        if (right == null) out += "null";
        else if (right.depth() == 0) out += right.toString();
        else out += right.toString();
        out += ")";
        return out;
    }
}
