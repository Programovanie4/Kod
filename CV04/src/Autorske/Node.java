package Autorske;

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
        Integer lmax = (left != null)?left.max():null;  // max laveho podstromu
        Integer rmax = (right != null)?right.max():null;  // max praveho podstromu
        if (lmax == null) return rmax;  // trochu opatrnejsi max
        else if (rmax == null) return lmax;
        else return Math.max(lmax, rmax);
    }

    /**
     * hlbka stromu
     */
    @Override
    int depth() {
        return 1+Math.max( (left  != null)?left.depth() :0,
                           (right != null)?right.depth():0);
    }

    /**
     * platí, ak všetky hodnoty (v listoch stromu) sú rovnaké
     */
    @Override
    boolean isUnique() {
        Integer max = max();    // zoberieme nejaky prvok stromu
        return (max == null || equalsTo(max));  // a tem musi byt rovnaky vsade
    }

    @Override
    boolean equalsTo(int x) {
        return
            ((left == null || left.equalsTo(x))
            &&
            ((right == null || right.equalsTo(x))));
    }

    /**
     * prvky v ľavom podstrome sú menšie alebo rovné ako prvky v pravom podstrome, a to pre každý vnútorný vrchol
     */
    @Override
    boolean isOrdered() {
        Integer min = preorder(Integer.MIN_VALUE);
        return min != null;     // ak po prelezeni stromu min != null, tak bol usporiadany, min je najvacsi prvok
    }

    /**
     * ak min == null, vrati null
     * inak trochu opatrnejsie right.preorder(left.preorder(min))
     * @return
     */
    @Override
    Integer preorder(Integer min) {
        Integer min1 = (min != null)?((left != null)?left.preorder(min):min):null;
                    // min1 je null, alebo najvacsi v lavom podstrome
        Integer min2 = (min1 != null)?((right != null)?right.preorder(min1):min1):null;
                    // min2 je null, alebo najvacsi v pravom a lavom podstrome
        return min2;
    }

    @Override
    public String toString() {
        return "(" + ((left!=null)?left:"null") + "," + ((right!=null)?right:"null") + ")";
    }
}
