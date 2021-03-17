package Verejne;

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
        return null;
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        return 0;
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        return false;
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
